package com.zizhuling.common.util;

import com.zizhuling.common.constant.SystemConstants;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.comparators.ComparableComparator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *    list工具
 * </p>
 *
 * @author hebiao Create on2019/8/26
 * @version 1.0
 */
public class ListUtil {


    /**
     * list集合查询条件大小限制
     */
    private static final int MAX_LIST_SIZE = 1000;

    /**
     * 将 Page 转成 List<Map>
     * @param page 待转换数据
     * @param <T> 泛型
     * @return List<Map> 数据
     */
    public static <T> List<Map<String, Object>> page2ListMap(Page<T> page){
        return listObject2ListMap(page);
    }

    /**
     * 将 List<Object> 转成 List<Map>
     * @param list 待转换数据
     * @param <T> 泛型
     * @return List<Map> 数据
     */
    public static <T> List<Map<String, Object>> listObject2ListMap(List<T> list){
        List<Map<String,Object>> maps = new ArrayList<>();
        list.forEach(item -> {
            Map<String,Object> map = (Map<String, Object>) JSON.toJSON(item);
            maps.add(map);
        });
        return maps;
    }

    /**
     * @description: map中key由下划线转为驼峰
     * @param dataMap map集合
     * @return: void
     * @author: hebiao
     * @date: 2020/4/28 18:29
     */
    public static Map<String, Object> parseMapToHumpFromUnderline(Map<String, Object> dataMap) {
        if(dataMap == null || dataMap.isEmpty()){
            return null;
        }

        Map<String, Object> resultMap = new HashMap<>();
        for (String key : dataMap.keySet()) {
            resultMap.put( FieldNameUtil.uncapitalize( FieldNameUtil.convertToCamelCase(key)), dataMap.get(key));
        }

        return resultMap;
    }

    /**
     * @description: map转对象
     * @param dataMap map集合
     * @param cls 类
     * @return: T 对象
     * @author: hebiao
     * @date: 2020/4/28 18:28
     */
    public static <T> T parseObjectFromMap(Map<String, Object> dataMap, Class<T> cls){
        return (dataMap == null || dataMap.isEmpty()) ? null : JSON.parseObject(JSON.toJSONString(dataMap), cls);
    }

    /**
     * @description:
     * @param o1 对象1
     * @param o2 对象2
     * @param orderColumn 排序字段
     * @param orderType 排序方式
     * @return: int 比较结果
     * @author: hebiao
     * @date: 2020/5/13 14:59
     */
    public static int compare(Object o1, Object o2, String orderColumn, String orderType) {
        try {
            Object value1 = PropertyUtils.getProperty(o1, orderColumn);
            Object value2 = PropertyUtils.getProperty(o2, orderColumn);
            if(SystemConstants.SORT_TYPE_DESC.equals(orderType)){
                return ComparableComparator.getInstance().compare(value2, value1);
            }
            return ComparableComparator.getInstance().compare(value1, value2);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("IllegalAccessException: " + e.toString());
        } catch (InvocationTargetException e) {
            throw new RuntimeException("InvocationTargetException: " + e.toString());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("NoSuchMethodException: " + e.toString());
        }
    }
}
