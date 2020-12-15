package io.pandora.mall.domian.system;

import com.baomidou.mybatisplus.annotation.TableName;
import io.pandora.mall.base.BaseDo;
import lombok.Data;
import java.util.Date;

/**
 * @author zhouhouxing
 * @date 2019/8/20 19:22
 */
@Data
@TableName("system_token")
public class Token extends BaseDo {
    private Integer id;
    private Long userId;
    private Integer sessionId;
    private String token;
    private Date createTime;
    private Date expireTime;
    private Date updateTime;
}
