package com.jyan.service.impl;

import com.jyan.entity.Log;
import com.jyan.mapper.LogMapper;
import com.jyan.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 江延
 * @since 2020-11-19
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
