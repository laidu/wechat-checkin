package com.laidu.bishe.backstage.mapper.custom;

import com.laidu.bishe.backstage.domain.StudentInfo;
import com.laidu.bishe.backstage.mapper.StudentInfoMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 学生信息mapper扩展
 * Created by laidu on 2017/5/23.
 */
public interface StudentInfoCustMapper extends StudentInfoMapper {

    /**
     * 通过学生微信号获取学生信息
     * @param wechatId
     * @return
     */
    @Select({
            "select",
            "student_id, student_name, wechat_id, class_id, feature_path, feature_type, register_time",
            "from student_info",
            "where wechat_id = #{teaWechatId,jdbcType=VARCHAR}"
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
    StudentInfo selectByWechatId(String wechatId);


    /**
     * 通过班级名获取学生列表
     * @param classId
     * @return
     */
    @Select({
            "select",
            "student_id,wechat_id",
            "from student_info",
            "where class_id = #{class_id,jdbcType=VARCHAR}"
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
    List<StudentInfo> selectByClassId(String classId);
}
