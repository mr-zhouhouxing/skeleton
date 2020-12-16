package io.pandora.mall.domian.system;

import com.baomidou.mybatisplus.annotation.TableName;
import io.pandora.mall.base.BaseDo;
import lombok.Data;

import java.util.Date;

/**
 * @author Created by John on 2020/10/16
 */
@Data
@TableName("mall_sys_job")
public class Job extends BaseDo {
    private Integer id;
    private String name;
    private int status;
    private Date createTime;
}
