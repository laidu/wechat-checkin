package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.WechatUserInfo;
import org.apache.ibatis.jdbc.SQL;

public class WechatUserInfoSqlProvider {

    public String insertSelective(WechatUserInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("wechat_user_info");
        
        if (record.getWechatId() != null) {
            sql.VALUES("wechat_id", "#{wechatId,jdbcType=VARCHAR}");
        }
        
        if (record.getUserInfoId() != null) {
            sql.VALUES("user_info_id", "#{userInfoId,jdbcType=VARCHAR}");
        }
        
        if (record.getUserType() != null) {
            sql.VALUES("user_type", "#{userType,jdbcType=INTEGER}");
        }
        
        if (record.getFollowStatus() != null) {
            sql.VALUES("follow_status", "#{followStatus,jdbcType=INTEGER}");
        }
        
        if (record.getWxUserInfo() != null) {
            sql.VALUES("wx_user_info", "#{wxUserInfo,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(WechatUserInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("wechat_user_info");
        
        if (record.getUserInfoId() != null) {
            sql.SET("user_info_id = #{userInfoId,jdbcType=VARCHAR}");
        }
        
        if (record.getUserType() != null) {
            sql.SET("user_type = #{userType,jdbcType=INTEGER}");
        }
        
        if (record.getFollowStatus() != null) {
            sql.SET("follow_status = #{followStatus,jdbcType=INTEGER}");
        }
        
        if (record.getWxUserInfo() != null) {
            sql.SET("wx_user_info = #{wxUserInfo,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("wechat_id = #{wechatId,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}