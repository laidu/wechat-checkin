package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.TeacherInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TeacherInfoMapper {
    @Delete({
        "delete from teacher_info",
        "where teacher_id = #{teacherId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long teacherId);

    @Insert({
        "insert into teacher_info (teacher_id, teacher_name, ",
        "wechat_id, register_time)",
        "values (#{teacherId,jdbcType=BIGINT}, #{teacherName,jdbcType=VARCHAR}, ",
        "#{wechatId,jdbcType=VARCHAR}, #{registerTime,jdbcType=TIMESTAMP})"
    })
    int insert(TeacherInfo record);

    @InsertProvider(type=TeacherInfoSqlProvider.class, method="insertSelective")
    int insertSelective(TeacherInfo record);

    @Select({
        "select",
        "teacher_id, teacher_name, wechat_id, register_time",
        "from teacher_info",
        "where teacher_id = #{teacherId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="teacher_id", property="teacherId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="teacher_name", property="teacherName", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat_id", property="wechatId", jdbcType=JdbcType.VARCHAR),
        @Result(column="register_time", property="registerTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TeacherInfo selectByPrimaryKey(Long teacherId);

    @UpdateProvider(type=TeacherInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TeacherInfo record);

    @Update({
        "update teacher_info",
        "set teacher_name = #{teacherName,jdbcType=VARCHAR},",
          "wechat_id = #{wechatId,jdbcType=VARCHAR},",
          "register_time = #{registerTime,jdbcType=TIMESTAMP}",
        "where teacher_id = #{teacherId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TeacherInfo record);
}