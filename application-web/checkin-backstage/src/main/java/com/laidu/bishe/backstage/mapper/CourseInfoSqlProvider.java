package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.CourseInfo;
import org.apache.ibatis.jdbc.SQL;

public class CourseInfoSqlProvider {

    public String insertSelective(CourseInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("course_info");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
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
        
        if (record.getSessionId() != null) {
            sql.VALUES("session_id", "#{sessionId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(CourseInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("course_info");
        
        if (record.getCourseId() != null) {
            sql.SET("course_id = #{courseId,jdbcType=BIGINT}");
        }
        
        if (record.getCourseName() != null) {
            sql.SET("course_name = #{courseName,jdbcType=VARCHAR}");
        }
        
        if (record.getTeacherId() != null) {
            sql.SET("teacher_id = #{teacherId,jdbcType=BIGINT}");
        }
        
        if (record.getClassName() != null) {
            sql.SET("class_name = #{className,jdbcType=VARCHAR}");
        }
        
        if (record.getSessionId() != null) {
            sql.SET("session_id = #{sessionId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}