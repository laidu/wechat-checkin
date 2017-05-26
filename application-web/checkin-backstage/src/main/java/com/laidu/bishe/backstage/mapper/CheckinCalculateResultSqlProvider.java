package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.CheckinCalculateResult;
import org.apache.ibatis.jdbc.SQL;

public class CheckinCalculateResultSqlProvider {

    public String insertSelective(CheckinCalculateResult record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("checkin_calculate_result");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getTeacherId() != null) {
            sql.VALUES("teacher_id", "#{teacherId,jdbcType=BIGINT}");
        }
        
        if (record.getStudentId() != null) {
            sql.VALUES("student_id", "#{studentId,jdbcType=BIGINT}");
        }
        
        if (record.getCheckinResult() != null) {
            sql.VALUES("checkin_result", "#{checkinResult,jdbcType=INTEGER}");
        }
        
        if (record.getSeqId() != null) {
            sql.VALUES("seq_id", "#{seqId,jdbcType=BIGINT}");
        }
        
        if (record.getCourceId() != null) {
            sql.VALUES("cource_id", "#{courceId,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(CheckinCalculateResult record) {
        SQL sql = new SQL();
        sql.UPDATE("checkin_calculate_result");
        
        if (record.getTeacherId() != null) {
            sql.SET("teacher_id = #{teacherId,jdbcType=BIGINT}");
        }
        
        if (record.getStudentId() != null) {
            sql.SET("student_id = #{studentId,jdbcType=BIGINT}");
        }
        
        if (record.getCheckinResult() != null) {
            sql.SET("checkin_result = #{checkinResult,jdbcType=INTEGER}");
        }
        
        if (record.getSeqId() != null) {
            sql.SET("seq_id = #{seqId,jdbcType=BIGINT}");
        }
        
        if (record.getCourceId() != null) {
            sql.SET("cource_id = #{courceId,jdbcType=BIGINT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}