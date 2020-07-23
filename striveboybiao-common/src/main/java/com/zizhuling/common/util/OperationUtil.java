package com.zizhuling.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p>
 * 基于复杂度处理和运算的工具类
 * </p>
 * Copyright (C) 2019 Kingstar Winning, Inc. All rights reserved.
 *
 * @author: hebiao Created on 2020/5/22 9:49
 * @version: 1.0
 */
public class OperationUtil {

    /**
     * @description: BigDecimal数值相加，空项默认为0
     * @param val1 加数
     * @param val2 加数
     * @return: java.math.BigDecimal 相加的值
     * @author: wangaogao
     * @date: 2020/5/22 9:53
     */
    public static BigDecimal bigDecimalAdd(BigDecimal val1, BigDecimal val2) {
        if (val1 == null) {
            val1 = BigDecimal.ZERO;
        }
        if (val2 == null) {
            val2 = BigDecimal.ZERO;
        }

        return val1.add(val2);
    }

    /**
     * @description: BigDecimal除法运算
     * @param val1 除数
     * @param val2 被除数
     * @param scale 小数点位数
     * @param roundingMode rounding mode to apply.
     * @return: java.math.BigDecimal 结果值
     * @author: wangaogao
     * @date: 2020/5/26 17:32
     */
    public static BigDecimal bigDecimalDivide(BigDecimal val1, BigDecimal val2, int scale, RoundingMode roundingMode) {
        if (val1 == null) {
            val1 = BigDecimal.ZERO;
        }
        if (val2 == null || val2.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return val1.divide(val2, scale, roundingMode);
    }

    /**
     * @description: BigDecimal去除小数点多余零
     * @param bigDecimal bigDecimal
     * @return: java.math.BigDecimal
     * @author: wangaogao
     * @date: 2020/6/30 18:02
     */
    public static BigDecimal formatBigDecimalTriZero(BigDecimal bigDecimal) {
        if(bigDecimal == null){
            return null;
        }

        return new BigDecimal(bigDecimal.stripTrailingZeros().toPlainString());
    }
}
