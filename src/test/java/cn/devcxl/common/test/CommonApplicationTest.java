package cn.devcxl.common.test;

import cn.devcxl.common.task.TestTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ScheduledFuture;

/**
 * @author devcxl
 */
@Slf4j
@SpringBootTest
public class CommonApplicationTest {

    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            String orderId = UUID.randomUUID().toString();
            ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(new TestTask(orderId), new Date(System.currentTimeMillis() + 10 * 1000));

            log.info("closeOrder:{}", orderId);
        }


    }

}
