package com.ks.dao;

import com.github.pagehelper.Page;
import com.ks.dto.ExamTrueAnswer;
import com.ks.dto.ExamTrueAnswerExample;
import com.ks.dto.ExamTrueAnswerKey;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("examTrueAnswerMapper")
public interface ExamTrueAnswerMapper {
    long countByExample(ExamTrueAnswerExample example);

    int deleteByExample(ExamTrueAnswerExample example);

    int deleteByPrimaryKey(ExamTrueAnswerKey key);

    int insert(ExamTrueAnswer record);

    int insertSelective(ExamTrueAnswer record);

    List<ExamTrueAnswer> selectByExample(ExamTrueAnswerExample example);

    ExamTrueAnswer selectByPrimaryKey(ExamTrueAnswerKey key);

    int updateByExampleSelective(@Param("record") ExamTrueAnswer record, @Param("example") ExamTrueAnswerExample example);

    int updateByExample(@Param("record") ExamTrueAnswer record, @Param("example") ExamTrueAnswerExample example);

    int updateByPrimaryKeySelective(ExamTrueAnswer record);

    int updateByPrimaryKey(ExamTrueAnswer record);

}