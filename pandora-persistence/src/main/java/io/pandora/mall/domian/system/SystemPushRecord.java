package io.pandora.mall.domian.system;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import java.io.Serializable;
import java.util.Date;

@Builder
@TableName("system_push_record")
public class SystemPushRecord implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "推送方ID")
    private Long fromId;

    @ApiModelProperty(value = "接收方ID")
    private Long toId;

    @ApiModelProperty(value = "推送类型")
    private String msgType;

    @ApiModelProperty(value = "推送内容")
    private String msgContent;

    @ApiModelProperty(value = "推送时间")
    private Date createTime;

    @ApiModelProperty(value = "响应码")
    private String resCode;

    @ApiModelProperty(value = "消息ID")
    private String taskId;

    @ApiModelProperty(value = "具体参数")
    private String parame;

    @ApiModelProperty(value = "个推推送，极光推送")
    private String channel;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getParame() {
        return parame;
    }

    public void setParame(String parame) {
        this.parame = parame;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fromId=").append(fromId);
        sb.append(", toId=").append(toId);
        sb.append(", msgType=").append(msgType);
        sb.append(", msgContent=").append(msgContent);
        sb.append(", createTime=").append(createTime);
        sb.append(", resCode=").append(resCode);
        sb.append(", taskId=").append(taskId);
        sb.append(", parame=").append(parame);
        sb.append(", channel=").append(channel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}