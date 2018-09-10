package com.ks.dao;

import com.ks.dto.ExamQuestionBank;
import com.ks.dto.ExamQuestionBankExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface ExamQuestionBankMapper {
    @SelectProvider(type=ExamQuestionBankSqlProvider.class, method="countByExample")
    long countByExample(ExamQuestionBankExample example);

    @DeleteProvider(type=ExamQuestionBankSqlProvider.class, method="deleteByExample")
    int deleteByExample(ExamQuestionBankExample example);

    @Delete({
        "delete from exam_question_bank",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into exam_question_bank (id, title, ",
        "answer, true_answer, ",
        "jie_xi, note, question_bank_id, ",
        "question_bank_name, category_id, ",
        "course_id, type, ",
        "is_lock, created_by, ",
        "created_date, updated_by, ",
        "updated_date)",
        "values (#{id,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{answer,jdbcType=VARCHAR}, #{trueAnswer,jdbcType=VARCHAR}, ",
        "#{jieXi,jdbcType=VARCHAR}, #{note,jdbcType=VARCHAR}, #{questionBankId,jdbcType=VARCHAR}, ",
        "#{questionBankName,jdbcType=VARCHAR}, #{categoryId,jdbcType=VARCHAR}, ",
        "#{courseId,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, ",
        "#{isLock,jdbcType=INTEGER}, #{createdBy,jdbcType=VARCHAR}, ",
        "#{createdDate,jdbcType=TIMESTAMP}, #{updatedBy,jdbcType=VARCHAR}, ",
        "#{updatedDate,jdbcType=TIMESTAMP})"
    })
    int insert(ExamQuestionBank record);

    @InsertProvider(type=ExamQuestionBankSqlProvider.class, method="insertSelective")
    int insertSelective(ExamQuestionBank record);

    @SelectProvider(type=ExamQuestionBankSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="answer", property="answer", jdbcType=JdbcType.VARCHAR),
        @Result(column="true_answer", property="trueAnswer", jdbcType=JdbcType.VARCHAR),
        @Result(column="jie_xi", property="jieXi", jdbcType=JdbcType.VARCHAR),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="question_bank_id", property="questionBankId", jdbcType=JdbcType.VARCHAR),
        @Result(column="question_bank_name", property="questionBankName", jdbcType=JdbcType.VARCHAR),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_lock", property="isLock", jdbcType=JdbcType.INTEGER),
        @Result(column="created_by", property="createdBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_by", property="updatedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="updated_date", property="updatedDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ExamQuestionBank> selectByExample(ExamQuestionBankExample example);

    @Select({
        "select",
        "id, title, answer, true_answer, jie_xi, note, question_bank_id, question_bank_name, ",
        "category_id, course_id, type, is_lock, created_by, created_date, updated_by, ",
        "updated_date",
        "from exam_question_bank",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="answer", property="answer", jdbcType=JdbcType.VARCHAR),
        @Result(column="true_answer", property="trueAnswer", jdbcType=JdbcType.VARCHAR),
        @Result(column="jie_xi", property="jieXi", jdbcType=JdbcType.VARCHAR),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="question_bank_id", property="questionBankId", jdbcType=JdbcType.VARCHAR),
        @Result(column="question_bank_name", property="questionBankName", jdbcType=JdbcType.VARCHAR),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_lock", property="isLock", jdbcType=JdbcType.INTEGER),
        @Result(column="created_by", property="createdBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_by", property="updatedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="updated_date", property="updatedDate", jdbcType=JdbcType.TIMESTAMP)
    })
    ExamQuestionBank selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ExamQuestionBankSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ExamQuestionBank record, @Param("example") ExamQuestionBankExample example);

    @UpdateProvider(type=ExamQuestionBankSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ExamQuestionBank record, @Param("example") ExamQuestionBankExample example);

    @UpdateProvider(type=ExamQuestionBankSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ExamQuestionBank record);

    @Update({
        "update exam_question_bank",
        "set title = #{title,jdbcType=VARCHAR},",
          "answer = #{answer,jdbcType=VARCHAR},",
          "true_answer = #{trueAnswer,jdbcType=VARCHAR},",
          "jie_xi = #{jieXi,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR},",
          "question_bank_id = #{questionBankId,jdbcType=VARCHAR},",
          "question_bank_name = #{questionBankName,jdbcType=VARCHAR},",
          "category_id = #{categoryId,jdbcType=VARCHAR},",
          "course_id = #{courseId,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "is_lock = #{isLock,jdbcType=INTEGER},",
          "created_by = #{createdBy,jdbcType=VARCHAR},",
          "created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "updated_by = #{updatedBy,jdbcType=VARCHAR},",
          "updated_date = #{updatedDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ExamQuestionBank record);
}