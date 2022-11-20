package cn.devcxl.common;

import cn.devcxl.common.annotation.Auth;
import cn.devcxl.common.annotation.NoAuth;
import cn.devcxl.common.constant.SysConstant;
import cn.devcxl.common.test.A;
import cn.devcxl.common.utils.ThreadLocalUtils;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@RestController
@RequestMapping("/test")
public class TestController {

    @Auth
    @GetMapping("/1")
    public String test1() {
        return ThreadLocalUtils.getName();
    }

    @NoAuth
    @GetMapping("/2")
    @A
    public String google() {

        return SysConstant.HOSTNAME;
    }
}
