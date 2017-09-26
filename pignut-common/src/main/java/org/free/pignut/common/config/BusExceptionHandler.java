package org.free.pignut.common.config;

import org.free.pignut.common.exception.BaseException;
import org.free.pignut.common.util.BusLogUtil;
import org.free.pignut.common.vo.BaseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liuzhongshuai on 2017/9/18.
 */
@ControllerAdvice
public class BusExceptionHandler {

    private final static BusLogUtil logger = new BusLogUtil(BusExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseBody<Object> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        BaseBody<Object> baseBody = new BaseBody<Object>();
        baseBody.setReturnResult("FAILD");
        //自定义异常
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            baseBody.setReturnCode(baseException.getErrorCode());
            baseBody.setReturnMsg(baseException.getMessage());
            logger.error("自定义异常:", e);
        } else {//其他异常
            //需要记录日志
            logger.error("非自定义异常:", e);
            baseBody.setReturnCode(5000);//位置异常错误码
            baseBody.setReturnMsg("系统异常:" + e.getMessage());
        }
        return baseBody;
    }
}
