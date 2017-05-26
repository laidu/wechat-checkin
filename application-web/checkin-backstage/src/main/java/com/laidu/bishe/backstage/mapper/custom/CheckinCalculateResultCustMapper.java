package com.laidu.bishe.backstage.mapper.custom;

import com.laidu.bishe.backstage.domain.CheckinCalculateResult;
import com.laidu.bishe.backstage.mapper.CheckinCalculateResultMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 考勤结果扩展mapper
 * Created by laidu on 2017/5/25.
 */
public interface CheckinCalculateResultCustMapper extends CheckinCalculateResultMapper {


    @Select({
            "select",
            "teacher_id, student_id, checkin_result, seq_id, cource_id",
            "from checkin_calculate_result",
            "where student_id = #{arg0,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="teacher_id", property="teacherId", jdbcType=JdbcType.BIGINT),
            @Result(column="student_id", property="studentId", jdbcType=JdbcType.BIGINT),
            @Result(column="checkin_result", property="checkinResult", jdbcType=JdbcType.INTEGER),
            @Result(column="seq_id", property="seqId", jdbcType=JdbcType.BIGINT),
            @Result(column="cource_id", property="courceId", jdbcType=JdbcType.BIGINT)
    })
    List<CheckinCalculateResult> selectByStudentId(Long studentId);
}
