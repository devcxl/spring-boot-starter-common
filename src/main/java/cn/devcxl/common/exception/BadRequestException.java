package cn.devcxl.common.exception;

import cn.devcxl.common.exception.base.GlobalException;
import cn.devcxl.common.exception.enums.CommonErrorCode;
import cn.devcxl.common.exception.interfaces.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author devcxl
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends GlobalException {

    public BadRequestException() {
        super(CommonErrorCode.BAD_REQUEST);
    }

    public BadRequestException(ErrorCode<CommonErrorCode> errorCode) {
        super(errorCode);
    }
}
