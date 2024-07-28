package com.cj.operationlog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 线程池配置参数
 * @author: sakana
 * @date: 2024/7/27 19:41
 * @version: 1.0
 */

@Data
@Configuration
@ConfigurationProperties("thread-pool")
public class ThreadPoolConfigProperties {
    private int coreSize;
    private int maxSize;
    private int keepAliveTime;
    private int queueCapacity;
}
