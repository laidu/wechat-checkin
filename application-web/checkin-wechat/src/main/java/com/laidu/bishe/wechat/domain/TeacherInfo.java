package com.laidu.bishe.wechat.domain;

public class TeacherInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacherInfo.teacherID
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    private Object teacherid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacherInfo.teacherName
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    private String teachername;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacherInfo.wechatID
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    private String wechatid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacherInfo.teacherID
     *
     * @return the value of teacherInfo.teacherID
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    public Object getTeacherid() {
        return teacherid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacherInfo.teacherID
     *
     * @param teacherid the value for teacherInfo.teacherID
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    public void setTeacherid(Object teacherid) {
        this.teacherid = teacherid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacherInfo.teacherName
     *
     * @return the value of teacherInfo.teacherName
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    public String getTeachername() {
        return teachername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacherInfo.teacherName
     *
     * @param teachername the value for teacherInfo.teacherName
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    public void setTeachername(String teachername) {
        this.teachername = teachername == null ? null : teachername.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacherInfo.wechatID
     *
     * @return the value of teacherInfo.wechatID
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    public String getWechatid() {
        return wechatid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacherInfo.wechatID
     *
     * @param wechatid the value for teacherInfo.wechatID
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    public void setWechatid(String wechatid) {
        this.wechatid = wechatid == null ? null : wechatid.trim();
    }
}