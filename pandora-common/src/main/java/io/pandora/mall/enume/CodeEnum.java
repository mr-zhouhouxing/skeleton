package io.pandora.mall.enume;

/**
 * 响应状态码枚举
 * @author John /
 */
public enum CodeEnum {
    SUCCESS(2000,"操作成功"),
    ERROR(4000,"操作失败"),
    IDENTIFICATION_ERROR(20001,"身份异常"),
    DATA_ERROR(20002,"业务错误"),
    PARAM_ERROR(20003,"参数错误"),
    EMAIL_ERROR(20004,"邮箱格式错误"),
    ADMIN_ERROR(20005,"不能修改管理员信息!"),
    INVALID_USERNAME_PASSWORD(20006,"用户名或密码错误"),
    INVALID_RE_PASSWORD(20007,"两次输入密码不一致"),
    INVALID_USER(20008,"用户不存在"),
    INVALID_USER_EXIST(20009,"用户已存在"),
    INVALID_ROLE(20010,"角色不存在"),
    USER_NO_PERMITION(20011,"当前用户无该接口权限"),
    VERIFY_PARAM_ERROR(20012,"校验码错误"),
    VERIFY_PARAM_PASS(20013,"校验码过期"),
    MOBILE_ERROR(20014,"手机号格式错误"),
    UPDATE_ROLEINFO_ERROR(20015,"更新角色信息失败");

    private Integer respCode;
    private String respMsg;

    CodeEnum(Integer respCode, String respMsg){
        this.respCode = respCode;
        this.respMsg=respMsg;
    }

    public Integer getCode() {
        return respCode;
    }
    public String getMsg() {
        return respMsg;
    }
}
