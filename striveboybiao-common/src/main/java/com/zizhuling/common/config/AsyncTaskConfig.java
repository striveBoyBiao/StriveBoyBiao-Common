package com.zizhuling.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * * @description: 公用线程池配置
 * @author: wangaogao
 * @date: Created in 2019/11/8 20:57
 * @version: 1.0
 */
@Configuration
@EnableAsync
@EnableScheduling
public class AsyncTaskConfig implements AsyncConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTaskConfig.class);

    /**
     * @description: 创建线程
     * @param:
     * @return: org.springframework.core.task.AsyncTaskExecutor
     * @author: wangaogao
     * @date: 2019/11/8 21:16
     */
    @Override
    @Bean(name = "asyncExecutor")
    public AsyncTaskExecutor getAsyncExecutor() {
        LOGGER.debug("Creating Async Task Executor");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //当前线程数
        executor.setCorePoolSize(10);
        // 最大线程数
        executor.setMaxPoolSize(600);
        //线程池所使用的缓冲队列大小
        executor.setQueueCapacity(2000);
        //线程最大空闲时间
        executor.setKeepAliveSeconds(30);
        //指定用于新创建的线程名称的前缀。
        executor.setThreadNamePrefix("ims-Async-Executor-");

        //初始化
        executor.initialize();

        return executor;
    }

    /**
     * @description: 自定义异常处理
     * @author: wangaogao
     * @date: Created in 2019/11/8 21:07
     * @version: 1.0
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }
}
