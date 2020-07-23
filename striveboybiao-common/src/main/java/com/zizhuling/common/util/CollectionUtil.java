package com.zizhuling.common.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;

import java.util.*;

/**
 * <p>
 * </P>
 *
 * @author hebiao Create on 2020/7/11 18:12
 * @version 1.0
 */
public class CollectionUtil extends CollectionUtils {
    public CollectionUtil() {
    }

    public static <T> List<T> subList(List<T> list, int start, int end) {
        if (isEmpty(list)) {
            return null;
        } else {
            if (start < 0) {
                start = 0;
            }

            if (end < 0) {
                end = 0;
            }

            int size;
            if (start > end) {
                size = start;
                start = end;
                end = size;
            }

            size = list.size();
            if (end > size) {
                if (start >= size) {
                    return null;
                }

                end = size;
            }

            return list.subList(start, end);
        }
    }

    public static List removeAll(Collection collection, Collection remove) {
        if (isEmpty(collection)) {
            return null;
        } else if (isEmpty(remove)) {
            List result = new ArrayList(collection);
            return result;
        } else {
            return ListUtils.removeAll(collection, remove);
        }
    }

    public static <T> List<T> convertToList(Set<T> target) {
        List<T> result = new LinkedList();
        if (isEmpty(target)) {
            return result;
        } else {
            result.addAll(target);
            return result;
        }
    }

    public static <T> Set<T> convertToSet(List<T> target) {
        Set<T> result = new LinkedHashSet();
        if (isEmpty(target)) {
            return result;
        } else {
            result.addAll(target);
            return result;
        }
    }

    public static <T> void addAll(List<T> list, List<T> itemsToAdd) {
        if (null != list && isNotEmpty(itemsToAdd)) {
            list.addAll(itemsToAdd);
        }

    }

    public static <T> void addAll(List<T> list, Collection<T> itemsToAdd) {
        if (null != list && isNotEmpty(itemsToAdd)) {
            list.addAll(itemsToAdd);
        }

    }

    public static <T> List<T> asList(T... t) {
        if (ArrayUtil.isEmpty(t)) {
            return null;
        } else {
            List<T> result = new LinkedList();

            for(int i = 0; i < t.length; ++i) {
                result.add(t[i]);
            }

            return result;
        }
    }


    public static <T> List<T> toList(List<T> target) {
        return (List)(null == target ? new ArrayList() : target);
    }

    public static <T> T getByIndex(List<T> target, int index) {
        if (isEmpty(target)) {
            return null;
        } else {
            return index < 0 ? null : target.get(index);
        }
    }

    public static <T> T getFirst(List<T> target) {
        return getByIndex(target, 0);
    }

}
