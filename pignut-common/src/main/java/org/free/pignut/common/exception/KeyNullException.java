package org.free.pignut.common.exception;

/**
 * 秘钥 为空（未配置） 异常
 * Created by liuzhongshuai on 2017/9/18.
 */
public class KeyNullException extends BaseException {

    public KeyNullException(Integer errorCode,String message){
        super(message);
        this.errorCode=errorCode;
    }
}
