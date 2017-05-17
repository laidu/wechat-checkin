package com.laidu.bishe.wechat.handler;

import com.laidu.bishe.wechat.service.WechatUserService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 菜单时间处理
 * @author laidu
 */
@Component
public class MenuHandler extends AbstractHandler {

    @Autowired
    WechatUserService wechatUserService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
            Map<String, Object> context, WxMpService weixinService,
            WxSessionManager sessionManager) {

        String msg = String.format("type:%s, event:%s, key:%s",
            wxMessage.getMsgType(), wxMessage.getEvent(),
            wxMessage.getEventKey());

        switch (wxMessage.getEvent()){

            case WxConsts.BUTTON_VIEW:
                break;
            case WxConsts.BUTTON_CLICK:
                // TODO: 2017/5/17 实现业务逻辑 
                break;
        }

        return WxMpXmlOutMessage.TEXT().content(msg)
                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                .build();
    }


}
