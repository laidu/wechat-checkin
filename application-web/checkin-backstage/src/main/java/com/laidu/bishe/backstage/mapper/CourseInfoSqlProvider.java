package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.CourseInfo;
import org.apache.ibatis.jdbc.SQL;

public class CourseInfoSqlProvider {

    public String insertSelective(CourseInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("course_info");
        
        if (record.getCourseId() != null) {
            sql.VALUES("course_id", "#{courseId,jdbcType=BIGINT}");
        }
        
        if (record.getCourseName() != null) {
            sql.VALUES("course_name", "#{courseName,jdbcType=VARCHAR}");
        }
        
        if (record.getTeacherId() != null) {
            sql.VALUES("teacher_id", "#{teacherId,jdbcType=BIGINT}");
        }
        
        if (record.getClassName() != null) {
            sql.VALUES("class_name", "#{className,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }
}