package com.cj.operationlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @description:
 * @author: sakana
 * @date: 2024/7/27 18:06
 * @version: 1.0
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class OperationLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(OperationLogApplication.class, args);
    }
}
