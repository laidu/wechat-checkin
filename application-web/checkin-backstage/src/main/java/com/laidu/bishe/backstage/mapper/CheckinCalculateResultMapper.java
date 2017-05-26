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
        "insert into checkin_calculate_result (id, teacher_id, ",
        "student_id, checkin_result, ",
        "seq_id, cource_id)",
        "values (#{id,jdbcType=BIGINT}, #{teacherId,jdbcType=BIGINT}, ",
        "#{studentId,jdbcType=BIGINT}, #{checkinResult,jdbcType=INTEGER}, ",
        "#{seqId,jdbcType=BIGINT}, #{courceId,jdbcType=BIGINT})"
    })
    int insert(CheckinCalculateResult record);

    @InsertProvider(type=CheckinCalculateResultSqlProvider.class, method="insertSelective")
    int insertSelective(CheckinCalculateResult record);

    @Select({
        "select",
        "id, teacher_id, student_id, checkin_result, seq_id, cource_id",
        "from checkin_calculate_result",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="teacher_id", property="teacherId", jdbcType=JdbcType.BIGINT),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.BIGINT),
        @Result(column="checkin_result", property="checkinResult", jdbcType=JdbcType.INTEGER),
        @Result(column="seq_id", property="seqId", jdbcType=JdbcType.BIGINT),
        @Result(column="cource_id", property="courceId", jdbcType=JdbcType.BIGINT)
    })
    CheckinCalculateResult selectByPrimaryKey(Long id);

    @UpdateProvider(type=CheckinCalculateResultSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CheckinCalculateResult record);

    @Update({
        "update checkin_calculate_result",
        "set teacher_id = #{teacherId,jdbcType=BIGINT},",
          "student_id = #{studentId,jdbcType=BIGINT},",
          "checkin_result = #{checkinResult,jdbcType=INTEGER},",
          "seq_id = #{seqId,jdbcType=BIGINT},",
          "cource_id = #{courceId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CheckinCalculateResult record);
}