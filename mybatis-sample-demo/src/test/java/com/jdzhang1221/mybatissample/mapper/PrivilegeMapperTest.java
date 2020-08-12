/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.jdzhang1221.mybatissample.mapper;

import com.jdzhang1221.mybatissample.model.SysPrivilege;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhangjundong
 * @date 2020/8/1214:36
 */
public class PrivilegeMapperTest extends BaseMapperTest{
    @Test
    public void testSelectById(){
        SqlSession sqlSession=getSqlSession();
        try {
            PrivilegeMapper privilegeMapper=sqlSession.getMapper(PrivilegeMapper.class);
            SysPrivilege sysPrivilege= privilegeMapper.selectById(1L);
            Assert.assertNotNull(sysPrivilege);
            Assert.assertEquals("用户管理",sysPrivilege.getPrivilegeName());
        } finally {
            sqlSession.close();
        }
    }
}
