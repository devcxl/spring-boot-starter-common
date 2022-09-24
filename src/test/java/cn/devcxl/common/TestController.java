package cn.devcxl.common;

import cn.devcxl.common.annotation.Auth;
import cn.devcxl.common.utils.ThreadLocalUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Auth
    @GetMapping("/1")
    public String test1(){
       return ThreadLocalUtils.getName();
    }
}
