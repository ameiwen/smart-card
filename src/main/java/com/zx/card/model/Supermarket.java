package com.zx.card.model;

import java.util.Date;

public class Supermarket {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_supermarket.id
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_supermarket.leader
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    private String leader;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_supermarket.position
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    private String position;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_supermarket.status
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_supermarket.update_time
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_supermarket.create_time
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_supermarket.id
     *
     * @return the value of sc_supermarket.id
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_supermarket.id
     *
     * @param id the value for sc_supermarket.id
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_supermarket.leader
     *
     * @return the value of sc_supermarket.leader
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    public String getLeader() {
        return leader;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_supermarket.leader
     *
     * @param leader the value for sc_supermarket.leader
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_supermarket.position
     *
     * @return the value of sc_supermarket.position
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    public String getPosition() {
        return position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_supermarket.position
     *
     * @param position the value for sc_supermarket.position
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_supermarket.status
     *
     * @return the value of sc_supermarket.status
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_supermarket.status
     *
     * @param status the value for sc_supermarket.status
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_supermarket.update_time
     *
     * @return the value of sc_supermarket.update_time
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_supermarket.update_time
     *
     * @param updateTime the value for sc_supermarket.update_time
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_supermarket.create_time
     *
     * @return the value of sc_supermarket.create_time
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_supermarket.create_time
     *
     * @param createTime the value for sc_supermarket.create_time
     *
     * @mbggenerated Wed Apr 15 21:55:42 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}