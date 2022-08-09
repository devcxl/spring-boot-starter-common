package cn.devcxl.common;

import cn.devcxl.common.annotation.EnableWebLog;
import cn.devcxl.common.annotation.Limit;
import cn.devcxl.common.annotation.NoAuth;
import cn.devcxl.common.annotation.WebLog;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author devcxl
 */
@EnableWebLog
@RestController
@RequestMapping("/test")
public class TestController {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/1")
    @NoAuth
    @WebLog
    @Limit(max = 1, time = 10, timeUnit = TimeUnit.SECONDS)
    public Resp<PageResponse<String>> test(@Validated PageRequest request) {

        String[] strings = {"1a", "2b", "3c", "4d"};

        redisTemplate.opsForValue().increment("test_1");
        PageResponse<String> pageResponse = new PageResponse<>(
                request,
                (long) strings.length,
                Arrays.stream(strings).collect(Collectors.toList())
        );

        return Resp.ok(pageResponse);
    }

}
