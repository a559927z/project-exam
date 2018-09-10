package com.ks.service;

import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankDto;

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
public interface UploadService {


    /**
     * 题库入库
     *
     * @param dto
     * @return
     */
    int insertSelective(ExamQuestionBankDto dto);
}
