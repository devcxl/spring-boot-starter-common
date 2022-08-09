package cn.devcxl.common.exception.enums;

import cn.devcxl.common.exception.interfaces.ErrorCode;

/**
 * @author devcxl
 */
public enum CommonErrorCode implements ErrorCode<CommonErrorCode> {
    /**
     * 错误的请求
     */
    BAD_REQUEST(400, "Bad request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),


    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    TOO_MANY_REQUESTS(429, "Too Many Requests"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    ;

    private int code;
    private String message;

    CommonErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public CommonErrorCode setMessage(String message) {
        this.message = message;
        return this;
    }


}
