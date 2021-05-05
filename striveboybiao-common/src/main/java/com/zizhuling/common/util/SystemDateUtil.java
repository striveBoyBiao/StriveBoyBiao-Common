package com.zizhuling.common.util;

import com.zizhuling.common.constant.SystemConstants;
import com.zizhuling.common.exception.ImsException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 系统时间工具类
 * </p>
 *
 * @author hebiao Create on2019/8/26
 * @version 1.0
 */
public class SystemDateUtil extends DateUtil {

    /**
     * 时间格式 年月日时分秒
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm:ss:SSS
     */
    public static final String DATE_FORMAT_SSS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式导出使用 年月日时分秒
     */
    public static final String DATE_FORMAT_FOR_EXPORT = "yyyyMMddHHmmss";
    /**
     * 时间格式导出使用 年月日
     */
    public static final String DATE_FORMAT_FOR_Y_M_D = "yyyyMMdd";

    /**
     * 时间格式 年月日
     */
    public static final String DATE_FORMAT_Y_M_D = "yyyy-MM-dd";

    public static final String DATE_FORMAT_Y_M = "yyyyMM";

    /**
     * 日期格式 YYYY-MM-DD 正则
     */
    public static final String DATE_REGEX_Y_M_D = "[0-9]{4}-[0-9]{2}-[0-9]{2}";

    /**
     * 日期格式 YYYY-MM 正则
     */
    public static final String DATE_REGEX_Y_M = "[0-9]{4}-[0-9]{2}";

    private static String BEGIN_DAY = "-1-1";
    private static String END_DAY = "-12-31";

    /**
     * h获取两个时间间隔的天数
     *
     * @param start
     * @param end
     * @return
     */
    public static int getIntervalDays(Date start, Date end) {
        Objects.requireNonNull(start, "start date must be not null");
        Objects.requireNonNull(end, "end date must be not null");
        int days = (int) ((end.getTime() - start.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /**
     * 获取两个时间之间所间隔的月份
     *
     * @param start 开始时间
     * @param end   结果时间
     * @return 间隔的月份
     */
    public static int getIntervalMonth(Date start, Date end) {
        Objects.requireNonNull(start, "start date must be not null");
        Objects.requireNonNull(end, "end date must be not null");
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar interim = Calendar.getInstance();
        interim.setTime(end);
        interim.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        if (startCalendar.get(Calendar.DATE) == 1 && interim.get(Calendar.DATE) == 1) {
            return year * 12 + month + 1;
        } else if (startCalendar.get(Calendar.DATE) != 1 && interim.get(Calendar.DATE) == 1) {
            return year * 12 + month;
        } else if (startCalendar.get(Calendar.DATE) == 1 && interim.get(Calendar.DATE) != 1) {
            return year * 12 + month;
        } else {
            return year * 12 + month - 1 < 0 ? 0 : year * 12 + month;
        }
    }

    /**
     * 获取时间中的年月数组
     * 第一位是年，第二位是月.
     * 月份是两位的，01-12
     *
     * @param date 指定时间
     * @return 结果数组
     */
    public static String[] getYearAndMonth(Date date) {
        Objects.requireNonNull(date, "target date must be not null");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }
        return new String[]
                {year, month};
    }

    /**
     * 获取时间中的月份 01--12
     *
     * @param date 指定时间
     * @return 可以阅读的月份
     */
    public static String getHumanMonth(Date date) {
        Objects.requireNonNull(date, "target date must be not null");
        int month = getMonth(date) + 1;
        String value = String.valueOf(month);
        if (value.length() == 1) {
            value = "0" + value;
        }
        return value;
    }

    /**
     * @param date
     * @return java.lang.Integer
     * @description 获取日期中的日
     * @author hebiao
     * @date 2019/9/12 15:00
     */
    public static Integer getDay(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(Calendar.DATE);
        }
    }

    /**
     * @param date
     * @return java.lang.Integer
     * @description 获取日期中的时
     * @author hebiao
     * @date 2019/9/16 19:46
     */
    public static Integer getHour(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(Calendar.HOUR_OF_DAY);
        }
    }

