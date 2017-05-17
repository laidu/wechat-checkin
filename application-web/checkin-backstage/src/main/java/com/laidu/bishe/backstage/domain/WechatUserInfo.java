package com.laidu.bishe.backstage.domain;

public class WechatUserInfo {
    private String wechatId;

    private String userInfoId;

    private Integer userType;

    private Integer followStatus;

    private String wxUserInfo;

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId == null ? null : wechatId.trim();
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId == null ? null : userInfoId.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(Integer followStatus) {
        this.followStatus = followStatus;
    }

    public String getWxUserInfo() {
        return wxUserInfo;
    }

    public void setWxUserInfo(String wxUserInfo) {
        this.wxUserInfo = wxUserInfo == null ? null : wxUserInfo.trim();
    }
}