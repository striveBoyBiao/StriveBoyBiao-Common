package com.zizhuling.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * <p>
 * Properties 文件的工具类
 * </p>
 *
 * @author hebiao Create on 2020/4/26 16:27
 * @version 1.0
 */
public class PropertiesUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger( PropertiesUtil.class);

    private static final String MESSAGE_STR="propertiesPath path is not must be null";

    /**
     * @description: 读取自定义的properties文件
     *
     * @author: Fang Kun
     * @param propertiesPath Properties文件的路径
     * @date: 2020/4/26 16:30
     * @return: java.util.Properties
     */
    public static Properties getProperties(String propertiesPath){
        Objects.requireNonNull(propertiesPath, MESSAGE_STR);
        Properties propertyFiles = new Properties();
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesPath)){
            propertyFiles.load(inputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return  propertyFiles;
    }

    /**
     * @description: 获取Map<String, Object>类型的map
     *
     * @author: Fang Kun
     * @param propertiesPath
     * @date: 2020/4/26 16:45
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public static Map<String, Object> propertiesMapHandler(String propertiesPath){
        Objects.requireNonNull(propertiesPath, MESSAGE_STR);
        Map<Object, Object> objectObjectMap = propertiesTransferMap(propertiesPath);
        Map<String, Object> map=new HashMap<>();
        for(Map.Entry<Object, Object> entry : objectObjectMap.entrySet()){
            map.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return map;
    }

    /**
     * @description: 获取Map<String, String>类型的map
     *
     * @author: Fang Kun
     * @param propertiesPath
     * @date: 2020/4/26 16:45
     * @return: java.util.Map<java.lang.String,java.lang.String>
     */
    public static Map<String, String> propertiesMapHandlerForStr(String propertiesPath){
        Objects.requireNonNull(propertiesPath, MESSAGE_STR);
        Map<Object, Object> objectObjectMap = propertiesTransferMap(propertiesPath);
        Map<String, String> map=new HashMap<>();
        for(Map.Entry<Object, Object> entry : objectObjectMap.entrySet()){
            map.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return map;
    }


    /**
     * @description: 将Properties转化成map
     *
     * @author: Fang Kun
     * @param propertiesPath
     * @date: 2020/4/26 16:44
     * @return: java.util.Map<java.lang.Object,java.lang.Object>
     */
    private static Map<Object, Object> propertiesTransferMap(String propertiesPath){
        Properties properties = getProperties(propertiesPath);
        return new HashMap<>(properties);
    }
}
