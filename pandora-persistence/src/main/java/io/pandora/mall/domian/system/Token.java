package io.pandora.mall.domian.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.pandora.mall.base.BaseDo;
import lombok.Data;

/**
 * @autho John
 * @Chinese zhouhouxing
 * @date 2019/8/20 19:22
 */
@Data
@TableName("sys_wy_token")
public class Token extends BaseDo {
    @TableId(value = "id", type = IdType.AUTO)
    private long id;
    private long uid;
    @TableField("wy_token")
    private String wyToken;
}
