package com.ks.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.ks.constants.QuestionBankTypeEnum;
import com.ks.dao.*;
import com.ks.dto.*;
import com.ks.service.AppScoreService;
import com.ks.vo.ScoreVo;
import lombok.extern.slf4j.Slf4j;
import net.chinahrd.utils.ArithUtil;
import net.chinahrd.utils.Identities;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年10月12日 15:41
 * @Verdion 1.0 版本
 * ${tags}
 */
@Slf4j
@Service
public class AppScoreServiceImpl implements AppScoreService {

    @Autowired
    private ExamQuestionBankScoreMapper examQuestionBankScoreMapper;

    @Autowired
    private ExamQuestionBankAnswerMapper examQuestionBankAnswerMapper;

    @Autowired
    private ExamUserAnswerYaMapper examUserAnswerYaMapper;

    @Autowired
    private ExamQuestionBankMapper examQuestionBankMapper;

    @Autowired
    private ExamRollUserAnswerMapper examRollUserAnswerMapper;


    /**
     * @param idList
     * @param questionBankId
     * @param enName
     */
    @Override
    public void saveScore(List<String> idList, String questionBankId, String enName) throws Exception {
        if (CollectionUtils.isEmpty(idList)) {
            log.info("没有答题");
        }
        ExamQuestionBankExample qbExample = new ExamQuestionBankExample();
        qbExample.createCriteria().andQuestionBankIdEqualTo(questionBankId);
        List<ExamQuestionBank> examQuestionBankList = examQuestionBankMapper.selectByExample(qbExample);
        String courseId = examQuestionBankList.get(0).getCourseId();

        List<ExamUserAnswerYa> userAnswerYaList = Lists.newArrayList();
        idList.forEach(i -> {
            ArrayList<String> strings = Lists.newArrayList(Splitter.on("@").omitEmptyStrings().trimResults().split(i));
            String selectAnswer = strings.get(0);
            String questionId = strings.get(1);

            ExamUserAnswerYa dto = new ExamUserAnswerYa();
            dto.setUserAnswerId(Identities.uuid2());
            dto.setUserId(enName);
            dto.setQuestionBankId(questionBankId);
            dto.setQuestionId(questionId);
            dto.setUserAnswer(selectAnswer);
            dto.setCourseId(courseId);
            examQuestionBankList.forEach(j -> {
                if (questionId.equals(j.getQuestionId())) {
                    dto.setType(j.getType());
                }
            });
            userAnswerYaList.add(dto);
        });

        Multimap<String, ExamUserAnswerYa> groupByQuestionId = ArrayListMultimap.create();
        userAnswerYaList.forEach(i -> {
            String questionId = i.getQuestionId();
            groupByQuestionId.put(questionId, i);
        });

        // 正确答案
        ExamQuestionBankAnswerExample qbAnswerExample = new ExamQuestionBankAnswerExample();
        qbAnswerExample.createCriteria().andQuestionBankIdEqualTo(questionBankId).andIsanswerEqualTo(true);
        List<ExamQuestionBankAnswer> trueAnswerList = examQuestionBankAnswerMapper.selectByExample(qbAnswerExample);


        Set<Map.Entry<String, Collection<ExamUserAnswerYa>>> userAnswerEntries = groupByQuestionId.asMap().entrySet();
        for (Map.Entry<String, Collection<ExamUserAnswerYa>> entry : userAnswerEntries) {
            String questionId = entry.getKey();
            String trueAnswerStr = "";
            for (ExamQuestionBankAnswer trueAnswer : trueAnswerList) {
                if (questionId.equals(trueAnswer.getQuestionId())) {
                    trueAnswerStr += trueAnswer.getAnswerno();
                }
            }
            List<ExamUserAnswerYa> userAnswerList = Lists.newArrayList();
            userAnswerList.addAll(entry.getValue());

            ExamUserAnswerYa readyDB = new ExamUserAnswerYa();
            BeanUtils.copyProperties(userAnswerList.get(0), readyDB);

            boolean yes = false;
            String userAnswerStr = "";
            for (ExamUserAnswerYa uaAnswer : userAnswerList) {
                String userAnswerNo = uaAnswer.getUserAnswer();
                userAnswerStr += userAnswerNo;
                // 将一方变字符串后，另一方循环检查是否包含
                if (StringUtils.containsAny(userAnswerNo, trueAnswerStr)) {
                    yes = true;
                } else {
                    // 只要有一个是错，本题剩下的用户答案都不用验了
                    yes = false;
                    break;
                }
            }
            readyDB.setTrueAnswer(trueAnswerStr);
            readyDB.setUserAnswer(userAnswerStr);
            if (yes) {
                readyDB.setIsYes(yes);
            } else {
                readyDB.setIsYes(false);
            }
            ExamUserAnswerYaExample delExample = new ExamUserAnswerYaExample();
            delExample.createCriteria().andQuestionIdEqualTo(readyDB.getQuestionId()).andUserIdEqualTo(readyDB.getUserId());
            examUserAnswerYaMapper.deleteByExample(delExample);
            examUserAnswerYaMapper.insertSelective(readyDB);
        }
    }

