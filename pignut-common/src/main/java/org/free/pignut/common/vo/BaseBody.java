package org.free.pignut.common.vo;

/**
 * 请求返回实体封装
 * Created by liuzhongshuai on 2017/9/14.
 * @author liuzhongshuai
 */
public class BaseBody<T> {


    /**
     * 返回成功得到了预期结果：SUCCESS
     * 返回成功未得到预期结果：FAILD
     */
    private String returnResult="SUCCESS";

    private Integer returnCode;


    private String returnMsg;


    private T data;

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public String getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(String returnResult) {
        this.returnResult = returnResult;
    }
}
