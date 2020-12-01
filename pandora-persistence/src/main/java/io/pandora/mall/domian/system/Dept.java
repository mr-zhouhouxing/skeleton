package io.pandora.mall.domian.system;

import com.baomidou.mybatisplus.annotation.TableName;
import io.pandora.mall.base.BaseDo;

import java.util.Date;

@TableName("mall_sys_dept")
public class Dept extends BaseDo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_sys_dept.id
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_sys_dept.name
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_sys_dept.pid
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    private Integer pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_sys_dept.pic_id
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    private Integer picId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_sys_dept.remark
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_sys_dept.status
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    private Boolean status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_sys_dept.seq
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    private Integer seq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_sys_dept.create_time
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_sys_dept.id
     *
     * @return the value of mall_sys_dept.id
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_sys_dept.id
     *
     * @param id the value for mall_sys_dept.id
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_sys_dept.name
     *
     * @return the value of mall_sys_dept.name
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_sys_dept.name
     *
     * @param name the value for mall_sys_dept.name
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_sys_dept.pid
     *
     * @return the value of mall_sys_dept.pid
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_sys_dept.pid
     *
     * @param pid the value for mall_sys_dept.pid
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_sys_dept.pic_id
     *
     * @return the value of mall_sys_dept.pic_id
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public Integer getPicId() {
        return picId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_sys_dept.pic_id
     *
     * @param picId the value for mall_sys_dept.pic_id
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_sys_dept.remark
     *
     * @return the value of mall_sys_dept.remark
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_sys_dept.remark
     *
     * @param remark the value for mall_sys_dept.remark
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_sys_dept.status
     *
     * @return the value of mall_sys_dept.status
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_sys_dept.status
     *
     * @param status the value for mall_sys_dept.status
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_sys_dept.seq
     *
     * @return the value of mall_sys_dept.seq
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_sys_dept.seq
     *
     * @param seq the value for mall_sys_dept.seq
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_sys_dept.create_time
     *
     * @return the value of mall_sys_dept.create_time
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_sys_dept.create_time
     *
     * @param createTime the value for mall_sys_dept.create_time
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}