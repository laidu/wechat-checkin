package com.laidu.bishe.utils.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程信息
 * @author laidu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseInfo {
    private Long courseId;

    private String courseName;

    private Long teacherId;

    private String classNames;

}