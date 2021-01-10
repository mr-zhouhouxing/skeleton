package io.pandora.mall.email.util;

import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.system.SystemEmailConfig;
import io.pandora.mall.email.model.EmailSendRequest;
import io.pandora.mall.email.model.EmailSendResponse;
import lombok.extern.slf4j.Slf4j;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * <pre> 阿里邮件发送类 </pre>
 * <small> 2019年6月23日 | John</small>
 */
@Slf4j
public class EmailSendUtils {

    /**
     * 阿里云发送邮件
     *
     * @param request
     * @param emailConfig
     * @return
     */
    public static EmailSendResponse emailSend(EmailSendRequest request, SystemEmailConfig emailConfig) {
        // 配置环境信息
        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", emailConfig.getHost());
        props.put("mail.smtp.port", emailConfig.getPort());
        props.put("mail.user", emailConfig.getFrom());
        props.put("mail.password", emailConfig.getPassword());

        // 构建授权信息，用户名&密码 用于进行进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailConfig.getFrom(), emailConfig.getPassword());
            }
        };

        // 使用环境属性和授权信息，创建邮件会话
        MimeMessage message = new MimeMessage(Session.getInstance(props, authenticator)) {};
        try {
            message.setFrom(new InternetAddress(emailConfig.getFrom(), emailConfig.getFromName()));

            for (String toEmailAccount : request.getToEmails()) {
                // 设置收件人邮箱
                InternetAddress to = new InternetAddress(toEmailAccount);
                message.setRecipient(MimeMessage.RecipientType.TO, to);
                message.setSubject(request.getSubject());
                message.setContent(request.getContent(), "text/html;charset=UTF-8");

                Transport.send(message);
            }

            return EmailSendResponse.builder().responseCode(Constant.OK).responseCode("邮件发送成功").build();

        } catch (Exception e) {
            e.printStackTrace();
            // 发送失败
            return EmailSendResponse.builder().responseCode("ERROR").responseCode(e.getMessage()).build();
        }
    }
}
