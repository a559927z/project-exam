package com.ks.dao;

import com.ks.dto.ExamRoll;
import com.ks.dto.ExamRollExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ExamRollMapper {
    @SelectProvider(type=ExamRollSqlProvider.class, method="countByExample")
    long countByExample(ExamRollExample example);

    @DeleteProvider(type=ExamRollSqlProvider.class, method="deleteByExample")
    int deleteByExample(ExamRollExample example);

    @Delete({
        "delete from exam_roll",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into exam_roll (id, roll_id, ",
        "question_bank_id)",
        "values (#{id,jdbcType=INTEGER}, #{rollId,jdbcType=VARCHAR}, ",
        "#{questionBankId,jdbcType=VARCHAR})"
    })
    int insert(ExamRoll record);

    @InsertProvider(type=ExamRollSqlProvider.class, method="insertSelective")
    int insertSelective(ExamRoll record);

    @SelectProvider(type=ExamRollSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="roll_id", property="rollId", jdbcType=JdbcType.VARCHAR),
        @Result(column="question_bank_id", property="questionBankId", jdbcType=JdbcType.VARCHAR)
    })
    List<ExamRoll> selectByExample(ExamRollExample example);

    @Select({
        "select",
        "id, roll_id, question_bank_id",
        "from exam_roll",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="roll_id", property="rollId", jdbcType=JdbcType.VARCHAR),
        @Result(column="question_bank_id", property="questionBankId", jdbcType=JdbcType.VARCHAR)
    })
    ExamRoll selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ExamRollSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ExamRoll record, @Param("example") ExamRollExample example);

    @UpdateProvider(type=ExamRollSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ExamRoll record, @Param("example") ExamRollExample example);

    @UpdateProvider(type=ExamRollSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ExamRoll record);

    @Update({
        "update exam_roll",
        "set roll_id = #{rollId,jdbcType=VARCHAR},",
          "question_bank_id = #{questionBankId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ExamRoll record);
}