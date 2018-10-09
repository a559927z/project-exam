package com.ks.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ks.constants.QuestionBankConstants;
import com.ks.dao.*;
import com.ks.dto.*;
import com.ks.service.ExamQuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月12日 00:35
 * @Verdion 1.0 版本
 * ${tags}
 */
@Service("examQuestionBankService")
public class ExamQuestionBankServiceImpl implements ExamQuestionBankService {

    @Autowired
    private ExamQuestionBankMapper examQuestionBankMapper;
    @Autowired
    private ExamQuestionBankMapperExt examQuestionBankMapperExt;

    @Autowired
    private ExamQuestionBankTrueAnswerMapper examQuestionBankTrueAnswerMapper;

    @Autowired
    private ExamQuestionBankScoreMapper examQuestionBankScoreMapper;


    @Autowired
    private ExamQuestionBankYaMapper examQuestionBankYaMapper;


    @Autowired
    private ExamUserAnswerYaMapper examUserAnswerYaMapper;


    @Override
    public List<ExamQuestionBankReportDto> findReport(String questionBankId) {
        return examQuestionBankMapperExt.findReport(questionBankId);
    }

    @Override
    public List<ExamQuestionBank> queryAllQuestionBank() {
        return examQuestionBankMapper.selectByExample(null);
    }

    @Override
    public Page<ExamQuestionBank> findByPage(int pageNo, int pageSize, String questionBankId) {
        PageHelper.startPage(pageNo, pageSize);
        return examQuestionBankMapperExt.findByPage(questionBankId);
    }

    @Override
    public List<ExamQuestionBank> selectByExample(String questionBankId) {
        ExamQuestionBankExample example = new ExamQuestionBankExample();
        example.createCriteria().andQuestionBankIdEqualTo(questionBankId);
        return examQuestionBankMapper.selectByExample(example);
    }


    @Override
    public List<ExamQuestionBankTotal> queryTotal() {
        return examQuestionBankMapperExt.queryTotal();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteByExample(String questionBankId) {
        Integer val = checkUseing(questionBankId);
        if (val != 0) {
            return val;
        }


        int i = 0, j = 0;
        try {
            ExamQuestionBankExample example = new ExamQuestionBankExample();
            example.createCriteria().andQuestionBankIdEqualTo(questionBankId);
            i = examQuestionBankMapper.deleteByExample(example);

            ExamQuestionBankTrueAnswerExample trueAnswerExample = new ExamQuestionBankTrueAnswerExample();
            trueAnswerExample.createCriteria().andQuestionBankIdEqualTo(questionBankId);
            j = examQuestionBankTrueAnswerMapper.deleteByExample(trueAnswerExample);

        } catch (Exception e) {
            throw new RuntimeException();
        }
        return i + j;
    }

    /**
     * 检查题库是否被使用中
     *
     * @param questionBankId
     * @return
     */
    private Integer checkUseing(String questionBankId) {
        ExamUserAnswerYaExample uaYaExample = new ExamUserAnswerYaExample();
        uaYaExample.createCriteria().andQuestionBankIdEqualTo(questionBankId);
        List<ExamUserAnswerYa> uaYaList = examUserAnswerYaMapper.selectByExample(uaYaExample);
        if (uaYaList.size() > 0) {
            // 学员在“考前押题里”有操作记录
            return QuestionBankConstants.USER_ANSWER_YA_USEING;
        }

        ExamQuestionBankYaExample qbYaExample = new ExamQuestionBankYaExample();
        qbYaExample.createCriteria().andQuestionBankIdEqualTo(questionBankId);
        List<ExamQuestionBankYa> qbYaList = examQuestionBankYaMapper.selectByExample(qbYaExample);
        if (qbYaList.size() > 0) {
            // 已经被列入押题管理
            return QuestionBankConstants.QUESTION_BANK_YA_USEING;
        }
        return QuestionBankConstants.NOT_USEING;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateQuestionType(String questionBankId, Double singleId, Double multipleId, Double yesNoId) {

        int rs = 0;
        for (int i = 1; i < 4; i++) {
            ExamQuestionBankScoreExample where = new ExamQuestionBankScoreExample();
            ExamQuestionBankScore val = new ExamQuestionBankScore();
            val.setQuestionBankId(questionBankId);
            if (i == 1) {
                val.setScore(singleId);
                val.setQuestionBankType("1");
                where.createCriteria()
                        .andQuestionBankIdEqualTo(questionBankId)
                        .andQuestionBankTypeEqualTo("1");
            } else if (i == 2) {
                val.setScore(multipleId);
                val.setQuestionBankType("2");
                where.createCriteria()
                        .andQuestionBankIdEqualTo(questionBankId)
                        .andQuestionBankTypeEqualTo("2");
            } else if (i == 3) {
                val.setScore(yesNoId);
                val.setQuestionBankType("3");
                where.createCriteria()
                        .andQuestionBankIdEqualTo(questionBankId)
                        .andQuestionBankTypeEqualTo("3");
            }
            examQuestionBankScoreMapper.deleteByExample(where);
            rs += examQuestionBankScoreMapper.insert(val);
        }
        return rs;
    }
}
