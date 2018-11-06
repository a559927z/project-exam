package com.ks.dao;

import com.ks.dto.ExamRollUserAnswer;
import com.ks.dto.ExamRollUserAnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamRollUserAnswerMapper {
    long countByExample(ExamRollUserAnswerExample example);

    int deleteByExample(ExamRollUserAnswerExample example);

    int deleteByPrimaryKey(String userAnswerId);

    int insert(ExamRollUserAnswer record);

    int insertSelective(ExamRollUserAnswer record);

    List<ExamRollUserAnswer> selectByExample(ExamRollUserAnswerExample example);

    ExamRollUserAnswer selectByPrimaryKey(String userAnswerId);

    int updateByExampleSelective(@Param("record") ExamRollUserAnswer record, @Param("example") ExamRollUserAnswerExample example);

    int updateByExample(@Param("record") ExamRollUserAnswer record, @Param("example") ExamRollUserAnswerExample example);

    int updateByPrimaryKeySelective(ExamRollUserAnswer record);

    int updateByPrimaryKey(ExamRollUserAnswer record);
}