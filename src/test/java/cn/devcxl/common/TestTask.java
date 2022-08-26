package cn.devcxl.common;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @author devcxl
 */
@Slf4j
public class TestTask implements Runnable, Callable<String> {
    private final String orderId;

    public TestTask(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public void run() {
        try {
            call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String call() throws Exception {
        log.info("删除order:{}", orderId);
        return orderId;
    }
}