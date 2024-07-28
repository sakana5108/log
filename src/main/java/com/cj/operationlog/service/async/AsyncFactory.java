package com.cj.operationlog.service.async;

import com.cj.operationlog.service.IOperationLogService;
import com.cj.operationlog.service.vo.OperationLogVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @description: 日日志任务工厂
 * @author: sakana
 * @date: 2024/7/28 6:52
 * @version: 1.0
 */
@Component
@Slf4j
public class AsyncFactory {

    public final ArrayBlockingQueue<OperationLogVO> queue = new ArrayBlockingQueue<>(1000);

    @Resource
    private IOperationLogService operationLogService;

    public TimerTask getTask() {
        return new TimerTask() {

            @Override
            public void run() {
                int queueSize = queue.size();
                if (queueSize == 0) return;
                ArrayList<OperationLogVO> list = new ArrayList<>(queueSize);
                while (queueSize > 0) {
                    list.add(queue.poll());
                    queueSize--;
                }
                operationLogService.saveOperationLog(list);
                // operationLogService.saveOperationLog(Arrays.asList(operationLogVO));
            }
        };
    }

    public void insert(OperationLogVO vo) {
        queue.add(vo);
    }
}
