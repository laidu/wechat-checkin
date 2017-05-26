package com.laidu.bishe.backstage.mapper.custom;

import com.laidu.bishe.backstage.domain.CheckinDetailInfo;
import com.laidu.bishe.backstage.domain.StudentInfo;
import com.laidu.bishe.backstage.mapper.CheckinDetailInfoMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 考勤详细结果扩展
 * Created by laidu on 2017/5/22.
 */
public interface CheckinDetailCustInfoMapper extends CheckinDetailInfoMapper {

    /**
     * 获取某次考勤的某个状态的学生列表
     * @param seqId
     * @param checkinStatus
     * @return
     */
    @Select({
            "select",
            "stu_id, checkin_time, proof_path, checkin_type, is_succ, checkin_result, ",
            "course_id, seq_id",
            "from checkin_detail_info",
            "where seq_id = #{arg0,jdbcType=BIGINT} and checkin_result = #{arg1,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="stu_id", property="studentId", jdbcType=JdbcType.BIGINT),
            @Result(column="checkin_time", property="checkinTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="proof_path", property="proofPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="checkin_type", property="checkinType", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_succ", property="isSucc", jdbcType=JdbcType.BIT),
            @Result(column="checkin_result", property="checkinResult", jdbcType=JdbcType.VARCHAR),
            @Result(column="course_id", property="courseId", jdbcType=JdbcType.BIGINT),
            @Result(column="seq_id", property="seqId", jdbcType=JdbcType.BIGINT)
    })
    List<CheckinDetailInfo> selectStudentBySeqIdAndStatus(Long seqId, String checkinStatus);


    /**
     * 通过考勤次序号和学号获取学生列表
     * @param studentId
     * @param seqId
     * @return
     */
    @Select({
            "select",
            "checkin_result, ",
            "course_id",
            "from checkin_detail_info",
            "where student_id = #{arg0,jdbcType=BIGINT} and course_id = #{arg1,jdbcType=BIGINT} and seq_id = #{arg2,jdbcType=BIGINT}"
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
    List<CheckinDetailInfo> selectStudentByStudentIdAndSeqId(Long studentId ,Long courseId ,Long seqId);
}
