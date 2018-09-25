package com.ks.dao;

import com.ks.dto.ExamUserAnswerYa;
import com.ks.dto.ExamUserAnswerYaExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("examUserAnswerYaMapper")
public interface ExamUserAnswerYaMapper {
    long countByExample(ExamUserAnswerYaExample example);

    int deleteByExample(ExamUserAnswerYaExample example);

    int deleteByPrimaryKey(String userAnswerId);

    int insert(ExamUserAnswerYa record);

    int insertSelective(ExamUserAnswerYa record);

    List<ExamUserAnswerYa> selectByExample(ExamUserAnswerYaExample example);

    ExamUserAnswerYa selectByPrimaryKey(String userAnswerId);

    int updateByExampleSelective(@Param("record") ExamUserAnswerYa record, @Param("example") ExamUserAnswerYaExample example);

    int updateByExample(@Param("record") ExamUserAnswerYa record, @Param("example") ExamUserAnswerYaExample example);

    int updateByPrimaryKeySelective(ExamUserAnswerYa record);

    int updateByPrimaryKey(ExamUserAnswerYa record);
}