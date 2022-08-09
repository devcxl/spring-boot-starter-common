package cn.devcxl.common.exception;

import cn.devcxl.common.exception.base.GlobalException;
import cn.devcxl.common.exception.enums.CommonErrorCode;
import cn.devcxl.common.exception.interfaces.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author devcxl
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends GlobalException {

    public UnauthorizedException() {
        super(CommonErrorCode.UNAUTHORIZED);
    }

    public UnauthorizedException(ErrorCode<CommonErrorCode> errorCode) {
        super(errorCode);
    }
}
