package com.ks.dao;

import com.ks.dto.ExamRoll;
import com.ks.dto.ExamRollExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamRollMapper {
    long countByExample(ExamRollExample example);

    int deleteByExample(ExamRollExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamRoll record);

    int insertSelective(ExamRoll record);

    List<ExamRoll> selectByExample(ExamRollExample example);

    ExamRoll selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamRoll record, @Param("example") ExamRollExample example);

    int updateByExample(@Param("record") ExamRoll record, @Param("example") ExamRollExample example);

    int updateByPrimaryKeySelective(ExamRoll record);

    int updateByPrimaryKey(ExamRoll record);
}