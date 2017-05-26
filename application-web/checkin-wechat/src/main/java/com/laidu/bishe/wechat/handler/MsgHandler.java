package com.laidu.bishe.wechat.handler;

import com.laidu.bishe.backstage.domain.StudentInfo;
import com.laidu.bishe.backstage.domain.TeacherInfo;
import com.laidu.bishe.backstage.enums.FeatureTypeEnum;
import com.laidu.bishe.backstage.mapper.custom.TeacherInfoCustMapper;
import com.laidu.bishe.backstage.model.CheckinContentInfo;
import com.laidu.bishe.backstage.model.StudentDetialInfo;
import com.laidu.bishe.backstage.service.StudentService;
import com.laidu.bishe.utils.utils.FileUtil;
import com.laidu.bishe.utils.utils.Md5Util;
import com.laidu.bishe.wechat.builder.TextBuilder;
import com.laidu.bishe.wechat.config.WechatProperties;
import com.laidu.bishe.wechat.enums.ClickMenuKeyEnum;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.http.MediaDownloadRequestExecutor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 微信消息处理器
 *
 * @author laidu
 */
@Slf4j
@Component
public class MsgHandler extends AbstractHandler {

    @Autowired
    private StudentService studentService;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatProperties wechatProperties;

    @Autowired(required = false)
    private TeacherInfoCustMapper teacherInfoCustMapper;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {



        if (!wxMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT)) {
            log.info("非事件消息,{}", wxMessage);
        }

        String message = "";


        ClickMenuKeyEnum clickMenuKeyEnum = null;

        if (wxMessage.getMsgType().equalsIgnoreCase(WxConsts.MEDIA_VOICE)) {

            message = voiceRecog(wxMessage);
        }


        if (wxMessage.getMsgType().equalsIgnoreCase(WxConsts.MASS_MSG_IMAGE)){

            CheckinContentInfo checkinContentInfo = studentService.requestCheckin(wxMessage.getFromUser());

            if (checkinContentInfo == null) {
                message = "请先点击【我要签到】按钮!";
            }

            if (!checkinContentInfo.getTypeEnum().equals(FeatureTypeEnum.FACE) &&!checkinContentInfo.getTypeEnum().equals(FeatureTypeEnum.LEAVE)) {
                message = "请按照要求上传指定的特征信息！";
            }

            if (wxMessage.getMediaId()!=null){

                try {
                    switch (checkinContentInfo.getTypeEnum()){
                        case LEAVE:
                            //发送假条信息至教师
                            message = sendLeaveInfo(wxMessage);
                            break;

                        case FACE:
                            message = faceRecog(wxMessage);
                            break;
                    }

                } catch (IOException e) {
                    log.info("人脸信息保存失败");
                }

            }
        }

