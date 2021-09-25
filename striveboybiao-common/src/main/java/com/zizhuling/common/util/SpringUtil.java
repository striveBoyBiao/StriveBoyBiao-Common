package com.zizhuling.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    private SpringUtil() {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        Assert.notNull(applicationContext, "SpringUtil injection ApplicationContext is null");
        this.applicationContext = applicationContext;
    }

    public static Object getBean(String beanName) {
        try {
            return applicationContext.getBean(beanName);
        } catch (BeansException e) {
            return null;
        }
    }

    public static <T> T getBean(Class<T> requiredType) {
        try {
            return applicationContext.getBean(requiredType);
        } catch (BeansException e) {
            return null;
        }
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) {
        try {
            return applicationContext.getBean(beanName, requiredType);
        } catch (BeansException e) {
            return null;
        }
    }

    public static Object getBean(String beanName, Object... objects) {
        try {
            return applicationContext.getBean(beanName, objects);
        } catch (BeansException e) {
            return null;
        }
    }

    public static <T> T getBean(Class<T> requiredType, Object... objects) {
        try {
            return applicationContext.getBean(requiredType, objects);
        } catch (BeansException e) {
            return null;
        }
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> requiredType) {
        try {
            return applicationContext.getBeansOfType(requiredType);
        } catch (BeansException e) {
            return null;
        }
    }
}
