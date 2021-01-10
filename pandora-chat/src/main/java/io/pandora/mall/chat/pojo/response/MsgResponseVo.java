package io.pandora.mall.chat.pojo.response;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

/**
 * @author Created by mr_zhou on 2021/1/6
 */
@Data
@Builder
public class MsgResponseVo implements Serializable {
    private String id;
    private Integer code;
    private Integer type;
    private String message;
    private Object data;
    private String dateTime;
}
