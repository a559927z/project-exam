package com.ks.service.impl;

import com.github.pagehelper.Page;
import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankExample;
import com.ks.dto.ExamQuestionBankTotal;
import com.ks.dto.ExamTrueAnswer;

import java.util.List;

/**
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年09月12日 00:36
 * @Verdion 1.0 版本
 * ${tags}
 */
public interface ExamQuestionBankService {

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<ExamQuestionBank> findByPage(int pageNo, int pageSize, String questionBankId);

    /**
     * @param questionBankId
     * @return
     */
    List<ExamQuestionBank> selectByExample(String questionBankId);

    /**
     * 题库的总题数
     *
     * @return
     */
    List<ExamQuestionBankTotal> queryTotal();


    int deleteByExample(String questionBankId);


    /**
     * 设置分数
     *
     * @param singleId
     * @param multipleId
     * @param yesNoId
     * @return
     */
    int updateQuestionType(String questionBankId, Double singleId, Double multipleId, Double yesNoId);
}
