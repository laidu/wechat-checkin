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
        "where stu_id = #{stuId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long stuId);

    @Insert({
        "insert into student_info (stu_id, stu_name, ",
        "wechat_id, class_id, ",
        "feature_path, feature_type)",
        "values (#{stuId,jdbcType=BIGINT}, #{stuName,jdbcType=VARCHAR}, ",
        "#{wechatId,jdbcType=VARCHAR}, #{classId,jdbcType=VARCHAR}, ",
        "#{featurePath,jdbcType=VARCHAR}, #{featureType,jdbcType=SMALLINT})"
    })
    int insert(StudentInfo record);

    @InsertProvider(type=StudentInfoSqlProvider.class, method="insertSelective")
    int insertSelective(StudentInfo record);

    @Select({
        "select",
        "stu_id, stu_name, wechat_id, class_id, feature_path, feature_type",
        "from student_info",
        "where stu_id = #{stuId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="stu_id", property="stuId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="stu_name", property="stuName", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat_id", property="wechatId", jdbcType=JdbcType.VARCHAR),
        @Result(column="class_id", property="classId", jdbcType=JdbcType.VARCHAR),
        @Result(column="feature_path", property="featurePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="feature_type", property="featureType", jdbcType=JdbcType.SMALLINT)
    })
    StudentInfo selectByPrimaryKey(Long stuId);

    @UpdateProvider(type=StudentInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StudentInfo record);

    @Update({
        "update student_info",
        "set stu_name = #{stuName,jdbcType=VARCHAR},",
          "wechat_id = #{wechatId,jdbcType=VARCHAR},",
          "class_id = #{classId,jdbcType=VARCHAR},",
          "feature_path = #{featurePath,jdbcType=VARCHAR},",
          "feature_type = #{featureType,jdbcType=SMALLINT}",
        "where stu_id = #{stuId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(StudentInfo record);
}