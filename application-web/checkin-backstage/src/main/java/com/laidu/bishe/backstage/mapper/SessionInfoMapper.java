package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.SessionInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface SessionInfoMapper {
    @Delete({
        "delete from session_info",
        "where session_id = #{sessionId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long sessionId);

    @Insert({
        "insert into session_info (session_id, session_index, ",
        "start_min, start_hour, ",
        "end_hour, end_min)",
        "values (#{sessionId,jdbcType=BIGINT}, #{sessionIndex,jdbcType=VARCHAR}, ",
        "#{startMin,jdbcType=INTEGER}, #{startHour,jdbcType=INTEGER}, ",
        "#{endHour,jdbcType=INTEGER}, #{endMin,jdbcType=INTEGER})"
    })
    int insert(SessionInfo record);

    @InsertProvider(type=SessionInfoSqlProvider.class, method="insertSelective")
    int insertSelective(SessionInfo record);

    @Select({
        "select",
        "session_id, session_index, start_min, start_hour, end_hour, end_min",
        "from session_info",
        "where session_id = #{sessionId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="session_id", property="sessionId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="session_index", property="sessionIndex", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_min", property="startMin", jdbcType=JdbcType.INTEGER),
        @Result(column="start_hour", property="startHour", jdbcType=JdbcType.INTEGER),
        @Result(column="end_hour", property="endHour", jdbcType=JdbcType.INTEGER),
        @Result(column="end_min", property="endMin", jdbcType=JdbcType.INTEGER)
    })
    SessionInfo selectByPrimaryKey(Long sessionId);

    @UpdateProvider(type=SessionInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SessionInfo record);

    @Update({
        "update session_info",
        "set session_index = #{sessionIndex,jdbcType=VARCHAR},",
          "start_min = #{startMin,jdbcType=INTEGER},",
          "start_hour = #{startHour,jdbcType=INTEGER},",
          "end_hour = #{endHour,jdbcType=INTEGER},",
          "end_min = #{endMin,jdbcType=INTEGER}",
        "where session_id = #{sessionId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SessionInfo record);
}