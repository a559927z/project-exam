package com.ks.dao;

import com.github.pagehelper.Page;
import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankExample;
import com.ks.dto.ExamQuestionBankTotal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("examQuestionBankMapperExt")
public interface ExamQuestionBankMapperExt {

    Page<ExamQuestionBank> findByPage(String questionBankId);

    List<ExamQuestionBankTotal> queryTotal();

}