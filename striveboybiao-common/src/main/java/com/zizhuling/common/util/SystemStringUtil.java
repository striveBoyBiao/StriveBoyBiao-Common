package com.zizhuling.common.util;

import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * <p>
 * 系统字符串工具类
 * </p>
 *
 * @author wuchao Create on2019/8/13
 * @version 1.0
 */
public class SystemStringUtil extends StringUtils {
    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new SecureRandom();

    /**
     * @param params 可变数量字符串拼接成sql可以使用的in的语法
     *               "a", "b", "c" ---> ('a', 'b', 'c')
     * @return java.lang.String
     * @description
     * @author wangaogao
     * @date 2019/9/25 15:50
     */
    public static String stringsJoinInSql(String... params) {
        return joinInSql(Arrays.asList(params));
    }

    /**
     * @param target 目标元素
     * @description: 将目标集合的元素拼接成sql可以使用的in的语法
     * <p>['a','b','c'] ---> ('a','b','a')<p/>
     * @author: Fang Kun update
     * @date: 2020/3/23 13:47
     * @return: java.lang.String
     */
    public static String joinInSql(List<String> target) {
        if (CollectionUtil.isEmpty(target)) {
            return null;
        }
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < target.size(); i++) {
            String str = target.get(i);
            sb.append("'").append(str).append("'");
            if (i != target.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }


    /**
     * @param param
     * @return java.lang.String
     * @description 拼接%，用于模糊查询
     * @author gengxiu
     * @date 2019/9/21 11:27
     * @modified By: Fang Kun 修改like变成instr函数, 此方法作用去字符串前后的空格
     */
    public static String buildLikeSql(String param) {
        if (SystemStringUtil.isEmpty(param)) {
            return param;
        }
        return param.trim();
    }

    /**
     * @param param
     * @return java.lang.String
     * @description 组装like的sql语句
     * @author Fang Kun
     * @date 2019/12/6 15:19
     */
    public static String likeSqlHandler(String param) {
        // 如果是个空格, 去空格
        if (StringUtil.isEmpty(param)) {
            return param;
        }
        // 处理 " "
        if (StringUtil.isBlank(param)) {
            return param.trim();
        }
        // 去空格;
        param = param.trim();
        // 如果有 %
        if (param.contains("%")) {
            param = param.replace("%", "\\%");
        }
        // 如果有 _
        if (param.contains("_")) {
            param = param.replace("_", "\\_");
        }
        // 如果有 []
        if (param.contains("[]")) {
            param = param.replace("[]", "\\[]");
        }
        return "%" + param + "%";
    }

    private static Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");

    /**
     * 判断是否有表情
     *
     * @param content
     * @return
     */
    public static boolean hasEmoji(String content) {
        if (isEmpty(content)) {
            return false;
        }
        Matcher matcher = pattern.matcher(content);
        return matcher.find();
    }

    /**
     * @param target 数据 , name  字段名
     *               ['a','b','c','e','f'] --->  name in ('a', 'b', 'c')  or name in ('e','f')
     * @return java.lang.String
     * @description 解决in超过1000报错, 拼接成sql可以使用的in的语法
     * @author hebiao
     * @date 2019/9/25 15:50
     */
    public static String joinInSql(List<String> target, String name) {
        if (CollectionUtil.isEmpty(target)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(name).append(" in (");
        for (int i = 0; i < target.size(); i++) {
            if (i == target.size() - 1) {
                sb.append("'").append(target.get(i)).append("'))");
            } else if ((i + 1) % 400 == 0) {
                sb.append("'").append(target.get(i)).append("'")
                        .append(") or ").append(name).append(" in (");
            } else {
                sb.append("'").append(target.get(i)).append("', ");
            }
        }

        return sb.toString();
    }

    /**
     * @param value 需要处理的对象
     * @description: 转成string，如果是null则返回"" nullTransformationEmpty
     * @author: Fang Kun
     * @date: 2020/3/23 13:48
     * @return: java.lang.String
     */
    public static String nullTransformationEmpty(Object value) {
        if (value == null) {
            return "";
        } else if (value instanceof String) {
            return value.toString();
        } else {
            return String.valueOf(value);
        }
    }

    /**
     * 生成随机字符串
     *
     * @param size 字符串长度
     */
    public static String generateNonce(int size) {
        char[] nonceChars = new char[size];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }


}
