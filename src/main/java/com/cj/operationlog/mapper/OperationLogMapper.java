package com.cj.operationlog.mapper;

import com.cj.operationlog.service.vo.OperationLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: sakana
 * @date: 2024/7/27 19:57
 * @version: 1.0
 */
@Mapper
public interface OperationLogMapper {
    void save(@Param("operationLogVO") List<OperationLogVO> operationLogVO);
}
