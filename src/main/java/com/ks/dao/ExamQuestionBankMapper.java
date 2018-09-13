package com.ks.dao;

import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("examQuestionBankMapper")
public interface ExamQuestionBankMapper {
    long countByExample(ExamQuestionBankExample example);

    int deleteByExample(ExamQuestionBankExample example);

    int deleteByPrimaryKey(String questionId);

    int insert(ExamQuestionBank record);

    int insertSelective(ExamQuestionBank record);

    List<ExamQuestionBank> selectByExample(ExamQuestionBankExample example);

    ExamQuestionBank selectByPrimaryKey(String questionId);

    int updateByExampleSelective(@Param("record") ExamQuestionBank record, @Param("example") ExamQuestionBankExample example);

    int updateByExample(@Param("record") ExamQuestionBank record, @Param("example") ExamQuestionBankExample example);

    int updateByPrimaryKeySelective(ExamQuestionBank record);

    int updateByPrimaryKey(ExamQuestionBank record);
}