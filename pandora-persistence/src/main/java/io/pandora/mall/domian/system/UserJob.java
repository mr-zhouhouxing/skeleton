package io.pandora.mall.domian.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.pandora.mall.base.BaseDo;
import lombok.Data;

import java.util.Date;

/**
 * @author Created by John on 2020/10/16
 */
@Data
@TableName("mall_sys_job")
public class UserJob extends BaseDo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;         //` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    private String name;        //` varchar(255)  DEFAULT NULL COMMENT '岗位名称',
    private int status;         //` int(1) DEFAULT NULL COMMENT '状态',
    @TableField("create_time")
    private Date createTime;   //` datetime DEFAULT NULL COMMENT '时间',
}
