package cn.devcxl.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author devcxl
 */
@Data
@ConfigurationProperties(prefix = "spring.common.task")
public class ThreadPoolConfigProperties {


    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;

    private String poolNamePrefix = "task-pool-";
}
