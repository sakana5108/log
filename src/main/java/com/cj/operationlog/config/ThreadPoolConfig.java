package com.cj.operationlog.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @description: 线程池
 * @author: sakana
 * @date: 2024/7/27 19:38
 * @version: 1.0
 */
@Configuration
public class ThreadPoolConfig {
    @Resource
    private ThreadPoolConfigProperties properties;


    // 普通线程池
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCoreSize());
        executor.setMaxPoolSize(properties.getMaxSize());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setKeepAliveSeconds(properties.getKeepAliveTime());
        return executor;
    }

    // 定时任务线程池
    @Bean
    public ScheduledExecutorService scheduledThreadPoolExecutor() {
        return new ScheduledThreadPoolExecutor(properties.getCoreSize());
    }
}
