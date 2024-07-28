package com.cj.operationlog.controller;

import com.cj.operationlog.service.annoiation.OperationLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: sakana
 * @date: 2024/7/28 7:46
 * @version: 1.0
 */
@RestController
public class TestController {
    @GetMapping("/test")
    @OperationLog("测试")
    public ResponseEntity<String> test(@RequestParam("name") String name) throws InterruptedException {
        return new ResponseEntity<>("你好" + name, HttpStatus.OK);
    }
}
