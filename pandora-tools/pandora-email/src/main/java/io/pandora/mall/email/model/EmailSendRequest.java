package io.pandora.mall.email.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Created by mr_zhou on 2020/12/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailSendRequest {
    @NotEmpty
    @ApiModelProperty("邮件接收方[支持多个]")
    private List<String> toEmails;

    @NotBlank
    @ApiModelProperty("邮件主体标题")
    private String subject;

    @NotBlank
    @ApiModelProperty("邮件主体内容")
    private String content;

    @NotNull
    @ApiModelProperty("邮件类型:1.验证码,2.HTML,3.文本")
    private int sendType;

    public EmailSendRequest(List<String> toEmails,int sendType) {
        this.toEmails = toEmails;
        this.sendType = sendType;
    }
}
