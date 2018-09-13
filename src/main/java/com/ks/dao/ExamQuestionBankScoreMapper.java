package com.ks.dao;

import com.ks.dto.ExamQuestionBankScore;
import com.ks.dto.ExamQuestionBankScoreExample;
import com.ks.dto.ExamQuestionBankScoreKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("examQuestionBankScoreMapper")
public interface ExamQuestionBankScoreMapper {
    long countByExample(ExamQuestionBankScoreExample example);

    int deleteByExample(ExamQuestionBankScoreExample example);

    int deleteByPrimaryKey(ExamQuestionBankScoreKey key);

    int insert(ExamQuestionBankScore record);

    int insertSelective(ExamQuestionBankScore record);

    List<ExamQuestionBankScore> selectByExample(ExamQuestionBankScoreExample example);

    ExamQuestionBankScore selectByPrimaryKey(ExamQuestionBankScoreKey key);

    int updateByExampleSelective(@Param("record") ExamQuestionBankScore record, @Param("example") ExamQuestionBankScoreExample example);

    int updateByExample(@Param("record") ExamQuestionBankScore record, @Param("example") ExamQuestionBankScoreExample example);

    int updateByPrimaryKeySelective(ExamQuestionBankScore record);

    int updateByPrimaryKey(ExamQuestionBankScore record);
}