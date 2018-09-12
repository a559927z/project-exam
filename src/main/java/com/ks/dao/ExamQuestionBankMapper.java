package com.ks.dao;

import com.github.pagehelper.Page;
import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankExample;

import java.util.List;

import com.ks.dto.ExamQuestionBankTotal;
import com.ks.dto.ExamTrueAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("examQuestionBankMapper")
public interface ExamQuestionBankMapper {
    long countByExample(ExamQuestionBankExample example);

    int deleteByExample(ExamQuestionBankExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamQuestionBank record);

    int insertSelective(ExamQuestionBank record);

    List<ExamQuestionBank> selectByExample(ExamQuestionBankExample example);

    ExamQuestionBank selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamQuestionBank record, @Param("example") ExamQuestionBankExample example);

    int updateByExample(@Param("record") ExamQuestionBank record, @Param("example") ExamQuestionBankExample example);

    int updateByPrimaryKeySelective(ExamQuestionBank record);

    int updateByPrimaryKey(ExamQuestionBank record);

    /**
     * 分页查询数据
     *
     * @return
     */
    Page<ExamQuestionBank> findByPage(String questionBankId);

    /**
     * 题库的总题数
     *
     * @return
     */
    List<ExamQuestionBankTotal> queryTotal();


}