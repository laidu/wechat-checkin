package com.laidu.bishe.wechat.domain;

public class CourseInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseInfo.courseID
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    private Object courseid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseInfo.courseName
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    private String coursename;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseInfo.teacherID
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    private Object teacherid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column courseInfo.className
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    private String classname;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseInfo.courseID
     *
     * @return the value of courseInfo.courseID
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    public Object getCourseid() {
        return courseid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseInfo.courseID
     *
     * @param courseid the value for courseInfo.courseID
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    public void setCourseid(Object courseid) {
        this.courseid = courseid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseInfo.courseName
     *
     * @return the value of courseInfo.courseName
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    public String getCoursename() {
        return coursename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseInfo.courseName
     *
     * @param coursename the value for courseInfo.courseName
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseInfo.teacherID
     *
     * @return the value of courseInfo.teacherID
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    public Object getTeacherid() {
        return teacherid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseInfo.teacherID
     *
     * @param teacherid the value for courseInfo.teacherID
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    public void setTeacherid(Object teacherid) {
        this.teacherid = teacherid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column courseInfo.className
     *
     * @return the value of courseInfo.className
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    public String getClassname() {
        return classname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column courseInfo.className
     *
     * @param classname the value for courseInfo.className
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }
}