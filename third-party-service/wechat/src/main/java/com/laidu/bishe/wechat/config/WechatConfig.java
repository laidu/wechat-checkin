package com.laidu.bishe.wechat.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by laidu on 2017/5/7.
 */
@Configuration
@ConfigurationProperties(prefix = "wechat.mp")
public class WechatConfig {

    /**
     * 设置微信公众号的appid
     */
    @Getter
    private String appId;

    /**
     * 设置微信公众号的app secret
     */
    @Getter
    private String secret;

    /**
     * 设置微信公众号的token
     */
    @Getter
    private String token;

    /**
     * 设置微信公众号的EncodingAESKey
     */
    @Getter
    private String aesKey;
}
