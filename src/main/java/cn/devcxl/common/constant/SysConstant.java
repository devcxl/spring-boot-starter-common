package cn.devcxl.common.constant;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * @author devcxl
 */
public class SysConstant {
    /**
     * 逻辑处理器核心数量
     */
    public static final int PROCESSORS = Runtime.getRuntime().availableProcessors();

    /**
     * 主机名称 /etc/hostname
     */
    public static final String HOSTNAME;

    static {
        try {
            HOSTNAME = InetAddress.getLocalHost().getHostName() + "_" + UUID.randomUUID().toString().replace("-", "");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
