package com.laidu.bishe.backstage.model;

import com.laidu.bishe.backstage.domain.SeqInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 考勤次序详细信息
 * Created by laidu on 2017/5/22.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeqDetialInfo{

    private SeqInfo seqInfo;

    /**
     * 课程名
     */
    private String courseName;
}
