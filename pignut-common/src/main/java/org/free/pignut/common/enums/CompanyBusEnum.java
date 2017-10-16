package org.free.pignut.common.enums;

/**
 * @author liuzhongshuai
 * Created by liuzhongshuai on 2017/9/21.
 */
public enum CompanyBusEnum {


    //返回了预期的结果
    SUCCESS("请求成功", 1000),
    PARAM_ERROR("参数验证未通过", 2001),
    SEARCH_COMPANY_ID_NULL("通过Id查询公司为空",2004),
    VALID_COMPANY_STATUS_DELETED("公司被删除(状态为1)",2006);



    CompanyBusEnum(String name, Integer code) {

        this.name = name;
        this.code = code;
    }


    public static CompanyBusEnum findByCode(Integer code) {
        for (CompanyBusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的库存操作类型:code = " + code);
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
