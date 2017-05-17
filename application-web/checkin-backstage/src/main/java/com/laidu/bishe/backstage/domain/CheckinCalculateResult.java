package com.laidu.bishe.backstage.domain;

public class CheckinCalculateResult {
    private Long id;

    private String courceId;

    private Long teacherId;

    private Long seqStart;

    private Long seqEnd;

    private Object result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourceId() {
        return courceId;
    }

    public void setCourceId(String courceId) {
        this.courceId = courceId == null ? null : courceId.trim();
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSeqStart() {
        return seqStart;
    }

    public void setSeqStart(Long seqStart) {
        this.seqStart = seqStart;
    }

    public Long getSeqEnd() {
        return seqEnd;
    }

    public void setSeqEnd(Long seqEnd) {
        this.seqEnd = seqEnd;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}