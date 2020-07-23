package com.zizhuling.common.handler;

import com.zizhuling.common.util.SystemStringUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 数据脱敏
 * </p>
 *
 * @author Hao.Y Created on2020-03-10
 * @version 1.0
 */
public class DataDesensitizedHandler {

    /** 脱敏正则 */
    //private static final String DATA_DESENSITIZED_REGEX = "(\\w{3})\\w*(\\w{4})";
    /**
     * 脱敏替换
     */
    private static final String DATA_DESENSITIZED_REPLACEMENT = "*******";
    /**
     * 替换符号
     */
    private static final String SIGN_STR = "*";

    /**
     * 过敏数据最小长度
     */
    private static final int DATA_DESENSITIZED_MIN_SIZE = 7;

    /**
     * @param name 姓名
     * @description: 姓名脱敏
     * @author: Hao.Y
     * @date: 2020/3/23 17:59
     * @return: java.lang.String 脱敏后的姓名
     */
    public static String desensitizedName(String name) {
        return middleDesensitized(name);
    }


    /**
     * @param socialName 社保卡号
     * @description: 社保卡脱敏
     * @author: Hao.Y
     * @date: 2020/3/23 17:52
     * @return: java.lang.String 脱敏后的社保卡号
     */
    public static String desensitizedSocialNumber(String socialName) {
        if (StringUtils.isNoneBlank(socialName)) {
            if (socialName.length() < DATA_DESENSITIZED_MIN_SIZE) {
                return socialName;
            }
            StringBuilder result = new StringBuilder();
            String prefix = socialName.substring(0, 3);
            String suffix = socialName.substring(socialName.length() - 4);
            result.append(prefix).append(DATA_DESENSITIZED_REPLACEMENT).append(suffix);
            return result.toString();
        }
        return socialName;

    }

    /**
     * @param admdvs 行政区划
     * @description: 行政区划编码脱敏
     * @author: Hao.Y
     * @date: 2020/3/23 18:00
     * @return: java.lang.String 脱敏后的行政区划
     */
    public static String desensitizedAdmdvs(String admdvs) {
        return middleDesensitized(admdvs);
    }

    /**
     * 中间脱敏处理
     *
     * @param targetStr
     * @return
     */
    private static String middleDesensitized(String targetStr) {
        if (SystemStringUtil.isNotBlank(targetStr)) {
            int length = targetStr.length();
            if (length == 2) {
                targetStr = SystemStringUtil.rightPad(targetStr.substring(0, 1), length, SIGN_STR);
            }
            if (length > 2) {
                targetStr = new StringBuilder( SystemStringUtil.rightPad(targetStr.substring(0, 1), length - 1, SIGN_STR)).append(targetStr.substring(length - 1)).toString();
            }
        }
        return targetStr;
    }

    /**
     * @param admdvsName 行政区划名称
     * @description: 行政区划名称脱敏
     * @author: Hao.Y
     * @date: 2020/3/23 18:00
     * @return: java.lang.String 脱敏后的行政区划名称
     */
    public static String desensitizedAdmdvsName(String admdvsName) {
        return middleDesensitized(admdvsName);
    }


    /**
     * @param medinsId 机构编码
     * @description: 机构编码脱敏
     * @author: Hao.Y
     * @date: 2020/3/23 18:01
     * @return: java.lang.String 脱敏后的机构编码
     */
    public static String desensitizedMedinsId(String medinsId) {
        if (StringUtils.isNoneBlank(medinsId)) {
            return StringUtils.left(medinsId, 3).concat(StringUtils.removeStart(
                    StringUtils.leftPad(StringUtils.right(medinsId, medinsId.length() - 8), StringUtils.length(medinsId), "*"), "***"));
        }
        return medinsId;
    }

    /**
     * @param medinsName 机构名称
     * @description: 机构名称脱敏
     * @author: Hao.Y
     * @date: 2020/3/23 17:40
     * @return: java.lang.String 脱敏后的机构名称
     */
    public static String desensitizedMedinsName(String medinsName) {
        if (StringUtils.isNoneBlank(medinsName)) {

            if (medinsName.length() <= DATA_DESENSITIZED_MIN_SIZE) {
                return medinsName;
            }
            StringBuilder result = new StringBuilder();
            String prefix = medinsName.substring(0, 3);
            String suffix = medinsName.substring(medinsName.length() - 4);
            result.append(prefix).append(DATA_DESENSITIZED_REPLACEMENT).append(suffix);
            return result.toString();
        }
        return medinsName;
    }

}
