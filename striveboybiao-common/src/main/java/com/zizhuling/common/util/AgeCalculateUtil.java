package com.zizhuling.common.util;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 鉴于当前日期计算方式较多，现统一日期计算方式: 1:年龄计算 所有年龄计算都是需要计算当天 年：精确到2位小数 月：精确到2位小数 日：精确到天
 * 2:住院天数计算 2.1 出院日期(天)-住院日期(天)+1 2.2 出院日期(天)-住院日期(天) 备注:若相减为0则天数等于1[当天入出院算1天]
 * 3:间隔天数计算 出院日期(天)-住院日期(天) (例如:分解住院/二次入院) 住院天数，间隔天数都是整数，不存在小数,只有年龄的计算存在小数
 * </p>
 * <p>
 * </p>
 *
 * @author hebiao Create on 2019-10-15 10:16:34
 * @version 1.0
 */
public class AgeCalculateUtil {

    private static final String SUFFIX_NINE = ".9";
    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 10;

    private static final  int NUM_THIRTY = 30;

    /**
     * 根据日期计算时间间隔
     *
     * @param date1 小日期(出生日期)
     * @param date2 大日期(入院日期)
     * @param unit  单位[Y,M,D]
     * @return <br>
     * 正常：返回double <br>
     * 异常:null
     * @author yuxiangtong
     */
    public static Double getAgeByTwoDateToRetainOneDecimal(Date date1, Date date2, String unit) {
        if (date1 == null || date2 == null || date1.after(date2)) {
            return null;
        }
        return getAgeByTwoDateToRetainOneDecimalIgnoreAfter(date1, date2, unit);
    }


    /**
     * 忽略先后顺序计算日期
     *
     * @param date1 日期1
     * @param date2 日期2
     * @param unit  单位[Y,M,D]
     * @return <br>
     * 正常：返回double <br>
     * 异常:null
     * @author caikang
     */
    public static Double getAgeByTwoDateToRetainOneDecimalIgnoreAfter(Date date1, Date date2, String unit) {
        if (date1 == null || date2 == null) {
            return null;
        }

        Double result = null;
        double age = 0;

        // 0岁
        double zeroAge = 0d;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date2);
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
            int dayOfYearNow = cal.get(Calendar.DAY_OF_YEAR);
            long millisNow = cal.getTimeInMillis();

            cal.setTime(date1);
            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
            long millisBirth = cal.getTimeInMillis();
            int dayOfYearBirth = cal.get(Calendar.DAY_OF_YEAR);
            int dayOfLeftBirth = ((yearBirth % 4) == 0 ? 366 : 365) - dayOfYearBirth;

