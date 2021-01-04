package io.pandora.mall.push.offline.dto;

import io.pandora.mall.base.BaseDto;

import java.util.Map;

/**
 * @author Created by mr_zhou on 2021/1/4
 */
public class PushResponse extends BaseDto {

    private String taskId;
    private String messageId;
    private Map<String,Object> response;

    public PushResponse(Map<String, Object> response) {
        this.response = response;
    }

    public String getTaskId() {
        return response.get("msgId").toString();
    }

    public String getMessageId() {
        return response.get("taskId").toString();
    }

    public Map<String, Object> getResponse() {
        return response;
    }

    public void setResponse(Map<String, Object> response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "PushResponse{ taskId='" + taskId + ", messageId='" + messageId + ", response=" + response + "}";
    }
}