    /**
     * @param date
     * @return java.lang.Integer
     * @description 获取日期中的分
     * @author hebiao
     * @date 2019/9/16 19:47
     */
    public static Integer getMinute(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(Calendar.MINUTE);
        }
    }


    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 转换为时间"yyyy-MM-dd HH:mm:ss:SSS"
     *
     * @param timeMillis
     * @return
     */
    public static String formatTime(long timeMillis, String dateFormat) {
        if(SystemStringUtil.isEmpty(dateFormat)){
            dateFormat = DATE_FORMAT_SSS;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(timeMillis);
        return sdf.format(date);
    }

    /**
     * @param date
     * @return java.lang.String
     * @description Date格式转String
     * @author hebiao
     * @date 2019/9/19 19:25
     */
    public static String dateConvertToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat( SystemConstants.DATE_FORMAT);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * @description: string转日期（yyyy-mm-dd）
     * @param dateStr
     * @return: java.util.Date
     * @author: hebiao
     * @date: 2020/6/12 19:11
     */
    public static Date stringConvertToDate(String dateStr) {
        if (SystemStringUtil.isBlank(dateStr)) {
            return null;
        }

        Date date;
        try {
            date = parseDate(dateStr, SystemConstants.DATE_FORMAT_Y_M_D);
        } catch (ParseException e) {
            throw new ImsException(210036);
        }

        return date;
    }

    /**
     * @param date
     * @param formart
     * @return java.lang.String
     * @description 传时间格式
     * @author hebiao
     * @date 2019/9/26 19:22
     */
    public static String dateConvertToString(Date date, String formart) {
        if (date == null) {
            return null;
        }
        if (SystemStringUtil.isEmpty(formart)) {
            return dateConvertToString(date);
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat(formart);
            String dateString = formatter.format(date);
            return dateString;
        }
    }

    /**
     * @param date
     * @return java.lang.String
     * @description Date格式转String 格式:yyyy-MM-dd
     * @author hebiao
     * @date 2019/9/26 17:28
     */
    public static String dateConvertToStringYMD(Date date) {
        String ymd = dateConvertToString(date, SystemConstants.DATE_FORMAT);
        return ymd;
    }


    /**
     * 获取上个月的第一天
     *
     * @return
     * @author lishang
     */
    public static Date getFirstDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取上个月的最后一天
     *
     * @return
     * @author lishang
     */
    public static Date getLastDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 导出时间格式 yyyyMMddHHmmss
     *
     * @param date
     * @return
     */
    public static String convertDateStringForExport(Date date) {
        return dateConvertToString(date, DATE_FORMAT_FOR_EXPORT);
    }

    /**
     * 生成时间格式 yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String getDateString(Date date) {
        return dateConvertToString(date, DATE_FORMAT_FOR_Y_M_D);
    }

    /**
     * 获取时间段之间的所有月份集合
     *
     * @param minDate 201901
     * @param maxDate 202012
     */
    public static List<String> getDateInfo(String minDate, String maxDate) {
        ArrayList<String> result = new ArrayList<>();
        //格式化为年月
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_Y_M);

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        try {
            min.setTime(sdf.parse(minDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(sdf.parse(maxDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
        } catch (ParseException e) {
            LoggerFactory.getLogger( SystemDateUtil.class).error(e.toString());
        }

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    /**
     * yyyyMM拼接yyyy年MM月
     *
     * @param list
     * @return
     */
    public static List<String> subStringDateString(List<String> list) {
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }
        StringBuilder sb;
        List<String> resultList = new ArrayList<>();
        for (String s : list) {
            sb = new StringBuilder();
            resultList.add(String.valueOf(sb.append(s).insert(4, "-")));
        }
        return resultList;
    }

    /**
     * 获取i月之前的时间
     *
     * @param date
     * @param i
     * @return
     * @author wangsongtao
     */
    public static Date getAgoDate(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -i + 1);
        return c.getTime();
    }


    /**
     * 时间添加天数
     *
     * @param date
     * @param i
     * @return
     * @author hebiao
     */
    public static Date addDays(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, i);
        return c.getTime();
    }

    /**
     * 对指定时间进行格式化字符串
     *
     * @param target  指定时间
     * @param pattern 格式化
     * @return 格式化后的字符串
     * @see SystemConstants
     */
    public static String formateDate(Date target, String pattern) {
        if (null == target) {
            return null;
        }
        String usePattern = pattern;
        if (SystemStringUtil.isEmpty(usePattern)) {
            usePattern = SystemConstants.DATE_FORMAT_Y_M_D;
        }
        FastDateFormat fastDateFormat = FastDateFormat.getInstance(usePattern);
        return fastDateFormat.format(target);
    }

    /**
     * 将字符串格式 YYYY-MM-DD 或 YYYY-MM 格式化为YYYYMM
     *
     * @param target 指定时间
     * @return 格式化后的字符串
     * @author hebiao
     */
    public static String formateYM(String target) {
        target = target.trim();
        if (StringUtil.isBlank(target) || target.length() < 7 || !(dateValidate(target, DATE_REGEX_Y_M_D) || dateValidate(target, DATE_REGEX_Y_M))) {
            throw new ImsException(210036);
        }
        return target.substring(0, 4) + target.substring(5, 7);
    }

    /**
     * 将字符串格式 YYYYMMDD 或 YYYYMM 格式化为YYYY-MM
     *
     * @param target 指定时间
     * @return 格式化后的字符串
     * @author hebiao
     */
    public static String formateY_M(String target) {
        target = target.trim();
        if (StringUtil.isBlank(target) || target.length() < 6 || !StringUtils.isNumeric(target)) {
            throw new ImsException(210036);
        }
        return target.substring(0, 4) + "-" + target.substring(4, 6);
    }

    /**
     * 将字符串格式YYYYMMDD格式化为YYYY-MM-DD
     *
     * @param target 指定时间
     * @return 格式化后的字符串
     * @author hebiao
     */
    public static String formateYMD(String target) {
        target = target.trim();
        if (StringUtil.isBlank(target) || target.length() != 8 || !StringUtils.isNumeric(target)) {
            throw new ImsException(210036);
        }
        return target.substring(0, 4) + "-" + target.substring(4, 6) + "-" + target.substring(6);
    }

    /**
     * 将字符串格式 YYYY-MM-DD 格式化为 YYYYMMDD
     *
     * @param target 指定时间
     * @return 格式化后的字符串
     * @author hebiao
     */
    public static String formateY_M_D(String target) {
        target = target.trim();
        if (StringUtil.isBlank(target) || target.length() != 10 || !dateValidate(target, DATE_REGEX_Y_M_D)) {
            throw new ImsException(210036);
        }
        return target.substring(0, 4) + target.substring(5, 7) + target.substring(8);
    }

    /**
     * 日期格式正则校验
     *
     * @param target
     * @return
     * @author hebiao
     */
    private static boolean dateValidate(String target, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(target);
        return m.matches();
    }


    /**
     * 判断开始时间小于等于截至时间
     *
     * @param startStr
     * @param endStr
     * @param parseRegex
     * @return
     * @author hebiao
     */
    public static void judgeDate(String startStr, String endStr, String parseRegex) {
        try {
            Date startDate = SystemDateUtil.parseDate(startStr, parseRegex);
            Date endDate = SystemDateUtil.parseDate(endStr, parseRegex);
            judgeDate(startDate, endDate);
        } catch (ParseException e) {
            throw new ImsException(210036);
        }
    }

    /**
     * 判断开始时间小于等于截至时间
     *
     * @param startDate
     * @param endDate
     * @return
     * @author hebiao
     */
    public static void judgeDate(Date startDate, Date endDate) {
        long t = startDate.getTime() - endDate.getTime();
        if (t > 0) {
            throw new ImsException(210037);
        }
    }

    /**
     * @param year 年份字符串
     * @param size 集合的长度
     * @description: 获取一个指定长度的年份的字符串数组
     * @author: hebiao
     * @date: 2020/4/15 16:49
     * @return: java.util.List<java.lang.String>
     */
    public static List<String> getYearList(String year, int size){
        int yearTemp=0;
        if(SystemStringUtil.isEmpty(year)){
            yearTemp = getYear(new Date());
        }else{
            yearTemp = Integer.parseInt(year);
        }
        int sizeTemp=size-1;
        List<String> yearArr=new ArrayList<>(sizeTemp+1);
        for (int i = sizeTemp; i >= 0; i--) {
            yearArr.add(String.valueOf(yearTemp-i));
        }
        return yearArr;
    }


    /**
     * @param statYear 年份
     * @description: 获取对应年份的所有月份
     * @return: java.util.List<java.lang.String> yyyyMM
     * @author: hebiao
     * @date: 2020/4/15 19:18
     */
    public static List<String> getMonthsByYear(String statYear) {
        if (SystemStringUtil.isBlank(statYear)) {
            return null;
        }
        List<String> result = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(statYear);
            if (i < 10) {
                sb.append("0");
            }
            result.add(sb.append(i).toString());
        }

        return result;
    }

    /**
     * @param statYear 年份
     * @description: 获取对应年份的所有月份（本年度截止到当前月）
     * @return: java.util.List<java.lang.String> yyyyMM
     * @author: hebiao
     * @date: 2020/4/15 19:18
     */
    public static List<String> getMonthsByYearToNow(String statYear) {
        if (SystemStringUtil.isBlank(statYear)) {
            return null;
        }
        List<String> result = new ArrayList<>();

        String year = String.valueOf(LocalDate.now().getYear());
        int count = year.equals(statYear) ? LocalDate.now().getMonthValue() : 12;

        for (int i = 1; i <= count; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(statYear);
            if (i < 10) {
                sb.append("0");
            }
            result.add(sb.append(i).toString());
        }

        return result;
    }

    /**
     * @param yearMon yyyyMM字符串
     * @description: 将yyyyMM转为yyyy年MM月
     * @return: java.lang.String yyyy年MM月 格式字符串
     * @author: hebiao
     * @date: 2020/4/15 20:30
     */
    public static String getYearAndMonthChineseFromString(String yearMon) {
        StringBuilder sb = new StringBuilder();
        sb.append(yearMon).insert(4, "年").append("月");

        return sb.toString();
    }

    /**
     * @param monthList yyyyMM格式数组
     * @description: 将yyyyMM转为yyyy年MM月
     * @return: java.util.List<java.lang.String> yyyy年MM月格式数组
     * @author: hebiao
     * @date: 2020/4/15 20:09
     */
    public static List<String> getYearAndMonthChineseFromList(List<String> monthList) {
        if (CollectionUtil.isEmpty(monthList)) {
            return null;
        }

        List<String> result = new ArrayList<>();
        for (String yearMon : monthList) {
            result.add(getYearAndMonthChineseFromString(yearMon));
        }

        return result;
    }

    /**
     * @param year
     * @description: 根据年份获取当年结束时间
     * @author: hebiao
     * @date: 2020/3/31 15:44
     * @return: java.util.Date
     */
    public static Date getYearEndTime(String year) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( SystemConstants.DATE_FORMAT_Y_M_D);
        Date date = null;
        try {
            date = SystemDateUtil.getDayEndDateTime(simpleDateFormat.parse(year + END_DAY));
        } catch (ParseException e) {
            LoggerFactory.getLogger( SystemDateUtil.class).error(e.toString());
        }
        return date;
    }

    /**
     * @param year
     * @description: 根据年份获取当年起始时间
     * @author: hebiao
     * @date: 2020/3/31 15:44
     * @return: java.util.Date
     */
    public static Date getYearBeginTime(String year) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( SystemConstants.DATE_FORMAT_Y_M_D);
        Date date = null;
        try {
            date = SystemDateUtil.getDayBeginDateTime(simpleDateFormat.parse(year + BEGIN_DAY));
        } catch (ParseException e) {
            LoggerFactory.getLogger( SystemDateUtil.class).error(e.toString());
        }
        return date;
    }

    /**
     * @description: 计算年龄
     * @author: hebiao
     * @param birthDay
     * @date: 2020/5/27 17:10
     * @return: int
     */
    public static int getAgeByBirth(Date birthDay) {
        Calendar cal = Calendar.getInstance();
        //当前年份
        int yearNow = cal.get(Calendar.YEAR);
        //当前月份
        int monthNow = cal.get(Calendar.MONTH);
        //当前日期
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //计算整岁数
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth){
                    //当前日期在生日之前，年龄减一
                    age--;
                }
            } else {
                //当前月份在生日之前，年龄减一
                age--;
            }
        }
        return age;
    }






}
