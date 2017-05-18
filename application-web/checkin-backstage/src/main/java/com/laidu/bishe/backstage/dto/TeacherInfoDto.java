package com.laidu.bishe.backstage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 教师信息Dto
 * Created by laidu on 2017/5/18.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherInfoDto {

    /**
     * 教师工号
     */
    @NotNull(message = "教师工号不能为空")
    private Long teacherId;

    /**
     * 教师姓名
     */
    @NotNull(message = "教师姓名不能为空")
    private String teacherName;

    /**
     * 教师微信号
     */
    @NotNull(message = "教师微信号不能为空")
    private String wechatId;
}
