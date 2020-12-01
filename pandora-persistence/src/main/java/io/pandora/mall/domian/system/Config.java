package io.pandora.mall.domian.system;

import com.baomidou.mybatisplus.annotation.TableName;
import io.pandora.mall.base.BaseDo;

@TableName("sys_config")
public class Config extends BaseDo {
    private Long id;
    /**
     * 配置项名称
     */
    private String name;

    /**
     * 配置项key
            查询时根据key获取值
     */
    private String key;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 文本框类型  (0: 代表普通文本框（input），1代表：多行的文本(textarea)，2代表： 富文本(Editor),3代表：图片（Image）)
     */
    private String type;

    /**
     * 是否必填项 0代表 是 , 1代表否
     */
    private String isrequ;

    /**
     * 默认值
     */
    private String defaultval;

    /**
     * 对应的值
     */
    private String value;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getIsrequ() {
        return isrequ;
    }

    public void setIsrequ(String isrequ) {
        this.isrequ = isrequ == null ? null : isrequ.trim();
    }

    public String getDefaultval() {
        return defaultval;
    }

    public void setDefaultval(String defaultval) {
        this.defaultval = defaultval == null ? null : defaultval.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", key=").append(key);
        sb.append(", seq=").append(seq);
        sb.append(", type=").append(type);
        sb.append(", isrequ=").append(isrequ);
        sb.append(", defaultval=").append(defaultval);
        sb.append(", value=").append(value);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}