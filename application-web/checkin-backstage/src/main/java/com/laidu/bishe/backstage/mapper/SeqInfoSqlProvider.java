package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.SeqInfo;
import org.apache.ibatis.jdbc.SQL;

public class SeqInfoSqlProvider {

    public String insertSelective(SeqInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("seq_info");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getTeacherId() != null) {
            sql.VALUES("teacher_id", "#{teacherId,jdbcType=BIGINT}");
        }
        
        if (record.getCourseId() != null) {
            sql.VALUES("course_id", "#{courseId,jdbcType=BIGINT}");
        }
        
        if (record.getStartTime() != null) {
            sql.VALUES("start_time", "#{startTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getClassIds() != null) {
            sql.VALUES("class_ids", "#{classIds,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SeqInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("seq_info");
        
        if (record.getTeacherId() != null) {
            sql.SET("teacher_id = #{teacherId,jdbcType=BIGINT}");
        }
        
        if (record.getCourseId() != null) {
            sql.SET("course_id = #{courseId,jdbcType=BIGINT}");
        }
        
        if (record.getStartTime() != null) {
            sql.SET("start_time = #{startTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getClassIds() != null) {
            sql.SET("class_ids = #{classIds,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}