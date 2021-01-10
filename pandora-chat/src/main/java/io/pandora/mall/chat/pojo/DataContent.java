package io.pandora.mall.chat.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Created by mr_zhou on 2021/1/6
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataContent implements Serializable {
    /**
     * 动作类型
     * @see io.pandora.mall.chat.enume.MessageActionEnum
     */
    private Integer action;

    private String tokenId;

    private String jsonObject;

    private String extend;
}
