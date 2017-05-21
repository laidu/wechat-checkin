package com.laidu.bishe.wechat.handler;

import com.laidu.bishe.backstage.model.ResultMessage;
import com.laidu.bishe.backstage.service.StudentService;
import com.laidu.bishe.backstage.service.TeacherService;
import com.laidu.bishe.wechat.enums.ClickMenuKeyEnum;
import com.laidu.bishe.wechat.service.WechatUserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 菜单时间处理
 *
 * @author laidu
 */
@Slf4j
@Component
public class MenuHandler extends AbstractHandler {

    @Autowired
    WechatUserService wechatUserService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        String msg = String.format("type:%s, event:%s, key:%s",
                wxMessage.getMsgType(), wxMessage.getEvent(),
                wxMessage.getEventKey());

        switch (wxMessage.getEvent()) {

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

    /**
     * 菜单点击事件处理
     *
     * @param wxMessage
     * @return
     */
    public WxMpXmlOutMessage clickEventHandle(WxMpXmlMessage wxMessage) {


        ClickMenuKeyEnum click = null;
        String message = "暂不支持的操作";

        //单独判断抽点的情况
        boolean isRandom = wxMessage.getEventKey().contains(ClickMenuKeyEnum.RANDOM_CHECKIN_.name().toLowerCase());
        if (isRandom){

            String eventKey = wxMessage.getEventKey();

            int centage = Integer.parseInt(eventKey.replace(ClickMenuKeyEnum.RANDOM_CHECKIN_.name().toLowerCase(),""));
            List<String> students = teacherService.randomCheckIn(wxMessage.getFromUser(),centage);

            //返回抽点的学生名单
            message = students.toString();
        }

        try {
            click = ClickMenuKeyEnum.valueOf(wxMessage.getEventKey().toUpperCase());
        } catch (Exception e) {
            log.error("暂不支持此操作！eventKey = {}", wxMessage.getEventKey());
        }


        if (click != null) {

            switch (click) {

                case START_CHECKIN:

                    //教师点击开始考勤
                    message = teacherService.startCheckin(wxMessage.getFromUser()).getMessage();
                    break;

                case END_CHECKIN:

                    //教师点击结束考勤
                    message = teacherService.stopCheckin(wxMessage.getFromUser()).getMessage();
                    break;


                case VIEW_CHECKIN:

                    // TODO: 2017/5/21 查看本节统计
                    //教师点击查看统计（本次考勤）

                    break;

                case MY_TEA_INFO:
                    // TODO: 2017/5/21 教师信息
                    break;
            }
        }


        return WxMpXmlOutMessage.TEXT()
                .content(message)
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .build();
    }


}
