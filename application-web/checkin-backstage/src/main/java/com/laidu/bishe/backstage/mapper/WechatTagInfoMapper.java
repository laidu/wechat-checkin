package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.WechatTagInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface WechatTagInfoMapper {
    @Delete({
        "delete from wechat_tag_info",
        "where tag_id = #{tagId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long tagId);

    @Insert({
        "insert into wechat_tag_info (tag_id, tag_name, ",
        "tag_count)",
        "values (#{tagId,jdbcType=BIGINT}, #{tagName,jdbcType=VARCHAR}, ",
        "#{tagCount,jdbcType=INTEGER})"
    })
    int insert(WechatTagInfo record);

    @InsertProvider(type=WechatTagInfoSqlProvider.class, method="insertSelective")
    int insertSelective(WechatTagInfo record);

    @Select({
        "select",
        "tag_id, tag_name, tag_count",
        "from wechat_tag_info",
        "where tag_id = #{tagId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="tag_id", property="tagId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="tag_name", property="tagName", jdbcType=JdbcType.VARCHAR),
        @Result(column="tag_count", property="tagCount", jdbcType=JdbcType.INTEGER)
    })
    WechatTagInfo selectByPrimaryKey(Long tagId);

    @UpdateProvider(type=WechatTagInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WechatTagInfo record);

    @Update({
        "update wechat_tag_info",
        "set tag_name = #{tagName,jdbcType=VARCHAR},",
          "tag_count = #{tagCount,jdbcType=INTEGER}",
        "where tag_id = #{tagId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(WechatTagInfo record);
}