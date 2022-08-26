package cn.devcxl.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时任务配置类
 *
 * @author devcxl
 */
@Slf4j
@Component
@EnableScheduling
public class ScheduledConfig implements SchedulingConfigurer {

    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 配置线程池
     *
     * @param taskRegistrar the registrar to be configured.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }


}
