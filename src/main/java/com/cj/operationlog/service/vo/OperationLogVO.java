package com.cj.operationlog.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 操作日志对象
 * @author: sakana
 * @date: 2024/7/27 18:07
 * @version: 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationLogVO {
    // id
    private Long id;
    // 操作类型
    private String type;
    // 请求uri
    private String requestUri;
    // 方法名
    private String name;
    // 请求方式
    private String method;
    // 请求参数
    private String params;
    // 响应体
    private String data;
    // 用户id
    private String userId;
    // 请求耗时
    private Long time;
    // 错误信息
    private String errorMessage;
}