    @Override
    public List<ScoreVo> calcRollScore(String rollId, String userId) {
        ExamRollUserAnswerExample ruaExample = new ExamRollUserAnswerExample();
        ruaExample.createCriteria().andRollIdEqualTo(rollId).andUserIdEqualTo(userId);
        List<ExamRollUserAnswer> ruaList = examRollUserAnswerMapper.selectByExample(ruaExample);
        if (CollectionUtils.isEmpty(ruaList)) {
            log.info("没有答题");
        }

        //根据题目类型分组
        Map<String, List<ExamRollUserAnswer>> dateListMap =
                ruaList
                        .stream()
                        .collect(Collectors.groupingBy(ExamRollUserAnswer::getType));
        Set<Map.Entry<String, List<ExamRollUserAnswer>>> entries = dateListMap.entrySet();
        List<ScoreVo> rs = Lists.newArrayList();
        int sRight = 0, sWrong = 0;
        int mRight = 0, mWrong = 0;
        int ynRight = 0, ynWrong = 0;
        List<ExamRollUserAnswer> sUaYaList = dateListMap.get(QuestionBankTypeEnum.SINGLE_QUESTION.getCode());
        List<ExamRollUserAnswer> mUaYaList = dateListMap.get(QuestionBankTypeEnum.MULTIPLE_QUESTION.getCode());
        List<ExamRollUserAnswer> ynUaYaList = dateListMap.get(QuestionBankTypeEnum.YES_NO_QUESTION.getCode());


        if (CollectionUtils.isNotEmpty(sUaYaList)) {
            ScoreVo vo1 = matchRoll(sUaYaList, QuestionBankTypeEnum.SINGLE_QUESTION.getCode());
            sRight = vo1.getRight();
            sWrong = vo1.getWrong();
            rs.add(vo1);
        }
        if (CollectionUtils.isNotEmpty(mUaYaList)) {
            ScoreVo vo2 = matchRoll(mUaYaList, QuestionBankTypeEnum.MULTIPLE_QUESTION.getCode());
            mRight = vo2.getRight();
            mWrong = vo2.getWrong();
            rs.add(vo2);
        }
        if (CollectionUtils.isNotEmpty(ynUaYaList)) {
            ScoreVo vo3 = matchRoll(ynUaYaList, QuestionBankTypeEnum.YES_NO_QUESTION.getCode());
            ynRight = vo3.getRight();
            ynWrong = vo3.getWrong();
            rs.add(vo3);
        }
        rs.add(calcRatio((sRight + mRight + ynRight), (sWrong + mWrong + ynWrong), "9999"));
        return rs;
    }


