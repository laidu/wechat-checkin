//package com.laidu.bishe.wechat.conditional;
//
//import com.laidu.bishe.backstage.domain.WechatTagInfo;
//import com.laidu.bishe.wechat.enums.WechatUserTagEnum;
//import com.laidu.bishe.wechat.service.WechatTagService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Condition;
//import org.springframework.context.annotation.ConditionContext;
//import org.springframework.core.type.AnnotatedTypeMetadata;
//
//import java.util.List;
//
///**
// * 微信公众平台用户标签是否初始化
// * Created by laidu on 2017/5/17.
// */
//public class WechatTagEnv implements Condition {
//
//    @Autowired
//    private WechatTagService wechatTagService;
//
//    @Override
//    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
//
//        List<WechatTagInfo> tagInfos = wechatTagService.getAllTags();
//
//        List<String> tagNames = null;
//        tagInfos.forEach(tagInfo -> {
//            tagNames.add(tagInfo.getTagName());
//        });
//
//        for (WechatUserTagEnum tagEnum : WechatUserTagEnum.values()){
//            if (!tagNames.contains(tagEnum.getMessage())){
//                return true;
//            }
//        }
//
//        return true;
//    }
//}
