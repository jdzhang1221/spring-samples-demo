/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.jdzhang1221.mybatissample.mapper;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author zhangjundong
 * @date 2020/8/1214:19
 */
public class PrivilegeProvider {
//    public String selectById(final Long id){
//        return new SQL(){
//            {
//                SELECT("id,privilege_name,privilege_url");
//                FROM("sys_privilege");
//                WHERE("id=#{id}");
//            }
//        }.toString();
//    }

    public String selectById(final Long id){
        return "select id,privilege_name,privilege_url from sys_privilege where id=#{id}";
    }
}
