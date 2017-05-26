package com.laidu.bishe.backstage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 特征信息
 * Created by laidu on 2017/5/22.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeatureInfo {

    /**
     * 特征信息路径
     */
    private String featurePath;

    /**
     * 特征信息类型
     */
    private int featureType;
}
