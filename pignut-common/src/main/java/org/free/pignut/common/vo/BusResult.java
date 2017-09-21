package org.free.pignut.common.vo;

/**
 * Created by liuzhongshuai on 2017/9/15.
 */
public class BusResult<T> {


    private Integer returnCode;

    private T data;

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
