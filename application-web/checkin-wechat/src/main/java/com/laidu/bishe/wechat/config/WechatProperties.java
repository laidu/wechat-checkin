package com.laidu.bishe.wechat.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by laidu on 2017/5/7.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wechat.mp")
public class WechatProperties {

    /**
     * 设置微信公众号的appid
     */
    private String appId;

    /**
     * 设置微信公众号的app secret
     */
    private String secret;

    /**
     * 设置微信公众号的token
     */
    private String token;

    /**
     * 设置微信公众号的EncodingAESKey
     */
    private String aesKey;

    /**
     * 微信多媒体文件保存路径
     */
    private String tmpDirFile;

    /**
     * 微信图片保存路径
     */
    private String tmpPictureDirFile;

    /**
     * 微信音频保存路径
     */
    private String tmpVoiceDirFile;
}
