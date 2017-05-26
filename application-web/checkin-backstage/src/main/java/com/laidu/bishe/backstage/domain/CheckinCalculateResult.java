package com.laidu.bishe.backstage.domain;

public class CheckinCalculateResult {
    private Long id;

    private Long teacherId;

    private Long studentId;

    private Integer checkinResult;

    private Long seqId;

    private Long courceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getCheckinResult() {
        return checkinResult;
    }

    public void setCheckinResult(Integer checkinResult) {
        this.checkinResult = checkinResult;
    }

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public Long getCourceId() {
        return courceId;
    }

    public void setCourceId(Long courceId) {
        this.courceId = courceId;
    }
}