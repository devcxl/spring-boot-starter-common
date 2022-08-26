package cn.devcxl.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <a href="https://cloud.tencent.com/developer/article/1806245">线程池配置参考</a>
 * <a href="https://segmentfault.com/a/1190000040723931">Spring Boot中如何配置线程池拒绝策略，妥善处理好溢出的任务</a>
 *
 * @author devcxl
 */
@Slf4j
@ConditionalOnProperty(
        prefix = "spring.common.task",
        name = {"core-pool-size", "max-pool-size", "queue-capacity"}
)
@Configuration
@EnableAsync
@EnableConfigurationProperties({ThreadPoolConfigProperties.class})
public class ThreadPoolConfig {

    @Resource
    private ThreadPoolConfigProperties properties;

    @PostConstruct
    public void init() {
        int corePoolSize = properties.getCorePoolSize();
        int maxPoolSize = properties.getMaxPoolSize();
        int queueCapacity = properties.getQueueCapacity();
        String poolNamePrefix = properties.getPoolNamePrefix();
        log.info("ThreadPoolTaskExecutor:[{}] core:{},max:{} queue:{}", poolNamePrefix, corePoolSize, maxPoolSize, queueCapacity);
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor poolExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        poolExecutor.setCorePoolSize(properties.getCorePoolSize());
        // 最大线程数
        poolExecutor.setMaxPoolSize(properties.getMaxPoolSize());
        // 队列大小
        poolExecutor.setQueueCapacity(properties.getQueueCapacity());
        // 线程最大空闲时间
        poolExecutor.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        // 拒绝策略
        poolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程名称前缀
        poolExecutor.setThreadNamePrefix(properties.getPoolNamePrefix());
        return poolExecutor;
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        // 核心线程数
        threadPoolTaskScheduler.setPoolSize(properties.getCorePoolSize());
        // 拒绝策略
        threadPoolTaskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程前缀
        threadPoolTaskScheduler.setThreadNamePrefix("Scheduled-");

        //调度器shutdown被调用时等待当前被调度的任务完成
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        //等待时长
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);

        threadPoolTaskScheduler.initialize();
        return threadPoolTaskScheduler;
    }


}
