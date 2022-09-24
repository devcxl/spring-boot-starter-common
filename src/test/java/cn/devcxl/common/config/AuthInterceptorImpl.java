package cn.devcxl.common.config;

import cn.devcxl.common.constant.JwtConstant;
import cn.devcxl.common.interceptor.AuthInterceptor;
import cn.devcxl.common.utils.ThreadLocalUtils;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author devcxl
 */
@Slf4j
@Component
public class AuthInterceptorImpl implements AuthInterceptor {

    private static JWTSigner SIGNER;

    @Value("${jwt.key}")
    private String jwtKey;

    @PostConstruct
    public void init(){
        log.info("load JWT signer Key Successful!");
        log.debug("key:{}",jwtKey);
        SIGNER = JWTSignerUtil.hs256(jwtKey.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public JWTSigner jwtSign() {
        return SIGNER;
    }

    @Override
    public boolean checkPermission(JWT jwt) {
        JSONObject payloads = jwt.getPayloads();
        String name = payloads.getStr("name");
        ThreadLocalUtils.setName(name);
        log.info("checkPermission: {}",name);
        return true;
    }

    public static void main(String[] args) {
        Map<String,Object> payloads = new HashMap<>();
        payloads.put("name","Test");
        payloads.put(JwtConstant.EXPIRATION,new Date().getTime()+1000000000L);
        String token = JWTUtil.createToken(payloads, JWTSignerUtil.hs256("123456789qwertyuiopasdfghjklzxcvbnmA".getBytes(StandardCharsets.UTF_8)));
        System.out.println(token);
    }
}
