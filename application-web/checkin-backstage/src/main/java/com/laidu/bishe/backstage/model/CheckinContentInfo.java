package com.laidu.bishe.backstage.model;

import com.laidu.bishe.backstage.enums.FeatureTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 签到类型信息
 * Created by laidu on 2017/5/23.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckinContentInfo {


    /**
     * 是否已签到成功
     */
    private boolean isSucc = false;

    /**
     * 是否正在等在考勤结果
     */
    private boolean isWait = false;

    /**
     * 签到类型／多媒体文件类型
     */
    private FeatureTypeEnum typeEnum;

    /**
     * 签到动作
     */
    private String action;

    /**
     * 刷脸签到图片md5值
     * @return
     */
    private String picMd5Sum;

    /**
     * 多媒体文件mediaId
     * @return
     */

    private String mediaId;

    /**
     * 教师id
     */
    private String teaWechatId;



    @Override
    public String toString() {

        if (isSucc){
            return "已完成本次考勤，请勿多次进行签到！";
        }

        if (isWait){
            return "签到信息已上传，请耐心等待签到结果，请勿多次进行签到！";
        }

        switch (typeEnum) {

            case FACE:

                return String.format("请选择刷脸签到，并做出【%s】动作。", action);

            case VOICE:

                return String.format("请选择发送语音信息，并朗诵以下文本【%s】。", action);

            case LEAVE:

                return String.format("请上传假条图像信息。");

            default:
                return "";
        }
    }
}
