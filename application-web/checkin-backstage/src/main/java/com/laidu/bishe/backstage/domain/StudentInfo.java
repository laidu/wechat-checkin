package com.laidu.bishe.backstage.domain;

import java.util.Date;

public class StudentInfo {
    private Long studentId;

    private String studentName;

    private String wechatId;

    private String classId;

    private String featurePath;

    private Short featureType;

    private Date registerTime;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
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

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}