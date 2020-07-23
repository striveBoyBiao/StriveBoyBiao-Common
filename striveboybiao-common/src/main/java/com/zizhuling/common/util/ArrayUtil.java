package com.zizhuling.common.util;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * </P>
 *
 * @author hebiao Create on 2020/7/11 18:42
 * @version 1.0
 */
public class ArrayUtil extends ArrayUtils {
    public ArrayUtil() {
    }

    public static <T> T getObject(T[] target, int index) {
        if (isEmpty(target)) {
            return null;
        } else {
            return index < 0 ? null : target[index];
        }
    }

    public static boolean isHasSame(String[] target) {
        if (isEmpty(target)) {
            return false;
        } else {
            Set<String> args = new HashSet();

            for(int i = 0; i < target.length; ++i) {
                String str = target[i];
                if (args.contains(str)) {
                    return true;
                }

                args.add(str);
            }

            return false;
        }
    }
}
