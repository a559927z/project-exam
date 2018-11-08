package com.ks.dao;

import com.ks.dto.ExamRollUser;
import com.ks.dto.ExamRollUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("examRollUserMapper")
public interface ExamRollUserMapper {
    long countByExample(ExamRollUserExample example);

    int deleteByExample(ExamRollUserExample example);

    int deleteByPrimaryKey(String rollId);

    int insert(ExamRollUser record);

    int insertSelective(ExamRollUser record);

    List<ExamRollUser> selectByExample(ExamRollUserExample example);

    ExamRollUser selectByPrimaryKey(String rollId);

    int updateByExampleSelective(@Param("record") ExamRollUser record, @Param("example") ExamRollUserExample example);

    int updateByExample(@Param("record") ExamRollUser record, @Param("example") ExamRollUserExample example);

    int updateByPrimaryKeySelective(ExamRollUser record);

    int updateByPrimaryKey(ExamRollUser record);
}