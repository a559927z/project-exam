package com.ks.dao;

import com.ks.dto.ExamRollAnswer;
import com.ks.dto.ExamRollAnswerExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("examRollAnswerMapper")
public interface ExamRollAnswerMapper {
    long countByExample(ExamRollAnswerExample example);

    int deleteByExample(ExamRollAnswerExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExamRollAnswer record);

    int insertSelective(ExamRollAnswer record);

    List<ExamRollAnswer> selectByExample(ExamRollAnswerExample example);

    ExamRollAnswer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExamRollAnswer record, @Param("example") ExamRollAnswerExample example);

    int updateByExample(@Param("record") ExamRollAnswer record, @Param("example") ExamRollAnswerExample example);

    int updateByPrimaryKeySelective(ExamRollAnswer record);

    int updateByPrimaryKey(ExamRollAnswer record);
}