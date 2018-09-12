package com.ks.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankExample;
import com.ks.dto.ExamQuestionBankTotal;
import com.ks.service.impl.ExamQuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Page<ExamQuestionBank> findByPage(int pageNo, int pageSize, String questionBankId) {
        PageHelper.startPage(pageNo, pageSize);
        return examQuestionBankMapper.findByPage(questionBankId);
    }

    @Override
    public List<ExamQuestionBankTotal> queryTotal() {
        return examQuestionBankMapper.queryTotal();
    }

    @Override
    public int deleteByExample(String questionBankId) {
        ExamQuestionBankExample example = new ExamQuestionBankExample();
        example.createCriteria().andQuestionBankIdEqualTo(questionBankId);
        return examQuestionBankMapper.deleteByExample(example);
    }
}
