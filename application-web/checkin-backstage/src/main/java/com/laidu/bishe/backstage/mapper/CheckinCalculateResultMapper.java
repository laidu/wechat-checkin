package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.CheckinCalculateResult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CheckinCalculateResultMapper {
    @Delete({
        "delete from checkin_calculate_result",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into checkin_calculate_result (id, cource_id, ",
        "teacher_id, seq_start, ",
        "seq_end, result)",
        "values (#{id,jdbcType=BIGINT}, #{courceId,jdbcType=VARCHAR}, ",
        "#{teacherId,jdbcType=BIGINT}, #{seqStart,jdbcType=BIGINT}, ",
        "#{seqEnd,jdbcType=BIGINT}, #{result,jdbcType=OTHER})"
    })
    int insert(CheckinCalculateResult record);

    @InsertProvider(type=CheckinCalculateResultSqlProvider.class, method="insertSelective")
    int insertSelective(CheckinCalculateResult record);

    @Select({
        "select",
        "id, cource_id, teacher_id, seq_start, seq_end, result",
        "from checkin_calculate_result",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="cource_id", property="courceId", jdbcType=JdbcType.VARCHAR),
        @Result(column="teacher_id", property="teacherId", jdbcType=JdbcType.BIGINT),
        @Result(column="seq_start", property="seqStart", jdbcType=JdbcType.BIGINT),
        @Result(column="seq_end", property="seqEnd", jdbcType=JdbcType.BIGINT),
        @Result(column="result", property="result", jdbcType=JdbcType.OTHER)
    })
    CheckinCalculateResult selectByPrimaryKey(Long id);

    @UpdateProvider(type=CheckinCalculateResultSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CheckinCalculateResult record);

    @Update({
        "update checkin_calculate_result",
        "set cource_id = #{courceId,jdbcType=VARCHAR},",
          "teacher_id = #{teacherId,jdbcType=BIGINT},",
          "seq_start = #{seqStart,jdbcType=BIGINT},",
          "seq_end = #{seqEnd,jdbcType=BIGINT},",
          "result = #{result,jdbcType=OTHER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CheckinCalculateResult record);
}