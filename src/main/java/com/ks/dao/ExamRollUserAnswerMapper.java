package com.ks.dao;

import com.ks.dto.ExamRollUserAnswer;
import com.ks.dto.ExamRollUserAnswerExample;
import com.ks.dto.ExamRollUserAnswerKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("examRollUserAnswerMapper")
public interface ExamRollUserAnswerMapper {
    long countByExample(ExamRollUserAnswerExample example);

    int deleteByExample(ExamRollUserAnswerExample example);

    int deleteByPrimaryKey(ExamRollUserAnswerKey key);

    int insert(ExamRollUserAnswer record);

    int insertSelective(ExamRollUserAnswer record);

    List<ExamRollUserAnswer> selectByExample(ExamRollUserAnswerExample example);

    ExamRollUserAnswer selectByPrimaryKey(ExamRollUserAnswerKey key);

    int updateByExampleSelective(@Param("record") ExamRollUserAnswer record, @Param("example") ExamRollUserAnswerExample example);

    int updateByExample(@Param("record") ExamRollUserAnswer record, @Param("example") ExamRollUserAnswerExample example);

    int updateByPrimaryKeySelective(ExamRollUserAnswer record);

    int updateByPrimaryKey(ExamRollUserAnswer record);
}