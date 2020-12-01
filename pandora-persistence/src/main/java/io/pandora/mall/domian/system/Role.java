package io.pandora.mall.domian.system;

import com.baomidou.mybatisplus.annotation.TableName;
import io.pandora.mall.base.BaseDo;

import java.util.Date;

@TableName("mall_sys_role")
public class Role extends BaseDo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_sys_role.id
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_sys_role.name
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_sys_role.remark
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_sys_role.create_time
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_sys_role.permission
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    private String permission;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_sys_role.level
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    private Integer level;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_sys_role.id
     *
     * @return the value of mall_sys_role.id
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_sys_role.id
     *
     * @param id the value for mall_sys_role.id
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_sys_role.name
     *
     * @return the value of mall_sys_role.name
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_sys_role.name
     *
     * @param name the value for mall_sys_role.name
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_sys_role.remark
     *
     * @return the value of mall_sys_role.remark
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_sys_role.remark
     *
     * @param remark the value for mall_sys_role.remark
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_sys_role.create_time
     *
     * @return the value of mall_sys_role.create_time
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_sys_role.create_time
     *
     * @param createTime the value for mall_sys_role.create_time
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_sys_role.permission
     *
     * @return the value of mall_sys_role.permission
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public String getPermission() {
        return permission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_sys_role.permission
     *
     * @param permission the value for mall_sys_role.permission
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_sys_role.level
     *
     * @return the value of mall_sys_role.level
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_sys_role.level
     *
     * @param level the value for mall_sys_role.level
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
}