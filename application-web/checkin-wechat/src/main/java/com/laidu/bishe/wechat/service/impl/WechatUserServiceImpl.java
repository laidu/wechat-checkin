package com.laidu.bishe.wechat.service.impl;

import com.laidu.bishe.backstage.domain.WechatUserInfo;
import com.laidu.bishe.backstage.mapper.WechatUserInfoMapper;
import com.laidu.bishe.backstage.model.Message;
import com.laidu.bishe.wechat.enums.FollowStatusEnum;
import com.laidu.bishe.wechat.service.WechatUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 微信公众号关注用户服务
 * Created by laidu on 2017/5/17.
 */
@Slf4j
@Service
public class WechatUserServiceImpl implements WechatUserService {

    @Lazy
    @Autowired(required = false)
    private WechatUserInfoMapper userInfoMapper;

    @Override
    public Message follow(WechatUserInfo userInfo) {

        try {
            userInfoMapper.insert(userInfo);
        } catch (Exception e) {

            log.info("保存关注用户数据失败！wechatUserInfo={}", userInfo);
        }
        return null;
    }

    @Override
    public Message unFollow(String wechatId) {

        WechatUserInfo userInfo = userInfoMapper.selectByPrimaryKey(wechatId);

        if (userInfo==null){
            log.info("该用户尚未关注微信号!");
            return null;
        }
        userInfo.setFollowStatus(FollowStatusEnum.UNFOLLOW.getCode());

        userInfoMapper.updateByPrimaryKey(userInfo);

        return null;
    }
}
