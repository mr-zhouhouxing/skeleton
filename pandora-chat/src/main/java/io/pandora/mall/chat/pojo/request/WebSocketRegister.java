package io.pandora.mall.chat.pojo.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Created by mr_zhou on 2021/1/6
 */
@Data
@ToString
public class WebSocketRegister implements Serializable {
    private String app = "";
    private String user = "";
    private String group = "";
    private String areaCode = "";
    private String clientType = "";
    private String country = "";
}
