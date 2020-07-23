package com.zizhuling.common.util;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.ArrayUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p>
 * 提供精确的浮点计算
 * </p>
 *
 * @author hebiao Create on 2019-10-15 10:16:34
 * @version 1.0
 */
public class WmathUtil {

    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 10;

    private static final int NORMAL_SCALE = 2;


    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2).doubleValue();
    }


    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }


    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.multiply(b2).doubleValue();
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
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(String v, int scale) {
        if (scale < 0) {
            scale = DEF_DIV_SCALE;
        }
        BigDecimal b = new BigDecimal(v);
        return b.divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 提供精确的舍入模式的计算（保留2位小数）
     *
     * @param v 需要舍入入的数字
     * @return 保留的结果
     * 注：BigDecimal的构造方法中的double/float入参构造时，会出现精度问题（并不是bigdecimal的问题），</br>
     * 例如new BigDecimal(1.9d).toString() != 1.9
     */
    public static double round(String v) {
        if (StrUtil.isEmpty(v)) {
            return 0d;
        }
        return NumberUtil.round(v, NORMAL_SCALE, RoundingMode.DOWN).doubleValue();
    }


    /**
     * 提供小数位处理
     *
     * @param d            需要处理的数据
     * @param scale        保留的精度
     * @param roundingMode 保留策略，例如，完全舍入模式（RoundingMode.DOWN），四舍五入（RoundingMode.HALF_UP，
     *                     RoundingMode.HALF_DOWN）
     * @return double
     * @author caikang
     */
    public static double round(double d, int scale, RoundingMode roundingMode) {
        if (scale < 0) {
            scale = NORMAL_SCALE;
        }

        if (roundingMode == null) {
            roundingMode = RoundingMode.HALF_UP;
        }
        BigDecimal b = new BigDecimal(String.valueOf(d));

        return b.setScale(scale, roundingMode).doubleValue();
    }


    /**
     * double类型 转 字符串 （显示用）
     *
     * <pre>
     * 12345678 => 12345678
     * 12345678.1000d => 12345678.1
     * 12345678.1456d => 12345678.15
     * </pre>
     *
     * @param d 需要格式化的double
     * @return double类型的字符串
     * @author yuxiangtong
     */
    public static String double2String(double d) {
        String result = double2StringOfScale(d, 2);
        return result;
    }

    /**
     * double转字符串，如果生成的字符串是整数，则去掉小尾巴
     *
     * @param d
     * @return
     */
    public static String double2String2(double d) {
        return NumberUtil.toStr(Double.parseDouble(double2String(d)));
    }


    /**
     * double类型 转 字符串 （显示用）
     *
     * <pre>
     * 12345678 => 12345678
     * 12345678.1000d => 12345678.1
     * 12345678.1456d => 12345678.15
     * </pre>
     *
     * @param d     需要格式化的double
     * @param scale 精确的精度
     * @return double类型的字符串
     * @author yuxiangtong
     */
    public static String double2StringOfScale(double d, int scale) {
        if (scale < 0) {
            scale = 2;
        }
        double dt = WmathUtil.round(d, scale);
        BigDecimal bigDecimal = BigDecimal.valueOf(dt);
        String result = bigDecimal.toString();
        return result;
    }


    /**
     * 提供精确的小数位四舍五入处理。默认两位小数
     *
     * @param v 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static double roundDefault(double v) {
        return round(v, NORMAL_SCALE);
    }


    /**
     * 小数转化成百分比。默认两位小数
     *
     * @param v 需要转化成百分比的数字
     * @return 转化成百分比的结果
     */
    public static String getPercentValue(double v) {
        return round(100 * v, NORMAL_SCALE) + "%";
    }


    /**
     * 取两个数中较小的数
     *
     * @param v1 参数1
     * @param v2 参数2
     * @return 较小的数
     */
    public static double getMinValue(double v1, double v2) {
        return sub(v1, v2) > 0 ? v2 : v1;
    }


    /**
     * 取两个数中较大的数
     *
     * @param v1 参数1
     * @param v2 参数2
     * @return 较大的数
     */
    public static double getMaxValue(double v1, double v2) {
        return sub(v1, v2) > 0 ? v1 : v2;
    }


    /**
     * 判断字符串是否为double型
     *
     * @param v1 参数
     * @return true：成功 false：失败
     */
    public static boolean isDouble(String v1) {
        if (StrUtil.isEmpty(v1) == true) {
            return false;
        }
        try {
            Double.parseDouble(v1);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }


    /**
     * 判断字符串是否为整数型
     *
     * @param v1 参数
     * @return true：成功 false：失败
     */
    public static boolean isInteger(String v1) {
        if (StrUtil.isEmpty(v1) == true) {
            return false;
        }

        try {
            Integer.parseInt(v1);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     * 向上取整
     *
     * <pre>
     * 0.111  -> 1
     * 10.00  -> 10
     * 10.001 -> 11
     * </pre>
     *
     * @param v1
     * @return
     */
    public static int ceil(double v1) {
        Double v2 = Math.ceil(v1);
        return v2.intValue();
    }


    /**
     * 计算总金额
     *
     * @param cost
     * @return 总金额
     */
    public static double getCost(String... cost) {
        double totalCost = 0;
        double fee = 0;
        if (cost == null || cost.length == 0) {
            return totalCost;
        }

        for (String s : cost) {
            if (StrUtil.isEmpty(s)) {
                fee = 0;
            } else {
                try {
                    fee = Double.parseDouble(s);
                } catch (Exception e) {
                    fee = 0;
                }
            }

            totalCost = totalCost + fee;
        }
        return totalCost;
    }


    /**
     * 计算总数量
     *
     * @param amount
     * @return 总数量
     */
    public static int getAmount(String... amount) {
        int totalAmount = 0;
        int count = 0;
        if (amount == null || amount.length == 0) {
            return totalAmount;
        }

        for (String s : amount) {
            if (StrUtil.isEmpty(s)) {
                count = 0;
            } else {
                try {
                    count = Integer.parseInt(s);
                } catch (Exception e) {
                    count = 0;
                }
            }

            totalAmount = totalAmount + count;
        }
        return totalAmount;
    }


    /**
     * 比较金额大小
     *
     * @param cost1 金额1
     * @param cost2 金额2
     * @return true：金额1>=金额2 false：金额1<金额2
     */
    public static boolean compareCost(String cost1, String cost2) {
        /* 金额为空时判断 */
        if (StrUtil.isEmpty(cost2)) {
            return true;
        } else if (StrUtil.isEmpty(cost1)) {
            return false;
        }

        try {
            double cost3 = Double.parseDouble(cost1);
            double cost4 = Double.parseDouble(cost2);
            if (cost3 >= cost4) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }


    /**
     * 提供加法运算。
     *
     * @param intArray
     * @return 参数的总和
     */
    public static int sum(int[] intArray) {
        if (ArrayUtils.isEmpty(intArray)) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < intArray.length; i++) {
            sum = sum + intArray[i];
        }
        return sum;
    }

    /**
     * 计算百分比的数字(向下取整)
     * @param a
     * @param b
     * @param scale 小数点保留几位
     * @return
     */
    public static Integer div(Integer a, Integer b, int scale, int mulNums) {
        if(a == null || b == null || b.intValue() == 0) {
            return null;
        }

        if(a.intValue() == 0) {
            return 0;
        }

        Double d3 = mul(NumberUtil.div(a, b, scale, RoundingMode.DOWN).doubleValue(), mulNums);
        return d3.intValue();
    }

    public static void main(String[] args) {
        System.out.println(div(30, 384, 2,100));
    }

}
