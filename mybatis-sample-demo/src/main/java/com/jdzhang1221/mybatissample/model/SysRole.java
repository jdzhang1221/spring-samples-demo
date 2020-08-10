/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.jdzhang1221.mybatissample.model;

import java.util.Date;

/**
 * @author zhangjundong
 * @date 2020/8/815:50
 */
public class SysRole {
    /**
     * 角色ID
     */
    private Long id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 有效标志
     */
    private Integer enabled;
    /**
     * 创建人
     */
    private Long createby;
    /**
     * 创建时间
     */
    private Date createTime;
//    /**
//     * 用户名
//     */
//    private String userName;
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }

//    private SysUser sysUser;
//
//    public SysUser getSysUser() {
//        return sysUser;
//    }
//
//    public void setSysUser(SysUser sysUser) {
//        this.sysUser = sysUser;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Long getCreateby() {
        return createby;
    }

    public void setCreateby(Long createby) {
        this.createby = createby;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
