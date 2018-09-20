package com.ks.dao;

import com.ks.dto.ExamQuestionBankYa;
import com.ks.dto.ExamQuestionBankYaExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("examQuestionBankYaMapper")
public interface ExamQuestionBankYaMapper {
    long countByExample(ExamQuestionBankYaExample example);

    int deleteByExample(ExamQuestionBankYaExample example);

    int deleteByPrimaryKey(String questionBankYaId);

    int insert(ExamQuestionBankYa record);

    int insertSelective(ExamQuestionBankYa record);

    List<ExamQuestionBankYa> selectByExample(ExamQuestionBankYaExample example);

    ExamQuestionBankYa selectByPrimaryKey(String questionBankYaId);

    int updateByExampleSelective(@Param("record") ExamQuestionBankYa record, @Param("example") ExamQuestionBankYaExample example);

    int updateByExample(@Param("record") ExamQuestionBankYa record, @Param("example") ExamQuestionBankYaExample example);

    int updateByPrimaryKeySelective(ExamQuestionBankYa record);

    int updateByPrimaryKey(ExamQuestionBankYa record);
}