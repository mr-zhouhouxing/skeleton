package io.pandora.mall.push.enume;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Created by mr_zhou on 2021/1/4
 */
public enum PushType {
    message(1,"消息提醒"),like(2,"点赞提醒"),comment(3,"点赞提醒"),reply(4,"回复提醒"),concern(5,"关注提醒");

    private Integer type;
    private String title;

    PushType() {}

    PushType(Integer type, String title) {
        this.type = type;
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "PushType{ type=" + type + ", title='" + title + "}";
    }

    public static String getTitle(Integer type){
        List<String> titles = Arrays.stream(PushType.values())
                .filter(p -> p.getType() == type)
                .map(PushType::getTitle).collect(Collectors.toList());

        if (titles != null && titles.size() > 0 ) return titles.get(0);
        return message.getTitle();
    }
}
