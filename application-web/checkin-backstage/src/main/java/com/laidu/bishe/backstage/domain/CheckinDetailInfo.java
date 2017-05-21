package com.laidu.bishe.backstage.domain;

import java.util.Date;

public class CheckinDetailInfo {
    private Long stuId;

    private Date checkinTime;

    private String proofPath;

    private Short checkinType;

    private Boolean isSucc;

    private Short checkinResult;

    private Long courseId;

    private Long seqId;

    private Long id;

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getProofPath() {
        return proofPath;
    }

    public void setProofPath(String proofPath) {
        this.proofPath = proofPath == null ? null : proofPath.trim();
    }

    public Short getCheckinType() {
        return checkinType;
    }

    public void setCheckinType(Short checkinType) {
        this.checkinType = checkinType;
    }

    public Boolean getIsSucc() {
        return isSucc;
    }

    public void setIsSucc(Boolean isSucc) {
        this.isSucc = isSucc;
    }

    public Short getCheckinResult() {
        return checkinResult;
    }

    public void setCheckinResult(Short checkinResult) {
        this.checkinResult = checkinResult;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}