package org.free.pignut.common.exception;

/**
 * Created by liuzhongshuai on 2017/9/18.
 * 所有的自定义异常类 必须继承此异常类
 */
public class BaseException extends RuntimeException {


    public BaseException(String message) {
        super(message);
    }

    protected Integer errorCode;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

}
