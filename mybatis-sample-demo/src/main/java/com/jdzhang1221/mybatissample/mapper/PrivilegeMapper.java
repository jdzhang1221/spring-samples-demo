/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.jdzhang1221.mybatissample.mapper;

import com.jdzhang1221.mybatissample.model.SysPrivilege;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.lang.reflect.Type;

/**
 * @author zhangjundong
 * @date 2020/8/922:56
 */
public interface PrivilegeMapper {

    @SelectProvider(type=PrivilegeProvider.class,method = "selectById")
    SysPrivilege selectById(Long id);
}
