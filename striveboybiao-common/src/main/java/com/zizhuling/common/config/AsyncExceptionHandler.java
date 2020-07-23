package com.zizhuling.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * * @description: 自定义异常处理类
 * @author: wangaogao
 * @date: Created in 2019/11/8 21:07
 * @version: 1.0
 */
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncExceptionHandler.class);

    /**
     * @description: 捕获线程异常信息
     * @param: throwable 异常类
     * @param: method 方法名
     * @param: objects 参数对象集合
     * @return: void
     * @author: wangaogao
     * @date: 2019/11/8 21:13
     */
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        LOGGER.debug("-------------》》》捕获线程异常信息");
        LOGGER.debug("Exception message - " + throwable.getMessage());
        LOGGER.debug("Method name - " + method.getName());

        for (Object param : objects) {
            LOGGER.debug("Parameter value - " + param);
        }
    }
}
