package com.ks.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.ks.constants.QuestionBankTypeEnum;
import com.ks.dao.ExamQuestionBankAnswerMapper;
import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dao.ExamQuestionBankScoreMapper;
import com.ks.dao.ExamUserAnswerYaMapper;
import com.ks.dto.*;
import com.ks.service.AppScoreService;
import com.ks.vo.ScoreVo;
import lombok.extern.slf4j.Slf4j;
import net.chinahrd.utils.ArithUtil;
import net.chinahrd.utils.CollectionKit;
import net.chinahrd.utils.Identities;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    /**
     * TODO idList 相同题目合拼，save时，计算答对还是错。
     *
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

//        ExamUserAnswerYaExample uaYaExampl = new ExamUserAnswerYaExample();
//        uaYaExampl.createCriteria().andQuestionBankIdEqualTo(questionBankId).andUserIdEqualTo(enName);
//        examUserAnswerYaMapper.deleteByExample(uaYaExampl);

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
            examUserAnswerYaMapper.insertSelective(dto);
        });
    }


    @Override
    public List<ScoreVo> calcScore(String questionBankId, String enName) {
        ExamUserAnswerYaExample uaYaExampl = new ExamUserAnswerYaExample();
        uaYaExampl.createCriteria().andQuestionBankIdEqualTo(questionBankId).andUserIdEqualTo(enName);
        List<ExamUserAnswerYa> userAnswerYaList = examUserAnswerYaMapper.selectByExample(uaYaExampl);
        if (CollectionUtils.isEmpty(userAnswerYaList)) {
            log.info("没有答题");
        }

        ExamQuestionBankAnswerExample qbAnswerExample = new ExamQuestionBankAnswerExample();
        qbAnswerExample.createCriteria().andQuestionBankIdEqualTo(questionBankId).andIsanswerEqualTo(true);
        List<ExamQuestionBankAnswer> qbAnswerList = examQuestionBankAnswerMapper.selectByExample(qbAnswerExample);

        List<ScoreVo> rs = Lists.newArrayList();
        //  用户答案
        Multimap<String, String> sUaYaMultiMap = ArrayListMultimap.create();
        Multimap<String, String> mUaYaMultiMap = ArrayListMultimap.create();
        Multimap<String, String> yUaYaMultiMap = ArrayListMultimap.create();
        userAnswerYaList.forEach(n -> {
            String questionId = n.getQuestionId();
            String userAnswer = n.getUserAnswer();
            String type = n.getType();
            if (QuestionBankTypeEnum.SINGLE_QUESTION.getCode().equals(type)) {
                sUaYaMultiMap.put(questionId, userAnswer);
            }
            if (QuestionBankTypeEnum.MULTIPLE_QUESTION.getCode().equals(type)) {
                mUaYaMultiMap.put(questionId, userAnswer);
            }
            if (QuestionBankTypeEnum.YES_NO_QUESTION.getCode().equals(type)) {
                yUaYaMultiMap.put(questionId, userAnswer);
            }
        });

        //  答案
        Multimap<String, String> sQbAnswerMultiMap = ArrayListMultimap.create();
        Multimap<String, String> mQbAnswerMultiMap = ArrayListMultimap.create();
        Multimap<String, String> yQbAnswerMultiMap = ArrayListMultimap.create();
        qbAnswerList.forEach(n -> {
            String questionId = n.getQuestionId();
            String userAnswer = n.getAnswerno();
            String type = n.getType();
            if (QuestionBankTypeEnum.SINGLE_QUESTION.getCode().equals(type)) {
                sQbAnswerMultiMap.put(questionId, userAnswer);
            }
            if (QuestionBankTypeEnum.MULTIPLE_QUESTION.getCode().equals(type)) {
                mQbAnswerMultiMap.put(questionId, userAnswer);
            }
            if (QuestionBankTypeEnum.YES_NO_QUESTION.getCode().equals(type)) {
                yQbAnswerMultiMap.put(questionId, userAnswer);
            }
        });

        int sRight = 0, sWrong = 0;
        int mRight = 0, mWrong = 0;
        int ynRight = 0, ynWrong = 0;
        if (sUaYaMultiMap.size() > 0) {
            ScoreVo vo1 = match(sUaYaMultiMap, sQbAnswerMultiMap, QuestionBankTypeEnum.SINGLE_QUESTION.getCode());
            sRight = vo1.getRight();
            sWrong = vo1.getWrong();
            rs.add(vo1);
        }
        if (mUaYaMultiMap.size() > 0) {
            ScoreVo vo2 = match(mUaYaMultiMap, mQbAnswerMultiMap, QuestionBankTypeEnum.MULTIPLE_QUESTION.getCode());
            mRight = vo2.getRight();
            mWrong = vo2.getWrong();
            rs.add(vo2);
        }
        if (yUaYaMultiMap.size() > 0) {
            ScoreVo vo3 = match(yUaYaMultiMap, yQbAnswerMultiMap, QuestionBankTypeEnum.YES_NO_QUESTION.getCode());
            ynRight = vo3.getRight();
            ynWrong = vo3.getWrong();
            rs.add(vo3);
        }
        rs.add(calcRatio((sRight + mRight + ynRight), (sWrong + mWrong + ynWrong), "9999"));
        return rs;
    }


    /**
     * 计算正确率
     *
     * @param uaYaMultiMap
     * @param qbAnswerMultiMap
     * @param type
     * @return
     */
    private ScoreVo match(Multimap<String, String> uaYaMultiMap, Multimap<String, String> qbAnswerMultiMap, String type) {
        int right = 0;
        int wrong = 0;
        Set<Map.Entry<String, Collection<String>>> entries = uaYaMultiMap.asMap().entrySet();
        for (Map.Entry<String, Collection<String>> entry : entries) {
            String questionId = entry.getKey();

            Collection<String> qAnswerList = qbAnswerMultiMap.get(questionId);
            String trueAnswerStr = CollectionKit.convertToString(qAnswerList, "");
            Collection<String> qUaYaList = entry.getValue();
            boolean yes = false;
            for (String uaAnswer : qUaYaList) {
                if (StringUtils.containsAny(uaAnswer, trueAnswerStr)) {
                    yes = true;
                } else {
                    yes = false;
                }
            }
            if (yes) {
                right++;
            } else {
                wrong++;
            }
        }
        ScoreVo scoreVo = calcRatio(right, wrong, type);
        return scoreVo;

    }


    /**
     * 计算得分
     *
     * @param right
     * @param qbScoreList
     * @return
     */
    private double calcScore(int right, String type, List<ExamQuestionBankScore> qbScoreList) {
        for (ExamQuestionBankScore qbScore : qbScoreList) {
            String qbType = qbScore.getQuestionBankType();
            if (qbType.equals(type)) {
                Double score = qbScore.getScore();
                return ArithUtil.mul(right, score);
            }
        }
        return 0.0;
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
