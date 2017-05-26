package com.laidu.bishe.wechat.service.impl;

import com.laidu.bishe.backstage.domain.WechatTagInfo;
import com.laidu.bishe.wechat.enums.WechatUserTagEnum;
import com.laidu.bishe.wechat.service.WechatTagService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpUserTagService;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laidu on 2017/5/17.
 */
@Slf4j
@Service
public class WechatTagServiceImpl implements WechatTagService {

    @Autowired
    private WxMpUserTagService wxMpUserTagService;


    @Override
    public boolean addUserTag(WechatUserTagEnum tag, String wechatId) {

        createTag(tag.getMessage());

        try {
            wxMpUserTagService.batchTagging(getTagId(tag),new String[]{wechatId});
            return true;
        } catch (WxErrorException e) {
            log.info("添加微信用户标签失败：teaWechatId={} ,tag={}",wechatId,tag);
        }

        return false;
    }

    @Override
    public Long getTagId(WechatUserTagEnum tagEnum) {

        Long tagId = null;

        try {

            List<WechatTagInfo> tags = getAllTags();
            for (WechatTagInfo tag : tags) {

                if (tagEnum.getMessage().equals(tag.getTagName())) {

                    tagId = Long.valueOf(tag.getTagId());
                }
            }

        } catch (Exception e) {
            log.info("获取用户tag失败");
            return null;
        }

        return tagId;
    }

    @Override
    public void createTag(String tagName) {

        List<WechatTagInfo> tags = getAllTags();

        List<String> tagNames = new ArrayList<>();
        tags.forEach(tagInfo -> {
            tagNames.add(tagInfo.getTagName());
        });

        if (!tagNames.contains(tagName)){

            try {
                wxMpUserTagService.tagCreate(tagName);
            } catch (WxErrorException e) {
                log.error("创建tag失败！ tagName={}",tagName);
            }
        }
    }

    @Override
    public List<WechatTagInfo> getAllTags() {

        List<WechatTagInfo> tagInfos = new ArrayList<>();

        try {
            List<WxUserTag> tags = new ArrayList<>();
            for (int i=0;i<3;i++){
                tags = wxMpUserTagService.tagGet();
                if (tags.size()>0){
                    break;
                }
            }
            tags.forEach(tag -> {

                WechatTagInfo tagInfo = convertToWechatTagInfo(tag);
                tagInfos.add(tagInfo);
            });

        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return tagInfos;
    }

    /**
     * WxUserTag -> WechatTagInfo
     * @param wxUserTag
     * @return
     */
    public WechatTagInfo convertToWechatTagInfo(WxUserTag wxUserTag){

        WechatTagInfo tagInfo = new WechatTagInfo();
        tagInfo.setTagId(wxUserTag.getId());
        tagInfo.setTagCount(wxUserTag.getCount());
        tagInfo.setTagName(wxUserTag.getName());

        return tagInfo;
    }

    public WxUserTag convertToWxUserTag(WechatTagInfo wechatTagInfo){

        WxUserTag wxUserTag = new WxUserTag();
        wxUserTag.setId(wechatTagInfo.getTagId());
        wxUserTag.setName(wechatTagInfo.getTagName());
        wxUserTag.setCount(wechatTagInfo.getTagCount());
        return wxUserTag;
    }
}
