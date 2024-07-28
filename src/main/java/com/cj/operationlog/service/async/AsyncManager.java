package com.cj.operationlog.service.async;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: sakana
 * @date: 2024/7/28 6:51
 * @version: 1.0
 */

@Component
public class AsyncManager {

    @Resource
    private ScheduledExecutorService scheduledExecutorService;

    @Value("${delay-million-seconds}")
    private long delayTime;

    public void execute(TimerTask task) {
        scheduledExecutorService.schedule(task, delayTime, TimeUnit.MILLISECONDS);
    }

    // TODO 关闭线程池代码

}
