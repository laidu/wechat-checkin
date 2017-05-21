package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.SessionInfo;
import org.apache.ibatis.jdbc.SQL;

public class SessionInfoSqlProvider {

    public String insertSelective(SessionInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("session_info");
        
        if (record.getSessionId() != null) {
            sql.VALUES("session_id", "#{sessionId,jdbcType=BIGINT}");
        }
        
        if (record.getSessionIndex() != null) {
            sql.VALUES("session_index", "#{sessionIndex,jdbcType=VARCHAR}");
        }
        
        if (record.getStartMin() != null) {
            sql.VALUES("start_min", "#{startMin,jdbcType=INTEGER}");
        }
        
        if (record.getStartHour() != null) {
            sql.VALUES("start_hour", "#{startHour,jdbcType=INTEGER}");
        }
        
        if (record.getEndHour() != null) {
            sql.VALUES("end_hour", "#{endHour,jdbcType=INTEGER}");
        }
        
        if (record.getEndMin() != null) {
            sql.VALUES("end_min", "#{endMin,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SessionInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("session_info");
        
        if (record.getSessionIndex() != null) {
            sql.SET("session_index = #{sessionIndex,jdbcType=VARCHAR}");
        }
        
        if (record.getStartMin() != null) {
            sql.SET("start_min = #{startMin,jdbcType=INTEGER}");
        }
        
        if (record.getStartHour() != null) {
            sql.SET("start_hour = #{startHour,jdbcType=INTEGER}");
        }
        
        if (record.getEndHour() != null) {
            sql.SET("end_hour = #{endHour,jdbcType=INTEGER}");
        }
        
        if (record.getEndMin() != null) {
            sql.SET("end_min = #{endMin,jdbcType=INTEGER}");
        }
        
        sql.WHERE("session_id = #{sessionId,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}