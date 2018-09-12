package com.ks.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dao.ExamTrueAnswerMapper;
import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankExample;
import com.ks.dto.ExamQuestionBankTotal;
import com.ks.dto.ExamTrueAnswerExample;
import com.ks.service.impl.ExamQuestionBankService;
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
    private ExamTrueAnswerMapper examTrueAnswerMapper;

    @Override
    public Page<ExamQuestionBank> findByPage(int pageNo, int pageSize, String questionBankId) {
        PageHelper.startPage(pageNo, pageSize);
        return examQuestionBankMapper.findByPage(questionBankId);
    }

    @Override
    public List<ExamQuestionBankTotal> queryTotal() {
        return examQuestionBankMapper.queryTotal();
    }

    @Transactional
    @Override
    public int deleteByExample(String questionBankId) {
        int i = 0, j = 0;
        try {
            ExamQuestionBankExample example = new ExamQuestionBankExample();
            example.createCriteria().andQuestionBankIdEqualTo(questionBankId);
            i = examQuestionBankMapper.deleteByExample(example);

            ExamTrueAnswerExample trueAnswerExample = new ExamTrueAnswerExample();
            trueAnswerExample.createCriteria().andQuestionBankIdEqualTo(questionBankId);
            j = examTrueAnswerMapper.deleteByExample(trueAnswerExample);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return i + j;
    }
}
