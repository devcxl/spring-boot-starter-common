package cn.devcxl.common.interceptor;

import cn.devcxl.common.annotation.Auth;
import cn.devcxl.common.annotation.NoAuth;
import cn.devcxl.common.constant.JwtConstant;
import cn.devcxl.common.exception.UnauthorizedException;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author devcxl
 * 认证拦截
 */
public interface AuthInterceptor extends HandlerInterceptor {

    /**
     * 请求头中的Token名称
     */
    String TOKEN_NAME = "token";

    /**
     * 获取项目的jwt加密密钥
     * eg:
     * private static final byte[] KEY = "123456789abcdefghijklmnopqrstuvwxyz".getBytes(StandardCharsets.UTF_8);
     * private static final JWTSigner SIGNER = JWTSignerUtil.hs256(KEY);
     *
     * @return
     */
    JWTSigner jwtSign();

    /**
     * 检查token判断是否放行
     *
     * @param jwt
     * @return
     */
    boolean checkPermission(JWT jwt);

    /**
     * default preHandle
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    default boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // 存在NoAuth注解的无需认证
            NoAuth annotation = method.getAnnotation(NoAuth.class);
            if (annotation != null) {
                return true;
            }
            String token = request.getHeader(TOKEN_NAME);
            if (StringUtils.hasLength(token)) {
                // TODO: redis查看token是否存在 key

                if (JWTUtil.verify(token, jwtSign())) {
                    JWT jwt = JWTUtil.parseToken(token);
                    JSONObject payloads = jwt.getPayloads();
                    Long exp = payloads.get(JwtConstant.EXPIRATION, Long.class);
                    long l = System.currentTimeMillis() / 1000L;
                    // 是否过期
                    if (exp > l) {
                        // 权限认证
                        Auth auth = method.getAnnotation(Auth.class);
                        if (auth != null) {
                            return checkPermission(jwt);
                        } else {
                            return true;
                        }
                    } else {
                        throw new UnauthorizedException();
                    }
                } else {
                    throw new UnauthorizedException();
                }
            }
            throw new UnauthorizedException();
        } else {
            return false;
        }
    }
}
