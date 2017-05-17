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
        "insert into checkin_detail_info (id, stu_id, ",
        "checkin_time, proof_path, ",
        "checkin_type, is_succ, ",
        "checkin_result, course_id, ",
        "seq_id)",
        "values (#{id,jdbcType=BIGINT}, #{stuId,jdbcType=BIGINT}, ",
        "#{checkinTime,jdbcType=TIMESTAMP}, #{proofPath,jdbcType=VARCHAR}, ",
        "#{checkinType,jdbcType=SMALLINT}, #{isSucc,jdbcType=BIT}, ",
        "#{checkinResult,jdbcType=SMALLINT}, #{courseId,jdbcType=BIGINT}, ",
        "#{seqId,jdbcType=BIGINT})"
    })
    int insert(CheckinDetailInfo record);

    @InsertProvider(type=CheckinDetailInfoSqlProvider.class, method="insertSelective")
    int insertSelective(CheckinDetailInfo record);

    @Select({
        "select",
        "id, stu_id, checkin_time, proof_path, checkin_type, is_succ, checkin_result, ",
        "course_id, seq_id",
        "from checkin_detail_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="stu_id", property="stuId", jdbcType=JdbcType.BIGINT),
        @Result(column="checkin_time", property="checkinTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="proof_path", property="proofPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="checkin_type", property="checkinType", jdbcType=JdbcType.SMALLINT),
        @Result(column="is_succ", property="isSucc", jdbcType=JdbcType.BIT),
        @Result(column="checkin_result", property="checkinResult", jdbcType=JdbcType.SMALLINT),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.BIGINT),
        @Result(column="seq_id", property="seqId", jdbcType=JdbcType.BIGINT)
    })
    CheckinDetailInfo selectByPrimaryKey(Long id);

    @UpdateProvider(type=CheckinDetailInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CheckinDetailInfo record);

    @Update({
        "update checkin_detail_info",
        "set stu_id = #{stuId,jdbcType=BIGINT},",
          "checkin_time = #{checkinTime,jdbcType=TIMESTAMP},",
          "proof_path = #{proofPath,jdbcType=VARCHAR},",
          "checkin_type = #{checkinType,jdbcType=SMALLINT},",
          "is_succ = #{isSucc,jdbcType=BIT},",
          "checkin_result = #{checkinResult,jdbcType=SMALLINT},",
          "course_id = #{courseId,jdbcType=BIGINT},",
          "seq_id = #{seqId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CheckinDetailInfo record);
}