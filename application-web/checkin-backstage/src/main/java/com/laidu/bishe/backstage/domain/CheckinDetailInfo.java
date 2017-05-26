package com.laidu.bishe.backstage.domain;

import java.util.Date;

public class CheckinDetailInfo {
    private Long id;

    private Long studentId;

    private Date checkinTime;

    private String proofPath;

    private String checkinType;

    private Boolean isSucc;

    private Long seqId;

    private Integer checkinResult;

    private Long courseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public String getCheckinType() {
        return checkinType;
    }

    public void setCheckinType(String checkinType) {
        this.checkinType = checkinType == null ? null : checkinType.trim();
    }

    public Boolean getIsSucc() {
        return isSucc;
    }

    public void setIsSucc(Boolean isSucc) {
        this.isSucc = isSucc;
    }

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public Integer getCheckinResult() {
        return checkinResult;
    }

    public void setCheckinResult(Integer checkinResult) {
        this.checkinResult = checkinResult;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}