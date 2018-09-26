package com.ks.dao;

import com.github.pagehelper.Page;
import com.ks.dto.ExamQuestionBank;
import com.ks.dto.PublicUserInfo;
import com.ks.dto.PublicUserInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("publicUserInfoMapperExt")
public interface PublicUserInfoMapperExt {

    /**
     * 分页
     *
     * @return
     */
    Page<PublicUserInfo> queryByPage();
}