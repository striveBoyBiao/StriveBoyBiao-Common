package com.zizhuling.common.util;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * </P>
 *
 * @author hebiao Create on 2020/7/18 18:10
 * @version 1.0
 */
public class DateUtil extends DateUtils {
    public DateUtil() {
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static Integer getYear(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(1);
        }
    }

    public static Integer getMonth(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(2);
        }
    }

    public static Date cloneDate(Date target) {
        return null == target ? null : (Date)target.clone();
    }

    public static Calendar calendar(Date date) {
        return null == date ? null : calendar(date.getTime());
    }

    public static Calendar calendar(long millis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return cal;
    }

    public static boolean logicEquals(Calendar calendar, Calendar target) {
        if (null == calendar) {
            return false;
        } else if (null == target) {
            return false;
        } else {
            return calendar.get(1) == target.get(1) && calendar.get(2) == target.get(2) && calendar.get(5) == target.get(5);
        }
    }

    public static Date getDayBeginDateTime(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            return calendar.getTime();
        }
    }

    public static Date getDayEndDateTime(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            return calendar.getTime();
        }
    }

    public static String getInterval(Date startDate, Date endDate) {
        Objects.requireNonNull(startDate, "startDate is must be not null");
        Objects.requireNonNull(endDate, "endDate is is must be not null");
        long day = 0L;
        long hour = 0L;
        long min = 0L;
        long sec = 0L;
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long interval;
        if (startTime < endTime) {
            interval = endTime - startTime;
        } else {
            interval = startTime - endTime;
        }

        day = interval / 86400000L;
        hour = interval / 3600000L - day * 24L;
        min = interval / 60000L - day * 24L * 60L - hour * 60L;
        sec = interval / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
        return hour + "小时" + min + "分钟" + sec + "秒";
    }
}