        return new TextBuilder().build(message, wxMessage, weixinService);

    }

    public String voiceRecog(WxMpXmlMessage wxMessage) throws WxErrorException {

        String result = "声纹信息无法识别！请重新上传！朗读以下文本：\n\n 【%s】";
        CheckinContentInfo checkinContentInfo = studentService.requestCheckin(wxMessage.getFromUser());

        if (checkinContentInfo == null) {
            return "请先点击【我要签到】按钮!";
        }

        if (!checkinContentInfo.getTypeEnum().equals(FeatureTypeEnum.VOICE)) {
            return "请按照要求上传指定的特征信息！";
        }

        if (checkinContentInfo.isWait()||checkinContentInfo.isSucc()){
            return checkinContentInfo.toString();
        }

        File voice = mediaVoiceDownload(wxMessage.getMediaId());

        if (wxMessage.getRecognition() == null || wxMessage.getRecognition().isEmpty()) {
            return String.format(result, checkinContentInfo.getAction());
        }

        if (wxMessage.getRecognition().trim().replace("。", "").equalsIgnoreCase(checkinContentInfo.getAction())) {

            studentService.stuCheckInByVoice(wxMessage.getFromUser(), voice);

            return String.format("语音内容匹配成功！请耐心等待签到结果。\n 语音识别结果：\n\n 【%s】", checkinContentInfo.getAction());
        } else {
            return String.format("语音内容匹配失败！识别结果：\n\n 【%s】\n\n"
                    + String.format(result, checkinContentInfo.getAction()), wxMessage.getRecognition().trim().replace("。", ""));
        }

    }

    public String faceRecog(WxMpXmlMessage wxMessage) throws WxErrorException, IOException {

        String result = "人脸信息获取失败！ 请重新上传包含以下动作的人脸信息！\n\n 【%s】";
        CheckinContentInfo checkinContentInfo = studentService.requestCheckin(wxMessage.getFromUser());

        if (checkinContentInfo == null) {
            return "请先点击【我要签到】按钮!";
        }

        if (checkinContentInfo.isWait()||checkinContentInfo.isSucc()){
            return checkinContentInfo.toString();
        }

        if (wxMessage.getMediaId()!=null){

            File face = mediaPictureDownload(wxMessage.getMediaId());

            if (face != null) {

                studentService.stuCheckInByFace(wxMessage.getFromUser(), face);

                return String.format("人脸信息上传成功！请耐心等待签到结果。");
            }
        }

        if (checkinContentInfo == null) {
            return "请先点击【我要签到】按钮!";
        }

        if (!checkinContentInfo.getTypeEnum().equals(FeatureTypeEnum.FACE)) {
            return "请按照要求上传指定的特征信息！";
        }

        if (wxMessage.getEvent()!=null&&!wxMessage.getEvent().equalsIgnoreCase(WxConsts.EVT_PIC_SYSPHOTO)) {
            return String.format("请通过点击【刷脸签到】按钮上传包含以下动作的人脸信息！\n\n 【%s】", checkinContentInfo.getAction());
        }


        return String.format(result, checkinContentInfo.getAction());

    }

    public String sendLeaveInfo(WxMpXmlMessage wxMessage) throws WxErrorException, IOException {


        CheckinContentInfo checkinContentInfo = studentService.requestCheckin(wxMessage.getFromUser());


        if (checkinContentInfo == null) {
            return "请先点击【我要签到】按钮!";
        }

        if (checkinContentInfo.isWait()||checkinContentInfo.isSucc()){
            return checkinContentInfo.toString();
        }

        if (wxMessage.getMediaId()!=null){

            StudentDetialInfo studentInfo = studentService.getMyInfo(wxMessage.getFromUser());
            mediaPictureDownload(wxMessage.getMediaId());

            String format = "收到一条来自【%s】学生【%s】的请假信息：\n ";

            //创建文本消息
            WxMpKefuMessage textMessage = new WxMpKefuMessage();
            textMessage.setMsgType(WxConsts.XML_MSG_TEXT);
            textMessage.setContent(String.format(format,studentInfo.getClassId(),studentInfo.getStudentName()));
            textMessage.setToUser(checkinContentInfo.getTeaWechatId());

            WxMpKefuMessage mediaImage = new WxMpKefuMessage();
            mediaImage.setToUser(checkinContentInfo.getTeaWechatId());
            mediaImage.setMsgType(WxConsts.MEDIA_IMAGE);
            mediaImage.setMediaId(wxMessage.getMediaId());

            try {

                wxMpService.getKefuService().sendKefuMessage(textMessage);
                wxMpService.getKefuService().sendKefuMessage(mediaImage);
                checkinContentInfo.setWait(true);

                studentService.stuLeave(wxMessage.getFromUser(),null);

                return "假条信息已发送至当前教师微信，请耐心等待审批通过！";

            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        }

        return "";

    }


    /**
     * 下载微信音频文件接口
     *
     * @param mediaId
     * @return
     * @throws WxErrorException
     */
    public File mediaVoiceDownload(String mediaId) throws WxErrorException {
        String url = "http://file.api.weixin.qq.com/cgi-bin/media/get";

        return wxMpService.execute(
                new MediaDownloadRequestExecutor(
                        new File(wechatProperties.getTmpVoiceDirFile())),
                url, "media_id=" + mediaId);
    }

    /**
     * 下载微信图片文件接口
     *
     * @param mediaId
     * @return
     * @throws WxErrorException
     */
    public File mediaPictureDownload(String mediaId) throws WxErrorException {
        String url = "http://file.api.weixin.qq.com/cgi-bin/media/get";

        return wxMpService.execute(
                new MediaDownloadRequestExecutor(
                        new File(wechatProperties.getTmpPictureDirFile())),
                url, "media_id=" + mediaId);
    }


}
