package com.ks.dao;

import com.ks.dto.ExamRollUser;
import com.ks.dto.ExamRollUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("examRollUserMapperExt")
public interface ExamRollUserMapperExt {

    List<ExamRollUser> queryRecordByUserId(String userId);

}