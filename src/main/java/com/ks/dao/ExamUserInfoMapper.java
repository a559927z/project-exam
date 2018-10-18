package com.ks.dao;

import com.ks.dto.ExamUserInfo;
import com.ks.dto.ExamUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamUserInfoMapper {
    long countByExample(ExamUserInfoExample example);

    int deleteByExample(ExamUserInfoExample example);

    int deleteByPrimaryKey(String account);

    int insert(ExamUserInfo record);

    int insertSelective(ExamUserInfo record);

    List<ExamUserInfo> selectByExample(ExamUserInfoExample example);

    ExamUserInfo selectByPrimaryKey(String account);

    int updateByExampleSelective(@Param("record") ExamUserInfo record, @Param("example") ExamUserInfoExample example);

    int updateByExample(@Param("record") ExamUserInfo record, @Param("example") ExamUserInfoExample example);

    int updateByPrimaryKeySelective(ExamUserInfo record);

    int updateByPrimaryKey(ExamUserInfo record);
}