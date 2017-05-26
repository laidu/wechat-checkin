package com.laidu.bishe.backstage.mapper.custom;

import com.laidu.bishe.backstage.domain.CourseInfo;
import com.laidu.bishe.backstage.mapper.CourseInfoMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 自定义CourseInfoMapper，扩展mybatis-generator生成的mapper功能
 * Created by laidu on 2017/5/21.
 */
public interface CourseInfoCustMapper extends CourseInfoMapper {

    /**
     * 通过教师工号和节次信息获取课程信息
     * @param teacherId
     * @param sessionId
     * @return
     */
    @Select({
            "select",
            "id, course_id, course_name, teacher_id, class_name, session_id",
            "from course_info",
            "where teacher_id = #{arg0,jdbcType=BIGINT} " +
                    "and session_id = #{arg1,jdbcType=VARCHAR} " +
                    "and week_day = #{arg2,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="course_id", property="courseId", jdbcType=JdbcType.BIGINT),
            @Result(column="course_name", property="courseName", jdbcType=JdbcType.VARCHAR),
            @Result(column="teacher_id", property="teacherId", jdbcType=JdbcType.BIGINT),
            @Result(column="class_name", property="className", jdbcType=JdbcType.VARCHAR),
            @Result(column="session_id", property="sessionId", jdbcType=JdbcType.VARCHAR),
            @Result(column="week_day", property="weekDay", jdbcType=JdbcType.VARCHAR)
    })
    List<CourseInfo> selectByTeaSesId(Long teacherId, String sessionId, String day);

    /**
     * 获取指定教师得所有课程
     * @param teacherId
     * @return
     */
    @Select({
            "select",
            "id, course_id, course_name, teacher_id, class_name, session_id",
            "from course_info",
            "where teacher_id = #{arg0,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="course_id", property="courseId", jdbcType=JdbcType.BIGINT),
            @Result(column="course_name", property="courseName", jdbcType=JdbcType.VARCHAR),
            @Result(column="teacher_id", property="teacherId", jdbcType=JdbcType.BIGINT),
            @Result(column="class_name", property="className", jdbcType=JdbcType.VARCHAR),
            @Result(column="session_id", property="sessionId", jdbcType=JdbcType.VARCHAR),
            @Result(column="week_day", property="weekDay", jdbcType=JdbcType.VARCHAR)
    })
    List<CourseInfo> selectByTeaId(Long teacherId);
}
