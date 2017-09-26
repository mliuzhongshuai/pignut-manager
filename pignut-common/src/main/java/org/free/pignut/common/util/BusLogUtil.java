package org.free.pignut.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by liuzhongshuai on 2017/9/26.
 * 日志工具类
 */
public class BusLogUtil {


    public BusLogUtil(Class c) {
        this.myLogger = LoggerFactory.getLogger(c);
    }

    private final static Logger logger = LoggerFactory.getLogger(BusLogUtil.class);
    private Logger myLogger = null;
    private static String ipStr = null;

    static {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            ipStr = addr.getHostAddress().toString();
        } catch (UnknownHostException e) {
            logger.error("获取服务器Ip异常", e);
            throw new RuntimeException("获取服务器Ip异常", e);
        }
    }

    public void error(String s, Object o) {
        myLogger.error("[" + ipStr + "]" + s, o);
    }


}
