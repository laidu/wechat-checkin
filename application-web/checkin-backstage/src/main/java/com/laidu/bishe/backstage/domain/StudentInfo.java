package com.laidu.bishe.backstage.domain;

public class StudentInfo {
    private Long stuId;

    private String stuName;

    private String wechatId;

    private String classId;

    private String featurePath;

    private Short featureType;

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId == null ? null : wechatId.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getFeaturePath() {
        return featurePath;
    }

    public void setFeaturePath(String featurePath) {
        this.featurePath = featurePath == null ? null : featurePath.trim();
    }

    public Short getFeatureType() {
        return featureType;
    }

    public void setFeatureType(Short featureType) {
        this.featureType = featureType;
    }
}