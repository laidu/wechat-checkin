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
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into session_info (id, session_id, ",
        "start_time, end_time, ",
        "day)",
        "values (#{id,jdbcType=INTEGER}, #{sessionId,jdbcType=VARCHAR}, ",
        "#{startTime,jdbcType=VARCHAR}, #{endTime,jdbcType=VARCHAR}, ",
        "#{day,jdbcType=VARCHAR})"
    })
    int insert(SessionInfo record);

    @InsertProvider(type=SessionInfoSqlProvider.class, method="insertSelective")
    int insertSelective(SessionInfo record);

    @Select({
        "select",
        "id, session_id, start_time, end_time, day",
        "from session_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="session_id", property="sessionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="day", property="day", jdbcType=JdbcType.VARCHAR)
    })
    SessionInfo selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SessionInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SessionInfo record);

    @Update({
        "update session_info",
        "set session_id = #{sessionId,jdbcType=VARCHAR},",
          "start_time = #{startTime,jdbcType=VARCHAR},",
          "end_time = #{endTime,jdbcType=VARCHAR},",
          "day = #{day,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SessionInfo record);
}