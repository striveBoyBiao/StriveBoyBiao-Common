package com.zizhuling.common.util;


import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * <p>
 *     字符串工具类
 * </P>
 *
 * @author hebiao Create on 2020/7/11 18:10
 * @version 1.0
 */
public class StringUtil extends StringUtils {
    private static Map<String, Integer> CACHEMAP = new LinkedHashMap();
    public static final String UNDERLINE = "_";
    public static final String SPACE_TAB = " ";
    public static final char LF = '\n';
    public static final char CR = '\r';
    public static final String CR_LF = String.valueOf(new char[]{'\r', '\n'});

    public StringUtil() {
    }

    public static Integer getIntegerValue(String str) {
        if (isEmpty(str)) {
            return null;
        } else {
            return CACHEMAP.containsKey(str) ? (Integer)CACHEMAP.get(str) : Integer.parseInt(str);
        }
    }

    public static Double getDoubleValue(String str) {
        return isEmpty(str) ? null : Double.valueOf(str);
    }

    public static String convertToString(Object obj) {
        return convertToString(obj, (String)null);
    }

    public static String convertToString(Object obj, String defaultValue) {
        if (null == obj) {
            return defaultValue;
        } else {
            return obj instanceof String ? (String)obj : defaultValue;
        }
    }

    public static String[] tokenizeToStringArray(String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
        if (isEmpty(str)) {
            return null;
        } else {
            StringTokenizer st = new StringTokenizer(str, delimiters);
            ArrayList tokens = new ArrayList();

            while(true) {
                String token;
                do {
                    if (!st.hasMoreTokens()) {
                        return toStringArray(tokens);
                    }

                    token = st.nextToken();
                    if (trimTokens) {
                        token = token.trim();
                    }
                } while(ignoreEmptyTokens && token.length() <= 0);

                tokens.add(token);
            }
        }
    }

    public static String[] toStringArray(Collection<String> collection) {
        return CollectionUtil.isEmpty(collection) ? null : (String[])collection.toArray(new String[collection.size()]);
    }

    public static String[] removeDuplicateValue(String[] array) {
        if (ArrayUtil.isEmpty(array)) {
            return array;
        } else {
            Set<String> set = new LinkedHashSet();
            String[] var2 = array;
            int var3 = array.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String element = var2[var4];
                set.add(element);
            }

            return toStringArray(set);
        }
    }

    static {
        for(int i = 0; i <= 50; ++i) {
            CACHEMAP.put(String.valueOf(i), i);
        }

    }

}
