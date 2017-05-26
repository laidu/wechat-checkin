package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.StudentInfo;
import org.apache.ibatis.jdbc.SQL;

public class StudentInfoSqlProvider {

    public String insertSelective(StudentInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("student_info");
        
        if (record.getStudentId() != null) {
            sql.VALUES("student_id", "#{studentId,jdbcType=BIGINT}");
        }
        
        if (record.getStudentName() != null) {
            sql.VALUES("student_name", "#{studentName,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatId() != null) {
            sql.VALUES("wechat_id", "#{wechatId,jdbcType=VARCHAR}");
        }
        
        if (record.getClassId() != null) {
            sql.VALUES("class_id", "#{classId,jdbcType=VARCHAR}");
        }
        
        if (record.getFeaturePath() != null) {
            sql.VALUES("feature_path", "#{featurePath,jdbcType=VARCHAR}");
        }
        
        if (record.getFeatureType() != null) {
            sql.VALUES("feature_type", "#{featureType,jdbcType=SMALLINT}");
        }
        
        if (record.getRegisterTime() != null) {
            sql.VALUES("register_time", "#{registerTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(StudentInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("student_info");
        
        if (record.getStudentName() != null) {
            sql.SET("student_name = #{studentName,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatId() != null) {
            sql.SET("wechat_id = #{wechatId,jdbcType=VARCHAR}");
        }
        
        if (record.getClassId() != null) {
            sql.SET("class_id = #{classId,jdbcType=VARCHAR}");
        }
        
        if (record.getFeaturePath() != null) {
            sql.SET("feature_path = #{featurePath,jdbcType=VARCHAR}");
        }
        
        if (record.getFeatureType() != null) {
            sql.SET("feature_type = #{featureType,jdbcType=SMALLINT}");
        }
        
        if (record.getRegisterTime() != null) {
            sql.SET("register_time = #{registerTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("student_id = #{studentId,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}