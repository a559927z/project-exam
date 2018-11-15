package com.ks.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.ks.constants.EisWebContext;
import com.ks.constants.QuestionBankConstants;
import com.ks.constants.QuestionBankCourseEnum;
import com.ks.constants.QuestionBankTypeEnum;
import com.ks.dao.ExamQuestionBankAnswerMapper;
import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dao.ExamQuestionBankScoreMapper;
import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankAnswer;
import com.ks.dto.ExamQuestionBankScore;
import com.ks.service.UploadService;
import com.ks.utils.StringUtil;
import net.chinahrd.utils.Identities;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月10日 14:11
 * @Verdion 1.0 版本
 * ${tags}
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService {

    final String[] answerNo = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};

    @Autowired
    private ExamQuestionBankMapper examQuestionBankMapper;

    @Autowired
    private ExamQuestionBankAnswerMapper examQuestionBankAnswerMapper;

    @Autowired
    private ExamQuestionBankScoreMapper examQuestionBankScoreMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertSelective(ExamQuestionBank dto) {

        int rs = 0;
        try {
            ExamQuestionBank o = new ExamQuestionBank();
            BeanUtils.copyProperties(dto, o);
            Date date = new Date();
            o.setCreatedBy(EisWebContext.getCurrentUserId());
            o.setUpdatedBy(EisWebContext.getCurrentUserId());
            o.setCreatedDate(date);
            o.setUpdatedDate(date);

            // 题目
            rs += examQuestionBankMapper.insertSelective(o);

            // 答案
            List<ExamQuestionBankAnswer> examTrueAnswerList = processTrueAnswer(o);
            examTrueAnswerList.forEach(n -> examQuestionBankAnswerMapper.insertSelective(n));

        } catch (Exception e) {
            throw e;
        }

        saveScore(dto);
        return rs;
    }

    private void saveScore(ExamQuestionBank dto) {
        String questionBankId = dto.getQuestionBankId();
        String courseId = dto.getCourseId();

        QuestionBankCourseEnum enumByCode = QuestionBankCourseEnum.getEnumByCode(courseId);

        ExamQuestionBankScore sDto = new ExamQuestionBankScore();
        sDto.setQuestionBankId(questionBankId);
        sDto.setQuestionBankType(QuestionBankTypeEnum.SINGLE_QUESTION.getCode());
        sDto.setScore(enumByCode.getSScore());
        examQuestionBankScoreMapper.insertSelective(sDto);

        ExamQuestionBankScore mDto = new ExamQuestionBankScore();
        mDto.setQuestionBankId(questionBankId);
        mDto.setQuestionBankType(QuestionBankTypeEnum.MULTIPLE_QUESTION.getCode());
        mDto.setScore(enumByCode.getMScore());
        examQuestionBankScoreMapper.insertSelective(mDto);

        ExamQuestionBankScore ynDto = new ExamQuestionBankScore();
        ynDto.setQuestionBankId(questionBankId);
        ynDto.setQuestionBankType(QuestionBankTypeEnum.YES_NO_QUESTION.getCode());
        ynDto.setScore(enumByCode.getYnScore());
        examQuestionBankScoreMapper.insertSelective(ynDto);

    }

    private List<ExamQuestionBankAnswer> processTrueAnswer(ExamQuestionBank entry) {
        String answerStr = entry.getAnswer().replace(QuestionBankConstants.ANSWER_PATTERN, "@").replace(QuestionBankConstants.ANSWER_TRUE_PATTERN, "@");
        String trueAnswerStr = entry.getTrueAnswer();

        List<String> answerList =
                Lists.newArrayList(Splitter.on("@").omitEmptyStrings().trimResults().split(answerStr));

        List<String> trueAnswerList =
                Lists.newArrayList(Splitter.on(QuestionBankConstants.ANSWER_TRUE_PATTERN).omitEmptyStrings().trimResults().split(trueAnswerStr));

        List<ExamQuestionBankAnswer> rs = Lists.newArrayList();
        for (int i = 0; i < answerList.size(); i++) {
            String answer = answerList.get(i);
            ExamQuestionBankAnswer qbAnswer = new ExamQuestionBankAnswer();
            qbAnswer.setId(Identities.uuid2());
            qbAnswer.setQuestionId(entry.getQuestionId());
            qbAnswer.setQuestionBankId(entry.getQuestionBankId());
            qbAnswer.setType(entry.getType());
            qbAnswer.setAnswer(answer);
            qbAnswer.setSort(i);
            qbAnswer.setAnswerno(answerNo[i]);
            for (int j = 0; j < trueAnswerList.size(); j++) {
                String trueAnswer = trueAnswerList.get(j);
                if (StringUtil.equals(answer, trueAnswer)) {
                    qbAnswer.setIsanswer(true);
                    break;
                } else {
                    qbAnswer.setIsanswer(false);
                }
            }
            rs.add(qbAnswer);
        }
        return rs;
    }
}
