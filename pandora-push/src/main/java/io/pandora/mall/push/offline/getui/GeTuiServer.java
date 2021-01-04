package io.pandora.mall.push.offline.getui;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.notify.Notify;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.payload.MultiMedia;
import com.gexin.rp.sdk.dto.GtReq;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.redis.util.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Created by John on 2020/3/27
 */
@Slf4j
@Service
public class GeTuiServer {

    private static String url;
    private static String appId;
    private static String logUrl;
    private static String appKey;
    private static String masterSecret;

    public GeTuiServer(GeTuiProperties properties) {
        url = properties.getUrl();
        appId = properties.getAppId();
        appKey = properties.getAppKey();
        logUrl = properties.getLogUrl();
        masterSecret = properties.getMasterSecret();

    }

    public IPushResult pushToSingle(String toID, String title, String text) {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        TransmissionTemplate template = transmissionTemplate(toID, title, text);
        SingleMessage message = new SingleMessage();
        // 开启离线 为 true
        message.setOffline(true);
        // 离线有效时间，单位为毫秒
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(toID);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        return ret;
    }

    public void pushAll(String title, String text) {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        // STEP4：选择通知模板
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionType(2);
        template.setTransmissionContent("");
        Notify notify = new Notify();
        notify.setTitle(title);
        notify.setContent(text);
        notify.setUrl("push.png");
        notify.setIntent("intent:#Intent;launchFlags=0x10000000;package=com.pp.yl;"
                + "component=apelord.Pandla" // Android 包名
                + "/com.getui.demo.MainActivity;i.parm1=12;end");

        notify.setType(GtReq.NotifyInfo.Type._intent);
        template.set3rdNotifyInfo(notify);
        template.setAPNInfo(getAPNPayload("+1", text));
        // 定义"AppMessage"类型消息对象,设置推送消息有效期等推送参数
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);

        // 时间单位为毫秒
        message.setOfflineExpireTime(1000 * 600);

        //  执行推送
        IPushResult ret = push.pushMessageToApp(message);

        log.info("------> 调用个推实行推送给全部用户回调结果【{}】。", ret.getResponse().toString());
    }

    public static NotificationTemplate getNotificationTemplate(String title, String text) {

        NotificationTemplate template = new NotificationTemplate();

        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        // 配置通知栏网络图标
        template.setLogoUrl(logUrl);
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);

        template.setTitle(title);
        template.setText(text);

        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("content", text);
        map.put("payload", text);

        // 透传消息接受方式设置，1：立即启动APP，2：客户端收到消息后需要自行处理
        template.setTransmissionContent(String.valueOf(map));

        // 透传消息接受方式设置，1：立即启动APP，2：客户端收到消息后需要自行处理
        template.setTransmissionType(0);
        template.setAPNInfo(getAPNPayload("", text));

        return template;
    }

    public static TransmissionTemplate transmissionTemplate(String toID, String title, String text) {

        TransmissionTemplate template = new TransmissionTemplate();

        template.setAppId(appId);
        template.setAppkey(appKey);

        template.setTransmissionType(2);
        template.setTransmissionContent("");

        Notify notify = new Notify();
        notify.setTitle(title);
        notify.setContent(text);
        notify.setIntent("intent:#Intent;launchFlags=0x10000000;package=com.pp.yl;"
                + "component=apelord.Pandla" // Android 包名
                + "/com.getui.demo.MainActivity;i.parm1=12;end");

        notify.setType(GtReq.NotifyInfo.Type._intent);

        template.set3rdNotifyInfo(notify);

        String count = JedisUtil.getJson(Constant.USER_PUSH_CLIENT + toID);

        template.setAPNInfo(getAPNPayload(count, text));

        //设置第三方通知
        return template;
    }

    private static APNPayload getAPNPayload(String count, String text) {
        APNPayload payload = new APNPayload();
        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        payload.setAutoBadge(count);
        payload.setContentAvailable(0);
        //ios 12.0 以上可以使用 Dictionary 类型的 sound
        payload.setCategory("$由客户端定义");
        payload.setSound("default");
        payload.addCustomMsg("text", text);

        //简单模式APNPayload.SimpleMsg
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg(text));

        //payload.setAlertMsg(getDictionaryAlertMsg());
        // 字典模式使用APNPayload.DictionaryAlertMsg

        //设置语音播报类型，int类型，0.不可用 1.播放body 2.播放自定义文本
        payload.setVoicePlayType(2);

        //设置语音播报内容，String类型，非必须参数，用户自定义播放内容，仅在voicePlayMessage=2时生效
        //注：当"定义类型"=2, "定义内容"为空时则忽略不播放
        payload.setVoicePlayMessage("定义内容");

        // 添加多媒体资源
        payload.addMultiMedia(new MultiMedia().setResType(MultiMedia.MediaType.pic)
                .setResUrl("资源文件地址")
                .setOnlyWifi(true));

        return payload;
    }

}
