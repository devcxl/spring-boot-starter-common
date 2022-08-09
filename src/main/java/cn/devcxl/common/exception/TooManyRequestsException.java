package cn.devcxl.common.exception;

import cn.devcxl.common.exception.base.GlobalException;
import cn.devcxl.common.exception.enums.CommonErrorCode;
import cn.devcxl.common.exception.interfaces.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author devcxl
 */
@ResponseStatus(code = HttpStatus.TOO_MANY_REQUESTS)
public class TooManyRequestsException extends GlobalException {


    public TooManyRequestsException() {
        super(CommonErrorCode.TOO_MANY_REQUESTS);
    }

    public TooManyRequestsException(ErrorCode<?> errorCode) {
        super(errorCode);
    }
}
