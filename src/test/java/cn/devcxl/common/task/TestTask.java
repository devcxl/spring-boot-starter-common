package cn.devcxl.common.task;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @author devcxl
 */
@Slf4j
public class TestTask implements Runnable{
    private final String orderId;

    public TestTask(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public void run() {
        try {
            log.info("删除order:{}", orderId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}