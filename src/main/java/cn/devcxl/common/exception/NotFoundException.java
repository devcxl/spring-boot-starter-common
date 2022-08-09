package cn.devcxl.common.exception;

import cn.devcxl.common.exception.base.GlobalException;
import cn.devcxl.common.exception.enums.CommonErrorCode;
import cn.devcxl.common.exception.interfaces.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author devcxl
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends GlobalException {
    public NotFoundException() {
        super(CommonErrorCode.NOT_FOUND);
    }

    public NotFoundException(ErrorCode<CommonErrorCode> errorCode) {
        super(errorCode);
    }
}
