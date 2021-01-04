package io.pandora.mall.domian.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by mr_zhou on 2021/1/4
 */
@Data
@TableName("system_push_client")
public class SystemPushClient implements Serializable {
    private Integer id;        //` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',

    private Long userId;      //` bigint(20) DEFAULT NULL COMMENT '用户',

    private String clientId;    //` varchar(225) DEFAULT NULL COMMENT '第三方推送ID',

    public SystemPushClient() {}

    public SystemPushClient(Long userId, String clientId) {
        this.userId = userId;
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "SystemPushClient{ id=" + id + ", userId=" + userId + ", clientId=" + clientId + "}";
    }
}
