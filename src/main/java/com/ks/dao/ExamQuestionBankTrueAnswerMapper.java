package com.ks.dao;

import com.ks.dto.ExamQuestionBankTrueAnswer;
import com.ks.dto.ExamQuestionBankTrueAnswerExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("examQuestionBankTrueAnswerMapper")
public interface ExamQuestionBankTrueAnswerMapper {
    long countByExample(ExamQuestionBankTrueAnswerExample example);

    int deleteByExample(ExamQuestionBankTrueAnswerExample example);

    int deleteByPrimaryKey(String questionId);

    int insert(ExamQuestionBankTrueAnswer record);

    int insertSelective(ExamQuestionBankTrueAnswer record);

    List<ExamQuestionBankTrueAnswer> selectByExample(ExamQuestionBankTrueAnswerExample example);

    ExamQuestionBankTrueAnswer selectByPrimaryKey(String questionId);

    int updateByExampleSelective(@Param("record") ExamQuestionBankTrueAnswer record, @Param("example") ExamQuestionBankTrueAnswerExample example);

    int updateByExample(@Param("record") ExamQuestionBankTrueAnswer record, @Param("example") ExamQuestionBankTrueAnswerExample example);

    int updateByPrimaryKeySelective(ExamQuestionBankTrueAnswer record);

    int updateByPrimaryKey(ExamQuestionBankTrueAnswer record);
}