            // 以年为单位计算年龄,计算方式如下： 将年龄分成3段，1：出生日期当年剩余天数;2:整数年之间间隔;3:入院日期在当年的天数+1(计算当天)
            //例如2001-01-02，2002-01-01,那么 1=(365-2),2=2002-2001-1,3:1
            if (StringUtils.equals("Y", unit)) {
                int year = yearNow - yearBirth;
                age = year;
                // 如果是相同年
                if (Double.compare(age, zeroAge) == 0) {
                    double decimal = round(
                            div((double) ((dayOfYearNow - dayOfYearBirth) + 1), (double) ((yearNow % 4) == 0 ? 366 : 365)), 3);
                    return decimal;
                } else {
                    age = age - 1;
                }

                double decimal = div((double) (dayOfLeftBirth + 1), (double) ((yearBirth % 4) == 0 ? 366 : 365))
                        + div((double) dayOfYearNow, (double) ((yearNow % 4) == 0 ? 366 : 365));

                // 计算年龄的小数位
                // 临床建议修复：1）系统年龄算法不变，但精确到小数点后一位(采用进位法，保留一位小数，如，1.11，保留一位后为：1.2；
                // 当小数点后一位已经为9时，不允许再进位，如：1.99，不可再进位，保留为：1.9；而1.81，可以进位为：1.9。)。
                // 1.9999999999
                if (Double.toString(decimal).indexOf(SUFFIX_NINE) > -1) {
                    if (decimal >= 1) {
                        decimal = 1.9;
                    } else {
                        decimal = 0.9;
                    }
                } else {
                    decimal = round(decimal, 3);
                }
                age = age + decimal;
            } // 以月为单位计算年龄
            else if (StringUtils.equals("M", unit)) {

                int year = yearNow - yearBirth;

                double divMonthNow = 0d;
                double divMonthBirth = 0d;
                if (ArrayUtils.contains(getBigMonth(), monthNow)) {
                    divMonthNow = 31;
                } else if (ArrayUtils.contains(getSmallMonth(), monthNow)) {
                    divMonthNow = NUM_THIRTY;
                } else if (ArrayUtils.contains(getFebruary(), monthNow)) {
                    divMonthNow = (yearBirth % 4) == 0 ? 29 : 28;
                }

                if (ArrayUtils.contains(getBigMonth(), monthBirth)) {
                    divMonthBirth = 31;
                } else if (ArrayUtils.contains(getSmallMonth(), monthBirth)) {
                    divMonthBirth = NUM_THIRTY;
                } else if (ArrayUtils.contains(getFebruary(), monthBirth)) {
                    divMonthBirth = (yearBirth % 4) == 0 ? 29 : 28;
                }

                boolean divMonthBirthFlag = Double.compare(divMonthBirth, 0d) == 0 ? true : false;
                if (divMonthBirthFlag) {
                    return null;
                }

                boolean divMonthNowFlag = Double.compare(divMonthNow, 0d) == 0 ? true : false;
                if (divMonthNowFlag) {
                    return null;
                }

                // 同一年，计算整月以及天数的占比
                if (year == 0) {
                    if (monthNow == monthBirth) {
                        age =round(div((double) ((dayOfMonthNow - dayOfMonthBirth) + 1), divMonthNow), 4);
                    } else {
                        double ageleft =div((double) ((divMonthBirth - dayOfMonthBirth) + 1), divMonthBirth)
                                + div(dayOfMonthNow, divMonthNow);
                        age = (monthNow - monthBirth - 1) + round(ageleft, 4);
                    }
                } // 不同年，计算整年+整月+天数占比
                else {
                    year = year - 1;

                    // 31天的月份，1，3，5，7，8，10，12
                    // 30天的月份，4，6，9，11
                    // 2月份单独计算
                    // 判断当月最大天数
                    double ageleft = div((divMonthBirth - dayOfMonthBirth) + 1, divMonthBirth)
                            +div(dayOfMonthNow, divMonthNow);

                    age = year * 12 + (monthNow - 1) + (12 - monthBirth) + round(ageleft, 4);
                }
            } // 以日为单位计算年龄
            else if (StringUtils.equals("D", unit)) {
                age = round(div((double) (millisNow - millisBirth), (double) (1000 * 60 * 60 * 24)), 3) + 1;
            }
            result = Double.valueOf(age);
        } catch (Exception e) {

        }
        return result;

    }



    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            scale = DEF_DIV_SCALE;
        }
        BigDecimal b = BigDecimal.valueOf(v);
        return b.divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算（四舍五入至小数点后10位）。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            scale = DEF_DIV_SCALE;
        }
        if (v1 == 0) {
            return 0;
        }
        if (v2 == 0) {
            v2 = 1;
        }

        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);

        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }




    public static Integer[] getBigMonth() {
        return bigMonth;
    }

    public static Integer[] getSmallMonth() {
        return smallMonth;
    }

    public static Integer[] getFebruary() {
        return february;
    }

    /**大月份，31天的月份，主要包含1，3，5，7，8，10，12月份 */
    private static Integer[] bigMonth = new Integer[]{
            1,
            3,
            5,
            7,
            8,
            10,
            12
    };

    /**小月份，30天的月份，主要包含4，6，9，11月份 */
    private static Integer[] smallMonth = new Integer[]{
            4,
            6,
            9,
            11
    };

    /**二月份 */
    private static Integer[] february = new Integer[]{
            2
    };



}
