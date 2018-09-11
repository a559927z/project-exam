package com.ks.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamTrueAnswer;
import com.ks.service.impl.ExamQuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Page<ExamQuestionBank> findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return examQuestionBankMapper.findByPage();
    }
}
