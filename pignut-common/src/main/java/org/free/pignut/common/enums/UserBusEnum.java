package org.free.pignut.common.enums;

/**
 * @author liuzhongshuai
 * Created by liuzhongshuai on 2017/9/14.
 */
public enum UserBusEnum {

    //返回了预期的结果
    SUCCESS("请求成功", 1000),
    PARAM_ERROR("参数验证未通过", 1001),
    SEARCH_USER_USERNAME_NULL("用户未注册",1002),
    VALID_USER_PASSWORD_FALSE("用户密码验证未通过",1003),
    SEARCH_USER_ID_NULL("通过Id查询用户为空",1004),
    VALID_USER_USERNAME_UNINE_FALSE("验证用户名唯一未通过",1005),
    VALID_USER_STATUS_DELETED("用户被禁用(状态为1)",1006),
    USER_KEY_NULL("秘钥为空",1007);



    UserBusEnum(String name, Integer code) {

        this.name = name;
        this.code = code;
    }


    public static UserBusEnum findByCode(Integer code) {
        for (UserBusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的操作类型:code = " + code);
    }


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    private Integer code;

}
