package com.laidu.bishe.wechat.service;

import com.laidu.bishe.backstage.domain.WechatTagInfo;
import com.laidu.bishe.wechat.enums.WechatUserTagEnum;

import java.util.List;

/**
 * 微信用户标签服务接口
 * Created by laidu on 2017/5/17.
 */
public interface WechatTagService {


    boolean addUserTag(WechatUserTagEnum tag,String wechatId);

    /**
     * 通过枚举获取tagId
     * @param tag
     * @return
     */
    Long getTagId(WechatUserTagEnum tag);

    /**
     * 创建tags
     * @param tagNames
     * @return
     */
    void createTag(String tagNames);

    /**
     * 获取所有tags
     * @return
     */
    List<WechatTagInfo> getAllTags();

}
