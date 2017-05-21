package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.CourseInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CourseInfoMapper {
    @Delete({
        "delete from course_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into course_info (id, course_id, ",
        "course_name, teacher_id, ",
        "class_name, session_id, ",
        "week_day)",
        "values (#{id,jdbcType=BIGINT}, #{courseId,jdbcType=BIGINT}, ",
        "#{courseName,jdbcType=VARCHAR}, #{teacherId,jdbcType=BIGINT}, ",
        "#{className,jdbcType=VARCHAR}, #{sessionId,jdbcType=VARCHAR}, ",
        "#{weekDay,jdbcType=VARCHAR})"
    })
    int insert(CourseInfo record);

    @InsertProvider(type=CourseInfoSqlProvider.class, method="insertSelective")
    int insertSelective(CourseInfo record);

    @Select({
        "select",
        "id, course_id, course_name, teacher_id, class_name, session_id, week_day",
        "from course_info",
        "where id = #{id,jdbcType=BIGINT}"
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
    CourseInfo selectByPrimaryKey(Long id);

    @UpdateProvider(type=CourseInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CourseInfo record);

    @Update({
        "update course_info",
        "set course_id = #{courseId,jdbcType=BIGINT},",
          "course_name = #{courseName,jdbcType=VARCHAR},",
          "teacher_id = #{teacherId,jdbcType=BIGINT},",
          "class_name = #{className,jdbcType=VARCHAR},",
          "session_id = #{sessionId,jdbcType=VARCHAR},",
          "week_day = #{weekDay,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CourseInfo record);
}