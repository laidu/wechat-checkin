package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.CheckinDetailInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CheckinDetailInfoMapper {
    @Delete({
        "delete from checkin_detail_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into checkin_detail_info (id, student_id, ",
        "checkin_time, proof_path, ",
        "checkin_type, is_succ, ",
        "seq_id, checkin_result, ",
        "course_id)",
        "values (#{id,jdbcType=BIGINT}, #{studentId,jdbcType=BIGINT}, ",
        "#{checkinTime,jdbcType=TIMESTAMP}, #{proofPath,jdbcType=VARCHAR}, ",
        "#{checkinType,jdbcType=VARCHAR}, #{isSucc,jdbcType=BIT}, ",
        "#{seqId,jdbcType=BIGINT}, #{checkinResult,jdbcType=INTEGER}, ",
        "#{courseId,jdbcType=BIGINT})"
    })
    int insert(CheckinDetailInfo record);

    @InsertProvider(type=CheckinDetailInfoSqlProvider.class, method="insertSelective")
    int insertSelective(CheckinDetailInfo record);

    @Select({
        "select",
        "id, student_id, checkin_time, proof_path, checkin_type, is_succ, seq_id, checkin_result, ",
        "course_id",
        "from checkin_detail_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.BIGINT),
        @Result(column="checkin_time", property="checkinTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="proof_path", property="proofPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="checkin_type", property="checkinType", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_succ", property="isSucc", jdbcType=JdbcType.BIT),
        @Result(column="seq_id", property="seqId", jdbcType=JdbcType.BIGINT),
        @Result(column="checkin_result", property="checkinResult", jdbcType=JdbcType.INTEGER),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.BIGINT)
    })
    CheckinDetailInfo selectByPrimaryKey(Long id);

    @UpdateProvider(type=CheckinDetailInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CheckinDetailInfo record);

    @Update({
        "update checkin_detail_info",
        "set student_id = #{studentId,jdbcType=BIGINT},",
          "checkin_time = #{checkinTime,jdbcType=TIMESTAMP},",
          "proof_path = #{proofPath,jdbcType=VARCHAR},",
          "checkin_type = #{checkinType,jdbcType=VARCHAR},",
          "is_succ = #{isSucc,jdbcType=BIT},",
          "seq_id = #{seqId,jdbcType=BIGINT},",
          "checkin_result = #{checkinResult,jdbcType=INTEGER},",
          "course_id = #{courseId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CheckinDetailInfo record);
}