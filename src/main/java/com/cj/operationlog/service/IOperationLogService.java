package com.cj.operationlog.service;

import com.cj.operationlog.service.annoiation.OperationLog;
import com.cj.operationlog.service.vo.OperationLogVO;

import java.util.List;

/**
 * @description:
 * @author: sakana
 * @date: 2024/7/28 6:56
 * @version: 1.0
 */
public interface IOperationLogService {
    void saveOperationLog(List<OperationLogVO> operationLogVO);
}
