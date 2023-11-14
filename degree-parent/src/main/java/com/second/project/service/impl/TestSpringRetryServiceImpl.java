package com.second.project.service.impl;

import com.second.common.aop.advice.BizException;
import com.second.common.bean.reponse.Result;
import com.second.common.util.DateUtils;
import com.second.project.service.TestSpringRetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/14
 * {@code @description} test spring retry service impl
 */
@Slf4j
@Service
public class TestSpringRetryServiceImpl implements TestSpringRetryService {

    private static final int TOTAL_NUM = 10000;

    @Override
    @Retryable(backoff = @Backoff(delay = 5000L, multiplier = 2))
    public Result<Integer> getRetryNum(int num) {
        log.info("start date : {}", DateUtils.getCurrentDateHms());
        if (num <= 0) {
            throw new BizException("数量不对");
        }
        log.info("end date : {}", DateUtils.getCurrentDateHms());
        return Result.success(TOTAL_NUM - num);
    }

    @Recover
    public Result<Integer> recover(Exception e) {
        log.info("重试发送失败");
        return Result.error("重试发送失败");
    }

}
