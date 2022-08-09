package cn.devcxl.common.exception.interfaces;

/**
 * 错误码接口
 * <p>建议每个模块使用每一千个code<br>
 * eg：<br>
 * 0-999 建议通用错误码<br>
 * 1000-1999 用户模块<br>
 * 2000-2999 xx业务模块<br>
 * 另外,该错误码不应与httpStatusCode有关
 *
 * @author devcxl
 */
public interface ErrorCode<T> {

    /**
     * 获取错误码
     *
     * @return 错误码
     */
    int getCode();

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    String getMessage();

    /**
     * 设置错误消息
     *
     * @param message 错误信息
     * @return impl self
     */
    T setMessage(String message);


}
