package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.CourseInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;

public interface CourseInfoMapper {
    @Insert({
        "insert into course_info (course_id, course_name, ",
        "teacher_id, class_name)",
        "values (#{courseId,jdbcType=BIGINT}, #{courseName,jdbcType=VARCHAR}, ",
        "#{teacherId,jdbcType=BIGINT}, #{className,jdbcType=VARCHAR})"
    })
    int insert(CourseInfo record);

    @InsertProvider(type=CourseInfoSqlProvider.class, method="insertSelective")
    int insertSelective(CourseInfo record);
}