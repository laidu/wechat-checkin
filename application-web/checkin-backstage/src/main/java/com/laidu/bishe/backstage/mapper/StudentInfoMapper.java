package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.StudentInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface StudentInfoMapper {
    @Delete({
        "delete from student_info",
        "where student_id = #{studentId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long studentId);

    @Insert({
        "insert into student_info (student_id, student_name, ",
        "wechat_id, class_id, ",
        "feature_path, feature_type, ",
        "register_time)",
        "values (#{studentId,jdbcType=BIGINT}, #{studentName,jdbcType=VARCHAR}, ",
        "#{wechatId,jdbcType=VARCHAR}, #{classId,jdbcType=VARCHAR}, ",
        "#{featurePath,jdbcType=VARCHAR}, #{featureType,jdbcType=SMALLINT}, ",
        "#{registerTime,jdbcType=TIMESTAMP})"
    })
    int insert(StudentInfo record);

    @InsertProvider(type=StudentInfoSqlProvider.class, method="insertSelective")
    int insertSelective(StudentInfo record);

    @Select({
        "select",
        "student_id, student_name, wechat_id, class_id, feature_path, feature_type, register_time",
        "from student_info",
        "where student_id = #{studentId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="student_name", property="studentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat_id", property="wechatId", jdbcType=JdbcType.VARCHAR),
        @Result(column="class_id", property="classId", jdbcType=JdbcType.VARCHAR),
        @Result(column="feature_path", property="featurePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="feature_type", property="featureType", jdbcType=JdbcType.SMALLINT),
        @Result(column="register_time", property="registerTime", jdbcType=JdbcType.TIMESTAMP)
    })
    StudentInfo selectByPrimaryKey(Long studentId);

    @UpdateProvider(type=StudentInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StudentInfo record);

    @Update({
        "update student_info",
        "set student_name = #{studentName,jdbcType=VARCHAR},",
          "wechat_id = #{wechatId,jdbcType=VARCHAR},",
          "class_id = #{classId,jdbcType=VARCHAR},",
          "feature_path = #{featurePath,jdbcType=VARCHAR},",
          "feature_type = #{featureType,jdbcType=SMALLINT},",
          "register_time = #{registerTime,jdbcType=TIMESTAMP}",
        "where student_id = #{studentId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(StudentInfo record);
}