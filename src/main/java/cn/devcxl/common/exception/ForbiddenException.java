package cn.devcxl.common.exception;

import cn.devcxl.common.exception.base.GlobalException;
import cn.devcxl.common.exception.enums.CommonErrorCode;
import cn.devcxl.common.exception.interfaces.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author devcxl
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ForbiddenException extends GlobalException {

    public ForbiddenException() {
        super(CommonErrorCode.FORBIDDEN);
    }

    public ForbiddenException(ErrorCode<CommonErrorCode> errorCode) {
        super(errorCode);
    }
}
