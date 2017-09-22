package org.free.pignut.common.vo.company;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by liuzhongshuai on 2017/9/21.
 */
public class CompanyVo {

    private Long id;

    @NotBlank(message = "公司名不能为空!")
    @Length(max = 30, message = "用户名长度在30个字符以内!")
    private String companyName;

    @NotNull(message = "公司所属人id必填")
    private Long ownerId;

    @NotBlank(message = "手机号码不能为空!")
    @Length(min = 11, max = 11, message = "请输入11位手机号码!")
    private String linkPhone;

    @NotBlank(message = "公司地址必填!")
    @Length(max = 50, message = "公司地址在50个字符以内!")
    private String companyAddress;

    private Date createTime;

    private Date modifyTime;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
