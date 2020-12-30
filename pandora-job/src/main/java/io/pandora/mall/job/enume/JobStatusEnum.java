package io.pandora.mall.job.enume;

/**
 * 定时任务状态枚举类
 */
public enum JobStatusEnum  {
    STOP("0", "停止"), RUNNING("1", "运行");

    private String code;
    private String value;

    JobStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
