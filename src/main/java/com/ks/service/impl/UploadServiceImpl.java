package com.ks.service.impl;

import com.ks.dao.ExamQuestionBankMapper;
import com.ks.dto.ExamQuestionBank;
import com.ks.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public int insertSelective(ExamQuestionBank dto) {
        return examQuestionBankMapper.insertSelective(dto);
    }
}
