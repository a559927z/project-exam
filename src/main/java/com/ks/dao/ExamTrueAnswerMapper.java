package com.ks.dao;

import com.ks.dto.ExamTrueAnswer;
import com.ks.dto.ExamTrueAnswerExample;
import com.ks.dto.ExamTrueAnswerKey;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("examTrueAnswerMapper")
public interface ExamTrueAnswerMapper {
    @SelectProvider(type=ExamTrueAnswerSqlProvider.class, method="countByExample")
    long countByExample(ExamTrueAnswerExample example);

    @DeleteProvider(type=ExamTrueAnswerSqlProvider.class, method="deleteByExample")
    int deleteByExample(ExamTrueAnswerExample example);

    @Delete({
        "delete from exam_true_answer",
        "where question_bank_id = #{questionBankId,jdbcType=VARCHAR}",
          "and true_answer_id = #{trueAnswerId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(ExamTrueAnswerKey key);

    @Insert({
        "insert into exam_true_answer (question_bank_id, true_answer_id, ",
        "true_answer, sort)",
        "values (#{questionBankId,jdbcType=VARCHAR}, #{trueAnswerId,jdbcType=VARCHAR}, ",
        "#{trueAnswer,jdbcType=VARCHAR}, #{sort,jdbcType=INTEGER})"
    })
    int insert(ExamTrueAnswer record);

    @InsertProvider(type=ExamTrueAnswerSqlProvider.class, method="insertSelective")
    int insertSelective(ExamTrueAnswer record);

    @SelectProvider(type=ExamTrueAnswerSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="question_bank_id", property="questionBankId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="true_answer_id", property="trueAnswerId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="true_answer", property="trueAnswer", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<ExamTrueAnswer> selectByExample(ExamTrueAnswerExample example);

    @Select({
        "select",
        "question_bank_id, true_answer_id, true_answer, sort",
        "from exam_true_answer",
        "where question_bank_id = #{questionBankId,jdbcType=VARCHAR}",
          "and true_answer_id = #{trueAnswerId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="question_bank_id", property="questionBankId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="true_answer_id", property="trueAnswerId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="true_answer", property="trueAnswer", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    ExamTrueAnswer selectByPrimaryKey(ExamTrueAnswerKey key);

    @UpdateProvider(type=ExamTrueAnswerSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ExamTrueAnswer record, @Param("example") ExamTrueAnswerExample example);

    @UpdateProvider(type=ExamTrueAnswerSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ExamTrueAnswer record, @Param("example") ExamTrueAnswerExample example);

    @UpdateProvider(type=ExamTrueAnswerSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ExamTrueAnswer record);

    @Update({
        "update exam_true_answer",
        "set true_answer = #{trueAnswer,jdbcType=VARCHAR},",
          "sort = #{sort,jdbcType=INTEGER}",
        "where question_bank_id = #{questionBankId,jdbcType=VARCHAR}",
          "and true_answer_id = #{trueAnswerId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(ExamTrueAnswer record);
}