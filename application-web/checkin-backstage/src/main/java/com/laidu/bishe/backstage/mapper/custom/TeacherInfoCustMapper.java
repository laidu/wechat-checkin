package com.laidu.bishe.backstage.mapper.custom;

import com.laidu.bishe.backstage.domain.TeacherInfo;
import com.laidu.bishe.backstage.mapper.TeacherInfoMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

/**
 * 教师信息表mapper扩展
 * Created by laidu on 2017/5/21.
 */
public interface TeacherInfoCustMapper extends TeacherInfoMapper{

    /**
     * 通过教师微信id获取教师信息
     * @param wechatId
     * @return
     */
    @Select({
            "select",
            "teacher_id, teacher_name, wechat_id",
            "from teacher_info",
            "where wechat_id = #{wechatId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="teacher_id", property="teacherId", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="teacher_name", property="teacherName", jdbcType=JdbcType.VARCHAR),
            @Result(column="wechat_id", property="wechatId", jdbcType=JdbcType.VARCHAR)
    })
    TeacherInfo selectByWechatId(String wechatId);
}
