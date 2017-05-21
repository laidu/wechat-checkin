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
        
        if (record.getCourceId() != null) {
            sql.VALUES("cource_id", "#{courceId,jdbcType=VARCHAR}");
        }
        
        if (record.getTeacherId() != null) {
            sql.VALUES("teacher_id", "#{teacherId,jdbcType=BIGINT}");
        }
        
        if (record.getSeqStart() != null) {
            sql.VALUES("seq_start", "#{seqStart,jdbcType=BIGINT}");
        }
        
        if (record.getSeqEnd() != null) {
            sql.VALUES("seq_end", "#{seqEnd,jdbcType=BIGINT}");
        }
        
        if (record.getResult() != null) {
            sql.VALUES("result", "#{result,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(CheckinCalculateResult record) {
        SQL sql = new SQL();
        sql.UPDATE("checkin_calculate_result");
        
        if (record.getCourceId() != null) {
            sql.SET("cource_id = #{courceId,jdbcType=VARCHAR}");
        }
        
        if (record.getTeacherId() != null) {
            sql.SET("teacher_id = #{teacherId,jdbcType=BIGINT}");
        }
        
        if (record.getSeqStart() != null) {
            sql.SET("seq_start = #{seqStart,jdbcType=BIGINT}");
        }
        
        if (record.getSeqEnd() != null) {
            sql.SET("seq_end = #{seqEnd,jdbcType=BIGINT}");
        }
        
        if (record.getResult() != null) {
            sql.SET("result = #{result,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}