package io.pandora.mall.domian.social;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@TableName("social_user")
public class SocialUser implements Serializable {
    @ApiModelProperty(value = "用户主键")
    private Long id;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "状态：0.正常，1.禁用，2.注销")
    private Integer status;

    @ApiModelProperty(value = "会员：0.普通用户，1.普通会员，2.超级会员")
    private Integer level;

    @ApiModelProperty(value = "适配第三方应用ID")
    private String openId;

    @ApiModelProperty(value = "账号角色")
    private Integer role;

    @ApiModelProperty(value = "用户唯一邀请码")
    private String inviteCode;

    @ApiModelProperty(value = "登录类型")
    private Integer loginType;

    @ApiModelProperty(value = "手机系统")
    private String mobileSystem;

    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "最后登录IP地址")
    private String lastLoginIp;

    @ApiModelProperty(value = "注册时间")
    private Date registerTime;

    @ApiModelProperty(value = "注册城市")
    private String registerCity;

    @ApiModelProperty(value = "注册来源")
    private Integer registerResource;

    public SocialUser(String password, String phone, String openId, String inviteCode, Integer loginType,
                      String mobileSystem, String lastLoginIp, Date registerTime, String registerCity, Integer registerResource) {
        this.password = password;
        this.phone = phone;
        this.openId = openId;
        this.inviteCode = inviteCode;
        this.loginType = loginType;
        this.mobileSystem = mobileSystem;
        this.lastLoginIp = lastLoginIp;
        this.registerTime = registerTime;
        this.registerCity = registerCity;
        this.registerResource = registerResource;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getMobileSystem() {
        return mobileSystem;
    }

    public void setMobileSystem(String mobileSystem) {
        this.mobileSystem = mobileSystem;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterCity() {
        return registerCity;
    }

    public void setRegisterCity(String registerCity) {
        this.registerCity = registerCity;
    }

    public Integer getRegisterResource() {
        return registerResource;
    }

    public void setRegisterResource(Integer registerResource) {
        this.registerResource = registerResource;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", status=").append(status);
        sb.append(", level=").append(level);
        sb.append(", openId=").append(openId);
        sb.append(", role=").append(role);
        sb.append(", inviteCode=").append(inviteCode);
        sb.append(", loginType=").append(loginType);
        sb.append(", mobileSystem=").append(mobileSystem);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", lastLoginIp=").append(lastLoginIp);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", registerCity=").append(registerCity);
        sb.append(", registerResource=").append(registerResource);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}