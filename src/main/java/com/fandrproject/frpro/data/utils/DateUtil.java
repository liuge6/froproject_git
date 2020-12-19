package com.fandrproject.frpro.data.utils;

import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * Created by sml
 * 2020/12/13 00:10
 */
public class DateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    /** 年. */
    public static final String YEAR_FORMAT = "yyyy";

    /** 默认日期格式. */
    public static final String DEFALT_DATE_FORMAT = "yyyy-MM-dd";

    /** 默认日期格式. */
    public static final String MONTH_DATE_FORMAT = "yyyy-MM";

    /** 默认时间格式. */
    public static final String DEFUALT_TIMESTAMPE_FORMAT = "yyyy-MM-dd hh:mm:ss sss";

    /** 时间格式yyyyMMddHHmmssSSS. */
    public static final String DATE_FORMATE_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /** 时间格式yyyyMMddHHmmssSSS. */
    public static final String DATE_FORMATE_YYYYMMDDHHMMSS0 = "yyyyMMddHHmmss";

    /** 时间格式yyyy-MM-dd HH:mm:ss. */
    public static final String DATE_FORMATE_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    /** 时间格式yyMMdd. */
    public static final String DATE_FORMATE_YYMMDD = "yyMMdd";

    /** 时间格式yyyyMMdd. */
    public static final String DATE_FORMATE_YYYYMMDD = "yyyyMMdd";

    /** 时间格式yyyyMMdd. */
    public static final String DATE_FORMATE_YYYYMMDDHH = "yyyyMMddHH";

    /**
     * 取得今天日期字符串.
     *
     * @return 今天日期字符串
     */
    public static String getCurrentDay() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DEFALT_DATE_FORMAT);
        return sdf.format(today);
    }

    /**
     * 取得现在时间（yyyy-MM-dd HH:mm:ss）.
     *
     * @return 取得现在时间
     */
    public static String getCurrentDate() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATE_YYYYMMDDHHMMSS);
        return sdf.format(today);
    }

    public static String getCurrentDate0() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATE_YYYYMMDDHHMMSS0);
        return sdf.format(today);
    }

    /**
     * 获取年月日yyyyMMdd
     *
     * @return
     */
    public static String getCurrentDayNew() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    /**
     * 取得当前年 .
     *
     * @return 当前年
     */
    public static String getCurrentYear() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(YEAR_FORMAT);
        return sdf.format(today);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param date1 较小的时间
     * @param date2 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(String date1, String date2, String type) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(type);

        Date smdate = sdf.parse(date1);
        Date bdate = sdf.parse(date2);
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取系统当前时间,形如20170908
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getYyyyMmDdString() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATE_YYYYMMDDHH);

        return sdf.format(date);
    }

    /**
     *
     * 得到当前日期的前一个月的年月
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getBeforeMonth(String month) throws ParseException {
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        SimpleDateFormat sdf = new SimpleDateFormat(MONTH_DATE_FORMAT);
        ca.setTime(sdf.parse(month));
        ca.add(Calendar.MONTH, -1);// 月份减1
        Date resultDate = ca.getTime(); // 结果
        return sdf.format(resultDate);
    }

    /**
     * CTS 日期转相应格式String日期
     * @throws ParseException
     */
    public static String getFormatDateByCTS(String CTSDate){
        DateFormat dFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);
        Date date;
        String CurrentDate = "";
        try {
            date = dFormat.parse(CTSDate);
            DateFormat dFormat2 = new SimpleDateFormat("yyyyMMdd");
            CurrentDate = dFormat2.format(date);
        } catch (ParseException e) {
            LOGGER.error("转换失败",e);
        }
        return CurrentDate;
    }

    /**
     * @Title: getYestoday
     * @Description:获取前一天日期
     * @param: @return 20180305
     * @return: String
     * @throws:
     */
    public static String getYestoday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        return yesterday;
    }

    public static String getYesterday(String yyyyMMdd) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(yyyyMMdd);
            c.setTime(date);
            int day1 = c.get(Calendar.DATE);
            c.set(Calendar.DATE, day1 - 1);

            return new SimpleDateFormat("yyyyMMdd").format(c.getTime());
        } catch (ParseException e) {
            LOGGER.error("转换失败",e);
        }
        return "";
    }
    public static String getTomorrow(String yyyyMMdd) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(yyyyMMdd);
            c.setTime(date);
            int day1 = c.get(Calendar.DATE);
            c.set(Calendar.DATE, day1 + 1);
            return new SimpleDateFormat("yyyyMMdd").format(c.getTime());
        } catch (ParseException e) {
            LOGGER.error("转换失败",e);
        }
        return "";
    }



    /**
     * 获取今天的星期
     */
    @SuppressWarnings("static-access")
    public static String getWeekOfDate(Date date,int day){
        String[] weekDats = {"7","1","2","3","4","5","6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, day);
        int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if (week < 0 ) {
            week = 0;
        }
        return weekDats[week];
    }


    /**
     * 获取某几天的时间
     *
     * 88389877
     * @param strType  日期格式类型
     * @param day      相加的时间
     */
    @SuppressWarnings("static-access")
    public static String getYearAndMonthByDay(String strType,int day){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, day);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(strType);
        String dateString = sdf.format(date);
        return dateString;
    }

    /**
     *
     * 得到当前日期的前一个月的年月
     *
     * @return
     * @throws ParseException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getBeforeMonthYearDate(String startTime, int type, int date) throws ParseException{
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATE_YYYYMMDD);
        if (StringUtils.isEmpty(startTime)) {
            ca.setTime(new Date());
        }else {
            Date smdate = sdf.parse(startTime);
            ca.setTime(smdate);
        }
        if (type == 1) {
            ca.add(Calendar.DATE, date);// 天数
        }else{
            ca.add(Calendar.MONTH, date);// 月
        }

        Date resultDate = ca.getTime(); // 结果
        return sdf.format(resultDate);
    }

    /**
     * 日期比较(因为daysBetween方法是用，参数2 - 参数1，所有逻辑是反的需要注意)
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static boolean compareDate(String date1, String date2) throws ParseException {
        int i = DateUtil.daysBetween(date1, date2, DateUtil.DEFALT_DATE_FORMAT);
        if(i >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
