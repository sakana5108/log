package com.cj.operationlog;

import com.cj.operationlog.service.IOperationLogService;
import com.cj.operationlog.service.vo.OperationLogVO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

@SpringBootTest
class OperationLogApplicationTests {

    @Resource
    private IOperationLogService operationLogService;

    @Test
    void contextLoads() {
        OperationLogVO build = OperationLogVO.builder().type("aqa")
                .name("aa")
                .method("aaa")
                .params("aaa")
                .requestUri("aaa")
                .time(10L)
                .data("aaa")
                .errorMessage("aaa")
                .userId("aaa")
                .build();
        OperationLogVO build1 = OperationLogVO.builder().type("aqa")
                .name("aa")
                .method("aaa")
                .params("aaa")
                .requestUri("aaa")
                .time(10L)
                .data("aaa")
                .errorMessage("aaa")
                .userId("aaa")
                .build();
        operationLogService.saveOperationLog(Arrays.asList(build,build1));
    }

}
