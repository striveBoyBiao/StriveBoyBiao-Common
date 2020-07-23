package com.zizhuling.common.util;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * <p>
 * 验证的工具类
 * </p>
 *
 * @author Fang Kun Created on 2019/8/2714:27
 * @version 1.0
 * @modified By:lishang 删除冗余方法
 */
public class ValidationUtil {
	// 正则

	/**
	 *手机号
	 */
	private final static Pattern REGEX_MOBILE_PHONE = Pattern.compile("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$");
	/**
	 *邮箱
	 */
	private final static String REGEX_MOBILE_EMAIL = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
	/**
	 *身份证
	 */
	private final static String REGEX_MOBILE_IDCARD = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9X]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
	/**
	 *金额
	 */
	private static final Pattern PATTERN_AMT = Pattern.compile("(^[-]?([1-9]\\d{0,13}|0)(\\.\\d{1,2})?$)");
	/**
	 *座机号码 带区号
	 */
	private static final Pattern PHONE_WITH_AREA_CODE = Pattern.compile("^(010|[0][1-9]{2,3})-[0-9]{5,10}$");
	/**
	 *不带区号
	 */
	private static final Pattern PHONE = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");

	/**
	 * @param str
	 * @return boolean
	 * @description 验证这个字符串是不是手机号或座机号码
	 * @author Fang Kun
	 * @date 2019/9/14 8:51
	 * @modify gengxiu
	 * @date 2019/12/31
	 */
	public static boolean checkPhoneNum(String str) {
		if (null == str) {
			return false;
		}
		if (REGEX_MOBILE_PHONE.matcher(str).find() || PHONE.matcher(str).find() || PHONE_WITH_AREA_CODE.matcher(str).find()) {
			return true;
		}
		return false;
	}

	/**
	 * @param str
	 * @return boolean
	 * @description 验证邮箱
	 * @author Fang Kun
	 * @date 2019/9/14 9:58
	 */
	public static boolean checkEmail(String str) {
		if (SystemStringUtil.isEmpty(str)) {
			return false;
		} else {
			str = str.trim();
			boolean matches = str.matches(REGEX_MOBILE_EMAIL);
			return matches;

		}
	}

	/**
	 * @param idNumber
	 * @return boolean
	 * @description 针对身份证的验证, 一代身份证为15位, 二代身份证为18位, 同时最后一位可能为X
	 * @author Fang Kun
	 * @date 2019/9/14 11:04
	 */
	public static boolean isIdNumber(String idNumber) {
		if (SystemStringUtil.isEmpty(idNumber)) {
			return false;
		}
		idNumber = idNumber.trim();
		if (idNumber.length() != 15 && idNumber.length() != 18) {
			return false;
		} else {
			boolean matches = idNumber.matches(REGEX_MOBILE_IDCARD);
			if (matches && idNumber.length() == 18) {

				char[] charArray = idNumber.toCharArray();
				int[] idCardWi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
				String[] idCardY = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
				int sum = 0;
				for (int i = 0; i < idCardWi.length; i++) {
					int current = Integer.parseInt(String.valueOf(charArray[i]));
					int count = current * idCardWi[i];
					sum += count;
				}
				char idCardLast = charArray[17];
				int idCardMod = sum % 11;
				if (idCardY[idCardMod].toUpperCase(Locale.ENGLISH).equals(String.valueOf(idCardLast).toUpperCase(Locale.ENGLISH))) {
					return true;
				} else {
					return false;
				}
			}
			return matches;
		}
	}

	/**
	 * @param str   要判断的字符串
	 * @param start 字符串的范围开始
	 * @param end   字符串的的范围结束
	 * @return boolean
	 * @description 判断字段是不是空的, 长度是否满足范围
	 * @author Fang Kun
	 * @date 2019/9/14 11:27
	 */
	public static boolean checkStringLength(String str, int start, int end) {
		if (SystemStringUtil.isEmpty(str)) {
			return false;
		}
		str = str.trim();
		if (str.length() < start) {
			return false;
		}
		if (str.length() > end) {
			return false;
		}
		return true;
	}

	/**
	 * 检出整数的值范围
	 *
	 * @param value
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public static boolean checkIntegerRange(Integer value, int minValue, int maxValue) {
		if (value == null) {
			return false;
		}
		if (value < minValue) {
			return false;
		}
		if (value > maxValue) {
			return false;
		}
		return true;
	}

	/**
	 * 字符串可为null，验证长度
	 *
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean checkStringLengthWithNull(String str, int start, int end) {
		if (SystemStringUtil.isEmpty(str)) {
			return false;
		}
		if (str.length() < start) {
			return true;
		}
		if (str.length() > end) {
			return true;
		}
		return false;
	}

	/**
	 * 校验字符串最大长度，
	 *
	 * @param value
	 * @param maxLength
	 * @return 如果不符合，返回fasle
	 */
	public static boolean checkMaxStringLength(String value, int maxLength) {
		if (value != null && value.length() > maxLength) {
			return false;
		}
		return true;
	}

	/**
	 * @description: 校验金额
	 * @param: [target]
	 * @return: boolean
	 * @author: wangaogao
	 * @date: 2019/10/16 16:51
	 */
	public static boolean validateBigdecimalAmt(BigDecimal target) {
		return target != null && PATTERN_AMT.matcher(target.toString()).find();
	}

	/**
	 * @description: 校验金额
	 * @param: [target]
	 * @return: boolean
	 * @author: wangaogao
	 * @date: 2019/10/16 16:51
	 */
	public static boolean validateBigdecimalAmtWithNull(BigDecimal target) {
		return target == null || PATTERN_AMT.matcher(target.toString()).find();
	}

}
