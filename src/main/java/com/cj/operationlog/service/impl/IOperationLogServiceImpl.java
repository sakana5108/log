package com.cj.operationlog.service.impl;

import com.cj.operationlog.mapper.OperationLogMapper;
import com.cj.operationlog.service.IOperationLogService;
import com.cj.operationlog.service.vo.OperationLogVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: sakana
 * @date: 2024/7/28 6:57
 * @version: 1.0
 */
@Slf4j
@Service
public class IOperationLogServiceImpl implements IOperationLogService {
    @Resource
    private OperationLogMapper operationLogMapper;

    @Override
    public void saveOperationLog(List<OperationLogVO> operationLogVO) {
        operationLogMapper.save(operationLogVO);
        log.info("入库{}条", operationLogVO.size());
    }
}
