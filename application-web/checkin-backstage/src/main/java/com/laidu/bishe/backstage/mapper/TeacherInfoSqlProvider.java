package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.TeacherInfo;
import org.apache.ibatis.jdbc.SQL;

public class TeacherInfoSqlProvider {

    public String insertSelective(TeacherInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("teacher_info");
        
        if (record.getTeacherId() != null) {
            sql.VALUES("teacher_id", "#{teacherId,jdbcType=BIGINT}");
        }
        
        if (record.getTeacherName() != null) {
            sql.VALUES("teacher_name", "#{teacherName,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatId() != null) {
            sql.VALUES("wechat_id", "#{wechatId,jdbcType=VARCHAR}");
        }
        
        if (record.getRegisterTime() != null) {
            sql.VALUES("register_time", "#{registerTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TeacherInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("teacher_info");
        
        if (record.getTeacherName() != null) {
            sql.SET("teacher_name = #{teacherName,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatId() != null) {
            sql.SET("wechat_id = #{wechatId,jdbcType=VARCHAR}");
        }
        
        if (record.getRegisterTime() != null) {
            sql.SET("register_time = #{registerTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("teacher_id = #{teacherId,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}