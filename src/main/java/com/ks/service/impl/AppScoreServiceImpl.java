package com.ks.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.ks.constants.QuestionBankTypeEnum;
import com.ks.dao.ExamQuestionBankAnswerMapper;
import com.ks.dao.ExamQuestionBankScoreMapper;
import com.ks.dao.ExamUserAnswerYaMapper;
import com.ks.dto.*;
import com.ks.service.AppScoreService;
import com.ks.vo.ScoreVo;
import lombok.extern.slf4j.Slf4j;
import net.chinahrd.utils.ArithUtil;
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


    /**
     * TODO throw ex
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
        ExamUserAnswerYaExample uaYaExampl = new ExamUserAnswerYaExample();
        uaYaExampl.createCriteria().andQuestionBankIdEqualTo(questionBankId).andUserIdEqualTo(enName);
        examUserAnswerYaMapper.deleteByExample(uaYaExampl);

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

        //  用户答案的多值map, key: qId, value: List<ExamUserAnswerYa>
        Multimap<String, ExamUserAnswerYa> uaYaMultiMap = ArrayListMultimap.create();
        userAnswerYaList.forEach(n -> {
            String questionId = n.getQuestionId();
            uaYaMultiMap.put(questionId, n);
        });

        ExamQuestionBankAnswerExample qbAnswerExample = new ExamQuestionBankAnswerExample();
        qbAnswerExample.createCriteria().andQuestionBankIdEqualTo(questionBankId);
        List<ExamQuestionBankAnswer> qbAnswerList = examQuestionBankAnswerMapper.selectByExample(qbAnswerExample);
        //  答案的多值map, key: qId, value: List<ExamQuestionBankAnswer>
        Multimap<String, ExamQuestionBankAnswer> qbAnswerMultiMap = ArrayListMultimap.create();
        qbAnswerList.forEach(n -> {
            String questionId = n.getQuestionId();
            qbAnswerMultiMap.put(questionId, n);
        });

        Map<String, Boolean> result = Maps.newHashMap();
        Multiset<String> keys = uaYaMultiMap.keys();
        // 获取用户多值Map里的questionId
        for (String questionId : keys) {
            Collection<ExamQuestionBankAnswer> qAnswerList = qbAnswerMultiMap.get(questionId);
            Collection<ExamUserAnswerYa> qUaYaList = uaYaMultiMap.get(questionId);

            // 每个key对应一个List, 用户答案和真答案匹配
//            for (ExamUserAnswerYa uaYa : qUaYaList) {
//                boolean yes = false;
//                String uaABCD = uaYa.getUserAnswer();
//                for (ExamQuestionBankAnswer qbAnswer : qAnswerList) {
//                    String answerABCD = qbAnswer.getAnswerno();
//                    if (uaABCD.equals(answerABCD)) {
//                        yes = true;
//                        break;
//                    } else {
//                        yes = false;
//                    }
//                }
//                result.put(questionId, yes);
//            }
            result.put(questionId, match(qUaYaList, qAnswerList));
        }
        return ratio(result, qbAnswerList, questionBankId);
    }

    private boolean match(Collection<ExamUserAnswerYa> qUaYaList, Collection<ExamQuestionBankAnswer> qAnswerList) {
        boolean yes = false;
        String qAnswerStr = "";
        for (ExamQuestionBankAnswer qAnswer : qAnswerList) {
            if (qAnswer.getIsanswer()) {
                qAnswerStr += qAnswer.getAnswerno();
            }
        }

        for (ExamUserAnswerYa uaYa : qUaYaList) {
            String uaABCD = uaYa.getUserAnswer();
            if (StringUtils.containsAny(qAnswerStr, uaABCD)) {
                yes = true;
            } else {
                yes = false;
            }
        }
        return yes;
    }


    /**
     * 计算正确率
     *
     * @param result
     * @param qbAnswerList
     * @param questionBankId TODO return
     */
    private List<ScoreVo> ratio(Map<String, Boolean> result, List<ExamQuestionBankAnswer> qbAnswerList, String questionBankId) {
        int sRight = 0, sWrong = 0;
        int mRight = 0, mWrong = 0;
        int ynRight = 0, ynWrong = 0;

        List<Map.Entry<String, Boolean>> sList = Lists.newArrayList();
        List<Map.Entry<String, Boolean>> mList = Lists.newArrayList();
        List<Map.Entry<String, Boolean>> ynList = Lists.newArrayList();

        List<ExamQuestionBankAnswer> trueAnswerList = Lists.newArrayList();
        for (ExamQuestionBankAnswer qbAnswer : qbAnswerList) {
            if (qbAnswer.getIsanswer()) {
                trueAnswerList.add(qbAnswer);
            }
        }


        Set<Map.Entry<String, Boolean>> entries = result.entrySet();
        for (Map.Entry<String, Boolean> entry : entries) {

            String questionId = entry.getKey();
            Boolean yes = entry.getValue();

            for (ExamQuestionBankAnswer trueAnswer : trueAnswerList) {
                if (questionId.equals(trueAnswer.getQuestionId())) {
                    String type = trueAnswer.getType();
//                    if (QuestionBankTypeEnum.SINGLE_QUESTION.getCode().equals(type)) {
//                        sList.add(entry);
//                    } else if (QuestionBankTypeEnum.MULTIPLE_QUESTION.getCode().equals(type) && yes) {
//                        mList.add(entry);
//                    } else if (QuestionBankTypeEnum.YES_NO_QUESTION.getCode().equals(type) && !yes) {
//                        ynList.add(entry);
//                    }
                    if (QuestionBankTypeEnum.SINGLE_QUESTION.getCode().equals(type) && yes) {
                        sRight++;
                    } else if (QuestionBankTypeEnum.SINGLE_QUESTION.getCode().equals(type) && !yes) {
                        sWrong++;
                    } else if (QuestionBankTypeEnum.MULTIPLE_QUESTION.getCode().equals(type) && yes) {
                        mRight++;
                    } else if (QuestionBankTypeEnum.MULTIPLE_QUESTION.getCode().equals(type) && !yes) {
                        mWrong++;
                    } else if (QuestionBankTypeEnum.YES_NO_QUESTION.getCode().equals(type) && yes) {
                        ynRight++;
                    } else if (QuestionBankTypeEnum.YES_NO_QUESTION.getCode().equals(type) && !yes) {
                        ynWrong++;
                    }
                }
            }
        }

        if (sList.size() > 0) {

        }

        // 得分
        ExamQuestionBankScoreExample qbScoreExample = new ExamQuestionBankScoreExample();
        qbScoreExample.createCriteria().andQuestionBankIdEqualTo(questionBankId);
        List<ExamQuestionBankScore> qbScoreList = examQuestionBankScoreMapper.selectByExample(qbScoreExample);
        if (CollectionUtils.isEmpty(qbScoreList)) {
            log.info("没有设置得分");
        }

        List<ScoreVo> rs = Lists.newArrayList();
        if (sRight != 0 || sWrong != 0) {
            ScoreVo sVo = calcRatio(sRight, sWrong, QuestionBankTypeEnum.SINGLE_QUESTION.getCode());
            sVo.setScore(calcScore(sVo.getRight(), QuestionBankTypeEnum.SINGLE_QUESTION.getCode(), qbScoreList));
            rs.add(sVo);
        }

        if (mRight != 0 || mWrong != 0) {
            ScoreVo mVo = calcRatio(mRight, mWrong, QuestionBankTypeEnum.MULTIPLE_QUESTION.getCode());
            mVo.setScore(calcScore(mVo.getRight(), QuestionBankTypeEnum.MULTIPLE_QUESTION.getCode(), qbScoreList));
            rs.add(mVo);
        }
        if (ynRight != 0 || ynWrong != 0) {
            ScoreVo ynVo = calcRatio(ynRight, ynWrong, QuestionBankTypeEnum.YES_NO_QUESTION.getCode());
            ynVo.setScore(calcScore(ynVo.getRight(), QuestionBankTypeEnum.YES_NO_QUESTION.getCode(), qbScoreList));
            rs.add(ynVo);
        }
        return rs;
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
