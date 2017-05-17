package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.StudentInfo;
import org.apache.ibatis.jdbc.SQL;

public class StudentInfoSqlProvider {

    public String insertSelective(StudentInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("student_info");
        
        if (record.getStuId() != null) {
            sql.VALUES("stu_id", "#{stuId,jdbcType=BIGINT}");
        }
        
        if (record.getStuName() != null) {
            sql.VALUES("stu_name", "#{stuName,jdbcType=VARCHAR}");
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
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(StudentInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("student_info");
        
        if (record.getStuName() != null) {
            sql.SET("stu_name = #{stuName,jdbcType=VARCHAR}");
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
        
        sql.WHERE("stu_id = #{stuId,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}