package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.WechatUserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface WechatUserInfoMapper {
    @Delete({
        "delete from wechat_user_info",
        "where wechat_id = #{wechatId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String wechatId);

    @Insert({
        "insert into wechat_user_info (wechat_id, user_info_id, ",
        "user_type, follow_status, ",
        "wx_user_info)",
        "values (#{wechatId,jdbcType=VARCHAR}, #{userInfoId,jdbcType=VARCHAR}, ",
        "#{userType,jdbcType=INTEGER}, #{followStatus,jdbcType=INTEGER}, ",
        "#{wxUserInfo,jdbcType=VARCHAR})"
    })
    int insert(WechatUserInfo record);

    @InsertProvider(type=WechatUserInfoSqlProvider.class, method="insertSelective")
    int insertSelective(WechatUserInfo record);

    @Select({
        "select",
        "wechat_id, user_info_id, user_type, follow_status, wx_user_info",
        "from wechat_user_info",
        "where wechat_id = #{wechatId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="wechat_id", property="wechatId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_info_id", property="userInfoId", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_type", property="userType", jdbcType=JdbcType.INTEGER),
        @Result(column="follow_status", property="followStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="wx_user_info", property="wxUserInfo", jdbcType=JdbcType.VARCHAR)
    })
    WechatUserInfo selectByPrimaryKey(String wechatId);

    @UpdateProvider(type=WechatUserInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WechatUserInfo record);

    @Update({
        "update wechat_user_info",
        "set user_info_id = #{userInfoId,jdbcType=VARCHAR},",
          "user_type = #{userType,jdbcType=INTEGER},",
          "follow_status = #{followStatus,jdbcType=INTEGER},",
          "wx_user_info = #{wxUserInfo,jdbcType=VARCHAR}",
        "where wechat_id = #{wechatId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(WechatUserInfo record);
}