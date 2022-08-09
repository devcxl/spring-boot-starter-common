package cn.devcxl.common;

import cn.devcxl.common.exception.interfaces.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author devcxl
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resp<T> {

    /**
     * 返回Code
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 返回消息
     */
    private String message;

    public Resp(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Resp(ErrorCode<?> errorCode, T data) {
        this.code = errorCode.getCode();
        this.data = data;
        this.message = errorCode.getMessage();
    }

    public static <T> Resp<T> ok(T data) {
        return new Resp<T>(200, data, "success");
    }

    public static <T> Resp<T> fail(ErrorCode<?> errorCode) {
        return new Resp<T>(errorCode.getCode(), null, errorCode.getMessage());
    }


}
