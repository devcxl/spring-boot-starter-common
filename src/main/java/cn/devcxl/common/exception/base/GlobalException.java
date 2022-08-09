package cn.devcxl.common.exception.base;


import cn.devcxl.common.exception.interfaces.ErrorCode;

/**
 * 全局异常类
 *
 * @author devcxl
 */
public class GlobalException extends RuntimeException {

    private ErrorCode<?> errorCode;

    public GlobalException(ErrorCode<?> errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode<?> getErrorCode() {
        return errorCode;
    }


}
