package io.pandora.mall.push.enume;

/**
 * @author Created by mr_zhou on 2021/1/4
 */
public enum PushChannelType {
    GeiTui(1,"个推推送"),JiGuang(2,"极光推送");

    private Integer channel;
    private String name;

    PushChannelType(Integer channel, String name) {
        this.channel = channel;
        this.name = name;
    }

    public static boolean verify(Integer type){
        for (PushChannelType channelType : PushChannelType.values()) {
            if (type == channelType.channel){
                return true;
            }
        }
        return false;
    }

    public static String getName(Integer type){
        for (PushChannelType channelType : PushChannelType.values()) {
            if (type == channelType.channel){
                return channelType.getName();
            }
        }
        return GeiTui.name;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
