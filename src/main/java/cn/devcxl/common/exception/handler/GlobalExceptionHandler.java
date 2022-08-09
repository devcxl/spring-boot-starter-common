package cn.devcxl.common.exception.handler;

import cn.devcxl.common.Resp;
import cn.devcxl.common.exception.*;
import cn.devcxl.common.exception.enums.CommonErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;
import java.util.stream.Collectors;

/**
 * @author devcxl
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    private static String getMessage(BindingResult ex) {
        return ex.getFieldErrors().stream().map(fieldError -> {
            if (fieldError != null) {
                // 返回消息组装  格式  属性名 + 注解消息
                return fieldError.getField() + " " + fieldError.getDefaultMessage();
            } else {
                return null;
            }
        }).collect(Collectors.joining("; "));
    }

    /**
     * 处理spring参数校验validation异常
     *
     * @param e
     * @return 错误信息
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public Resp<String> handleValidatedException(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            String message = getMessage(ex.getBindingResult());
            return Resp.fail(CommonErrorCode.BAD_REQUEST.setMessage(message));
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException ex = (ConstraintViolationException) e;
            String message = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));
            return Resp.fail(CommonErrorCode.BAD_REQUEST.setMessage(message));
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;

            String message = getMessage(ex.getBindingResult());
            return Resp.fail(CommonErrorCode.BAD_REQUEST.setMessage(message));
        }
        return Resp.fail(CommonErrorCode.BAD_REQUEST);
    }

    /**
     * 处理参数异常
     *
     * @param e
     * @return 错误信息
     */
    @ExceptionHandler(BadRequestException.class)
    public Resp<String> handlerClientException(BadRequestException e) {
        log.error("参数异常", e);
        return Resp.fail(e.getErrorCode());
    }

    /**
     * 处理访问权限异常
     *
     * @param e
     * @return 错误信息
     */
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public Resp<String> handlerClientException(ForbiddenException e) {
        log.error("访问权限异常", e);
        return Resp.fail(e.getErrorCode());
    }


    /**
     * 处理未找到异常
     *
     * @param e
     * @return 错误信息
     */
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Resp<String> handlerClientException(NotFoundException e) {
        log.error("未找到异常", e);
        return Resp.fail(e.getErrorCode());
    }

    /**
     * 处理未认证异常
     *
     * @param e
     * @return 错误信息
     */
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UnauthorizedException.class)
    public Resp<String> handlerClientException(UnauthorizedException e) {
        log.error("未认证异常", e);
        return Resp.fail(e.getErrorCode());
    }

    /**
     * 访问次数过多异常
     *
     * @param e
     * @return 错误信息
     */
    @ResponseStatus(code = HttpStatus.TOO_MANY_REQUESTS)
    @ExceptionHandler(TooManyRequestsException.class)
    public Resp<String> handlerClientException(TooManyRequestsException e) {
        log.error("访问次数过多异常", e);
        return Resp.fail(e.getErrorCode());
    }

    /**
     * 运行时异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public Resp<String> handler(RuntimeException e) {
        log.error("运行时异常", e);
        return Resp.fail(CommonErrorCode.INTERNAL_SERVER_ERROR.setMessage(e.getMessage()));
    }

}
