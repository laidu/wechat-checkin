package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.SessionInfo;
import org.apache.ibatis.jdbc.SQL;

public class SessionInfoSqlProvider {

    public String insertSelective(SessionInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("session_info");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getSessionId() != null) {
            sql.VALUES("session_id", "#{sessionId,jdbcType=VARCHAR}");
        }
        
        if (record.getStartTime() != null) {
            sql.VALUES("start_time", "#{startTime,jdbcType=VARCHAR}");
        }
        
        if (record.getEndTime() != null) {
            sql.VALUES("end_time", "#{endTime,jdbcType=VARCHAR}");
        }
        
        if (record.getDay() != null) {
            sql.VALUES("day", "#{day,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SessionInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("session_info");
        
        if (record.getSessionId() != null) {
            sql.SET("session_id = #{sessionId,jdbcType=VARCHAR}");
        }
        
        if (record.getStartTime() != null) {
            sql.SET("start_time = #{startTime,jdbcType=VARCHAR}");
        }
        
        if (record.getEndTime() != null) {
            sql.SET("end_time = #{endTime,jdbcType=VARCHAR}");
        }
        
        if (record.getDay() != null) {
            sql.SET("day = #{day,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}