package com.laidu.bishe.wechat.service;

import com.laidu.bishe.backstage.domain.WechatUserInfo;
import com.laidu.bishe.backstage.model.Message;

/**
 * 微信用户服务接口
 * Created by laidu on 2017/5/17.
 */

public interface WechatUserService {

    /**
     * 微信扫码关注用户
     * @param userInfo
     * @return
     */
    Message follow(WechatUserInfo userInfo);

    /**
     * 微信取消关注用户
     * @param wechatId
     * @return
     */
    Message unFollow(String wechatId);


}
