package com.ks.dao;

import com.ks.dto.ExamQuestionBankAnswer;
import com.ks.dto.ExamQuestionBankAnswerExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("examQuestionBankAnswerMapper")
public interface ExamQuestionBankAnswerMapper {
    long countByExample(ExamQuestionBankAnswerExample example);

    int deleteByExample(ExamQuestionBankAnswerExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExamQuestionBankAnswer record);

    int insertSelective(ExamQuestionBankAnswer record);

    List<ExamQuestionBankAnswer> selectByExample(ExamQuestionBankAnswerExample example);

    ExamQuestionBankAnswer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExamQuestionBankAnswer record, @Param("example") ExamQuestionBankAnswerExample example);

    int updateByExample(@Param("record") ExamQuestionBankAnswer record, @Param("example") ExamQuestionBankAnswerExample example);

    int updateByPrimaryKeySelective(ExamQuestionBankAnswer record);

    int updateByPrimaryKey(ExamQuestionBankAnswer record);
}