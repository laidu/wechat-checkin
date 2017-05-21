package com.laidu.bishe.backstage.mapper.custom;

import com.laidu.bishe.backstage.domain.SessionInfo;
import com.laidu.bishe.backstage.mapper.SessionInfoMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

/**
 * 节次信息扩展类
 * Created by laidu on 2017/5/21.
 */
public interface SessionInfoCustMapper extends SessionInfoMapper {


    /**
     * 通过时间获取节次信息
     * @param hour 小时数
     * @param min  分钟数
     * @return
     */
    @Select({
            "select",
            "session_id, session_index, start_min, start_hour, end_hour, end_min",
            "from session_info",
            "where start_hour * 60 + start_min <= #{arg0,jdbcType=INTEGER} *60 + #{ arg1,jdbcType=INTEGER} " +
                    "and end_hour * 60 + end_min >= #{arg0,jdbcType=INTEGER} *60 + #{arg1,jdbcType=INTEGER} "
    })
    @Results({
            @Result(column="session_id", property="sessionId", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="session_index", property="sessionIndex", jdbcType=JdbcType.VARCHAR),
            @Result(column="start_min", property="startMin", jdbcType=JdbcType.INTEGER),
            @Result(column="start_hour", property="startHour", jdbcType=JdbcType.INTEGER),
            @Result(column="end_hour", property="endHour", jdbcType=JdbcType.INTEGER),
            @Result(column="end_min", property="endMin", jdbcType=JdbcType.INTEGER)
    })
    SessionInfo selectByTime(int hour,int min);
}
