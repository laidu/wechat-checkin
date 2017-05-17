package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.WechatTagInfo;
import org.apache.ibatis.jdbc.SQL;

public class WechatTagInfoSqlProvider {

    public String insertSelective(WechatTagInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("wechat_tag_info");
        
        if (record.getTagId() != null) {
            sql.VALUES("tag_id", "#{tagId,jdbcType=BIGINT}");
        }
        
        if (record.getTagName() != null) {
            sql.VALUES("tag_name", "#{tagName,jdbcType=VARCHAR}");
        }
        
        if (record.getTagCount() != null) {
            sql.VALUES("tag_count", "#{tagCount,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(WechatTagInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("wechat_tag_info");
        
        if (record.getTagName() != null) {
            sql.SET("tag_name = #{tagName,jdbcType=VARCHAR}");
        }
        
        if (record.getTagCount() != null) {
            sql.SET("tag_count = #{tagCount,jdbcType=INTEGER}");
        }
        
        sql.WHERE("tag_id = #{tagId,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}