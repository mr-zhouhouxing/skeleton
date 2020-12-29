package io.pandora.mall.domian.social;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@TableName("social_user_invite")
public class SocialUserInvite implements Serializable {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "邀请人邀请码")
    private String code;

    @ApiModelProperty(value = "被邀请人ID")
    private Long userId;

    @ApiModelProperty(value = "通过状态:0.未通过，1.通过")
    private Integer disStatus;

    @ApiModelProperty(value = "达到条件数量")
    private Integer conditionCount;

    @ApiModelProperty(value = "奖励锁:0.未奖励，1.已赠送奖励")
    private Integer awardLock;

    @ApiModelProperty(value = "邀请时间")
    private Date createTime;

    @ApiModelProperty(value = "通过时间")
    private Date passTime;

    public SocialUserInvite(String code, Long userId, Date createTime) {
        this.code = code;
        this.userId = userId;
        this.createTime = createTime;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDisStatus() {
        return disStatus;
    }

    public void setDisStatus(Integer disStatus) {
        this.disStatus = disStatus;
    }

    public Integer getConditionCount() {
        return conditionCount;
    }

    public void setConditionCount(Integer conditionCount) {
        this.conditionCount = conditionCount;
    }

    public Integer getAwardLock() {
        return awardLock;
    }

    public void setAwardLock(Integer awardLock) {
        this.awardLock = awardLock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", userId=").append(userId);
        sb.append(", disStatus=").append(disStatus);
        sb.append(", conditionCount=").append(conditionCount);
        sb.append(", awardLock=").append(awardLock);
        sb.append(", createTime=").append(createTime);
        sb.append(", passTime=").append(passTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}