    @Override
    public List<ScoreVo> calcYaScore(String questionBankId, String userId) {
        ExamUserAnswerYaExample uaYaExample = new ExamUserAnswerYaExample();
        uaYaExample.createCriteria()
                .andQuestionBankIdEqualTo(questionBankId)
                .andUserIdEqualTo(userId);
        List<ExamUserAnswerYa> yaUserAnswerList = examUserAnswerYaMapper.selectByExample(uaYaExample);
        if (CollectionUtils.isEmpty(yaUserAnswerList)) {
            log.info("没有答题");
        }
        //根据题目类型分组
        Map<String, List<ExamUserAnswerYa>> dateListMap =
                yaUserAnswerList
                        .stream()
                        .collect(Collectors.groupingBy(ExamUserAnswerYa::getType));
        Set<Map.Entry<String, List<ExamUserAnswerYa>>> entries = dateListMap.entrySet();
        List<ScoreVo> rs = Lists.newArrayList();
        int sRight = 0, sWrong = 0;
        int mRight = 0, mWrong = 0;
        int ynRight = 0, ynWrong = 0;
        List<ExamUserAnswerYa> sUaYaList = dateListMap.get(QuestionBankTypeEnum.SINGLE_QUESTION.getCode());
        List<ExamUserAnswerYa> mUaYaList = dateListMap.get(QuestionBankTypeEnum.MULTIPLE_QUESTION.getCode());
        List<ExamUserAnswerYa> ynUaYaList = dateListMap.get(QuestionBankTypeEnum.YES_NO_QUESTION.getCode());


        if (CollectionUtils.isNotEmpty(sUaYaList)) {
            ScoreVo vo1 = matchYa(sUaYaList, QuestionBankTypeEnum.SINGLE_QUESTION.getCode());
            sRight = vo1.getRight();
            sWrong = vo1.getWrong();
            rs.add(vo1);
        }
        if (CollectionUtils.isNotEmpty(mUaYaList)) {
            ScoreVo vo2 = matchYa(mUaYaList, QuestionBankTypeEnum.MULTIPLE_QUESTION.getCode());
            mRight = vo2.getRight();
            mWrong = vo2.getWrong();
            rs.add(vo2);
        }
        if (CollectionUtils.isNotEmpty(ynUaYaList)) {
            ScoreVo vo3 = matchYa(ynUaYaList, QuestionBankTypeEnum.YES_NO_QUESTION.getCode());
            ynRight = vo3.getRight();
            ynWrong = vo3.getWrong();
            rs.add(vo3);
        }
        rs.add(calcRatio((sRight + mRight + ynRight), (sWrong + mWrong + ynWrong), "9999"));
        return rs;
    }

    private ScoreVo matchYa(List<ExamUserAnswerYa> uaYaList, String type) {
        int right = 0;
        int wrong = 0;
        for (ExamUserAnswerYa s : uaYaList) {
            if (s.getIsYes()) {
                right++;
            } else {
                wrong++;
            }
        }
        return calcRatio(right, wrong, type);
    }

    private ScoreVo matchRoll(List<ExamRollUserAnswer> uaYaList, String type) {
        int right = 0;
        int wrong = 0;
        for (ExamRollUserAnswer s : uaYaList) {
            if (s.getIsYes()) {
                right++;
            } else {
                wrong++;
            }
        }
        return calcRatio(right, wrong, type);
    }


    /**
     * 计算正确率
     *
     * @param right
     * @param wrong
     * @param type
     * @return
     */
    private ScoreVo calcRatio(int right, int wrong, String type) {
        ScoreVo vo = new ScoreVo();
        vo.setRight(right);
        vo.setWrong(wrong);
        vo.setType(type);
        vo.setRatioStr(
                ArithUtil.mul(ArithUtil.div(right, (right + wrong), 2), 100) + "%"
        );
        return vo;
    }

}
