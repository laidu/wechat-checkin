package com.laidu.bishe.wechat.handler;

import com.laidu.bishe.backstage.domain.WechatUserInfo;
import com.laidu.bishe.utils.util.JacksonUtil;
import com.laidu.bishe.wechat.builder.TextBuilder;
import com.laidu.bishe.wechat.enums.FollowStatusEnum;
import com.laidu.bishe.wechat.enums.WechatUserTagEnum;
import com.laidu.bishe.wechat.service.WechatTagService;
import com.laidu.bishe.wechat.service.WechatUserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserTagService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Binary Wang
 */
@Slf4j
@Component
public class SubscribeHandler extends AbstractHandler {


    @Lazy
    @Autowired(required = false)
    private WechatUserService wechatUserService;

    @Autowired
    private WechatTagService wechatTagService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
            Map<String, Object> context, WxMpService weixinService,
            WxSessionManager sessionManager) throws WxErrorException {

        log.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        WxMpUser userWxInfo = weixinService.getUserService()
            .userInfo(wxMessage.getFromUser(), null);

        if (userWxInfo != null) {

            //保存关注用户信息
            WechatUserInfo userInfo = new WechatUserInfo();
            userInfo.setWechatId(userWxInfo.getOpenId());
            userInfo.setFollowStatus(FollowStatusEnum.FOLLOW.getCode());
            userInfo.setUserType(WechatUserTagEnum.UNREGISTER.getCode());
            userInfo.setWxUserInfo(JacksonUtil.objToJson(userWxInfo));

            wechatTagService.addUserTag(WechatUserTagEnum.UNREGISTER,userInfo.getWechatId());

            wechatUserService.follow(userInfo);
        }

        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = handleSpecial(wxMessage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build("感谢关微信考勤系统！", wxMessage, weixinService);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
            throws Exception {
        //TODO
        return null;
    }

}

