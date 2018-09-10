package com.ks.service.impl;

import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dao.ExamTrueAnswerMapper;
import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankDto;
import com.ks.dto.ExamTrueAnswer;
import com.ks.service.UploadService;
import org.springframework.beans.BeanUtils;
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
 * @DATE 2018年09月10日 14:11
 * @Verdion 1.0 版本
 * ${tags}
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService {


    @Autowired
    private ExamQuestionBankMapper examQuestionBankMapper;

    @Autowired
    private ExamTrueAnswerMapper examTrueAnswerMapper;


    @Transactional
    @Override
    public int insertSelective(ExamQuestionBankDto dto) {
        int rs = 0;
        ExamQuestionBank o = new ExamQuestionBank();
        BeanUtils.copyProperties(dto, o);

        List<ExamTrueAnswer> examTrueAnswerList = dto.getExamTrueAnswerList();
        // 答案
        examTrueAnswerList.forEach(n -> examTrueAnswerMapper.insertSelective(n));
        // 题目
        rs += examQuestionBankMapper.insertSelective(o);

        return rs;
    }
}
