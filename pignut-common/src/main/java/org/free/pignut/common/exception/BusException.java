package org.free.pignut.common.exception;

/**
 * Created by liuzhongshuai on 2017/9/18.
 */
public class BusException extends  BaseException {

    public BusException(Integer errorCode,String message){
        super(message);
        this.errorCode=errorCode;
    }
}
