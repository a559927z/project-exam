package com.ks.dao;

import com.ks.dto.PublicLogin;
import com.ks.dto.PublicLoginExample;
import com.ks.dto.PublicLoginKey;
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

public interface PublicLoginMapper {
    @SelectProvider(type=PublicLoginSqlProvider.class, method="countByExample")
    long countByExample(PublicLoginExample example);

    @DeleteProvider(type=PublicLoginSqlProvider.class, method="deleteByExample")
    int deleteByExample(PublicLoginExample example);

    @Delete({
        "delete from public_login",
        "where customer_id = #{customerId,jdbcType=VARCHAR}",
          "and access_id = #{accessId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(PublicLoginKey key);

    @Insert({
        "insert into public_login (customer_id, access_id, ",
        "password, is_lock)",
        "values (#{customerId,jdbcType=VARCHAR}, #{accessId,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{isLock,jdbcType=BIT})"
    })
    int insert(PublicLogin record);

    @InsertProvider(type=PublicLoginSqlProvider.class, method="insertSelective")
    int insertSelective(PublicLogin record);

    @SelectProvider(type=PublicLoginSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="access_id", property="accessId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_lock", property="isLock", jdbcType=JdbcType.BIT)
    })
    List<PublicLogin> selectByExample(PublicLoginExample example);

    @Select({
        "select",
        "customer_id, access_id, password, is_lock",
        "from public_login",
        "where customer_id = #{customerId,jdbcType=VARCHAR}",
          "and access_id = #{accessId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="access_id", property="accessId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_lock", property="isLock", jdbcType=JdbcType.BIT)
    })
    PublicLogin selectByPrimaryKey(PublicLoginKey key);

    @UpdateProvider(type=PublicLoginSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PublicLogin record, @Param("example") PublicLoginExample example);

    @UpdateProvider(type=PublicLoginSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") PublicLogin record, @Param("example") PublicLoginExample example);

    @UpdateProvider(type=PublicLoginSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PublicLogin record);

    @Update({
        "update public_login",
        "set password = #{password,jdbcType=VARCHAR},",
          "is_lock = #{isLock,jdbcType=BIT}",
        "where customer_id = #{customerId,jdbcType=VARCHAR}",
          "and access_id = #{accessId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(PublicLogin record);
}