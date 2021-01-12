package io.pandora.mall.domian.chat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("chat_connect_log")
public class ChatConnectLog implements Serializable {

    @TableId(value = "pk_id",type = IdType.ID_WORKER_STR)
    private String pkId;

    private String channelId;

    @ApiModelProperty(value = "0-已连接1-已注册，2-注册失败3-已下线")
    private Integer status;

    private String token;

    @ApiModelProperty(value = "客户端类型，描述清楚自己是哪个平台的，Web、APP、PAD")
    private String clientType;

    @ApiModelProperty(value = "哪个应用，要么是我们系统的应用，要么是others第三方的")
    private String app;

    @ApiModelProperty(value = "用户名，app+user，唯一标记到一个WebSocket客户端")
    private String user;

    @ApiModelProperty(value = "群组")
    private String group;

    @ApiModelProperty(value = "区域编码，取国标，默认为空字符串")
    private String areaCode;

    private String country;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String serverHost;

    private Integer serverPort;

    @ApiModelProperty(value = "实例标识，规则自定义")
    private String instanceFlag;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", channelId=").append(channelId);
        sb.append(", status=").append(status);
        sb.append(", token=").append(token);
        sb.append(", clientType=").append(clientType);
        sb.append(", app=").append(app);
        sb.append(", user=").append(user);
        sb.append(", group=").append(group);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", country=").append(country);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serverHost=").append(serverHost);
        sb.append(", serverPort=").append(serverPort);
        sb.append(", instanceFlag=").append(instanceFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}