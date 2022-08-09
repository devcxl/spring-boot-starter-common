package cn.devcxl.common;

import cn.devcxl.common.interceptor.AuthInterceptor;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author devcxl
 */
@Component
public class AuthInterceptorImpl implements AuthInterceptor {

    private static final byte[] KEY = "123456789abcdefghijklmnopqrstuvwxyz".getBytes(StandardCharsets.UTF_8);
    private static final JWTSigner SIGNER = JWTSignerUtil.hs256(KEY);

    @Override
    public JWTSigner jwtSign() {
        return SIGNER;
    }

    @Override
    public boolean checkPermission(JWT jwt) {
        return true;
    }
}
