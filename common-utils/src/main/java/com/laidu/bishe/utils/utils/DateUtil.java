package com.laidu.bishe.utils.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by chenwen on 16/10/24.
 */
public class DateUtil {

    public static final long DAYOFTWOWEEK=2*7;
    public static final long TIMEOFDAY = 24*60*60*1000;
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DAY_FORMAT = "yyyy-MM-dd";

    public static final String STRING_FORMAT = "yyyMMddHHmmssSSS";

    public static String getDate(String format, Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static Date getDate(String format, String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(date);
    }

    public static String formatDateToString(Date date){
        DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    public static String formatDateToStrings(Date date){
        if (date == null){
            return null;
        }
        DateFormat format= new SimpleDateFormat("yyyy-MM-dd ");
        return format.format(date);
    }
    public static String formatDateToDayFormatStrings(Date date){
        DateFormat format= new SimpleDateFormat(DAY_FORMAT);
        return format.format(date);
    }
    public static String formatDateStrings(Date date){
        DateFormat format= new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }

    public static Date parseStringToDate(String date) throws ParseException {
        DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.parse(date);
    }

    public static Date parseToDate(Date date,String pattern) throws ParseException{
        DateFormat format= new SimpleDateFormat(pattern);
        return format.parse(format.format(date));
    }

    public static Date parseStringToDate2(String date) throws ParseException{
        DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(date);
    }

    public static Date parseStringToyyyyMMdd(String date) throws ParseException{
        DateFormat format= new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }
    public static Date getNDayToDate(Date d ,int n){
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DAY_OF_MONTH, n);
        return c.getTime();
    }
    public static Date getNYearToDate(Date d ,int n){
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.YEAR, n);
        return c.getTime();
    }

    /**
     * 获取两个日期距离天数
     * @param from 开始日期
     * @param to 结束日期
     * @return
     */
    public static int getIntervalDays(Date from, Date to){
        return (int) ((to.getTime() - from.getTime()) / 1000 / 60 / 60 / 24);
    }

    public static String formatDateToString(long l) {
        Date d = new Date(l);
        DateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        return format.format(d);
    }

    /**
     * 获取一小时
     * @param d
     * @param n
     * @return
     */
    public static Date getNHourToDate(Date d,int n){
        Calendar c = Calendar.getInstance();

        c.setTime(d);

        c.add(Calendar.HOUR_OF_DAY,n);

        return c.getTime();
    }


    public static Date getDate(Date beginDate, int datas) {
        Calendar beginCal=Calendar.getInstance();
        beginCal.setTime(beginDate);
        GregorianCalendar calendar = new GregorianCalendar(beginCal.get(Calendar.YEAR),beginCal.get(Calendar.MONTH),beginCal.get(Calendar.DATE));
        calendar.add(GregorianCalendar.DATE, datas);
        String begin = new java.sql.Date(calendar.getTime().getTime()).toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = null;
        try {
            endDate = sdf.parse(begin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }
    public static Date getMonth(Date beginDate, int month) {
        Calendar beginCal=Calendar.getInstance();
        beginCal.setTime(beginDate);
        GregorianCalendar calendar = new GregorianCalendar(beginCal.get(Calendar.YEAR),beginCal.get(Calendar.MONTH),beginCal.get(Calendar.DATE));
        calendar.add(GregorianCalendar.MONTH, month);
        String begin = new java.sql.Date(calendar.getTime().getTime()).toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = null;
        try {
            endDate = sdf.parse(begin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }

    public static int getQuarter(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        return (month / 3) + 1;
    }

    public static int getQuarter(){
        return getQuarter(new Date());
    }

    public static int getYear(Date date){

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return  calendar.get(Calendar.YEAR);
    }

    /**
     * 获取某个月份第一天(相比当前时间)
     * 例如(获取当月,传0,前一个月,传-1)
     * @param month
     * @return 例如 Wed Feb 01 00:00:00 CST 2017
     */
    public static Date getMonthFirstDay(int month){
        Calendar calendar=Calendar.getInstance();//获取当前日期
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static int getAbbrYear(){
        int year = getYear(new Date());
        return year % 100;
    }

    private static long orderNum = 0l;
    private static String date;
    public static synchronized String getOrderNo() {
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        if(date==null||!date.equals(str)){
            date = str;
            orderNum  = 0l;
        }
        orderNum ++;
        long orderNo = Long.parseLong((date)) * 10000;
        orderNo += orderNum;
        return orderNo+"";
    }

    /**
     * UNIXTIME
     * @return
     */
    public static int getCurrentUnixTime(){
        return Integer.parseInt(Long.toString(System.currentTimeMillis() / 1000));
    }


    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static Date getTodayEndTime(){
        Date today = new Date();
        today.setHours(23);
        today.setMinutes(59);
        today.setSeconds(59);
        return today;
    }

    public static void main(String[] args) {
//        System.out.println(DateUtil.getDate(DEFAULT_FORMAT,DateUtil.getNHourToDate(new Date(),24)));


        System.out.println(DateUtil.getDate(DEFAULT_FORMAT,DateUtil.getTodayEndTime()));
    }
}
