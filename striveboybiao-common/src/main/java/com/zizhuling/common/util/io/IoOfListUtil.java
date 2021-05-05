package com.zizhuling.common.util.io;

/**
 *
 * @author hebiao Created on 2019/10/3017:24
 * @version 1.0
 */
public class IoOfListUtil {

    /**
     * @description 包装数组
     *
     * @param original
     * @return java.lang.Byte[]
     * @author hebiao
     * @date 2019/10/30 19:09
     */
    public static Byte[] toWrap(byte[] original) {
        int length = original.length;
        Byte[] dest = new Byte[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i];
        }
        return dest;
    }
}
