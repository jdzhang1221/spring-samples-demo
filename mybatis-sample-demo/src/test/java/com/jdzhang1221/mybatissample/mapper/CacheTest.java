/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.jdzhang1221.mybatissample.mapper;

import com.jdzhang1221.mybatissample.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import sun.rmi.server.UnicastServerRef2;

/**
 * @author zhangjundong
 * @date 2020/8/1623:52
 */
public class CacheTest extends BaseMapperTest {

    @Test
    public void testL1Cache(){
        SqlSession sqlSession=getSqlSession();
        SysUser sysUser1=null;
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            sysUser1=userMapper.selectById(1L);
            sysUser1.setUserName("New Name");
            SysUser sysUser2=userMapper.selectById(1L);
            Assert.assertEquals("New Name", sysUser2.getUserName());
            Assert.assertEquals(sysUser1, sysUser2);
        } finally {
            sqlSession.close();
        }
    }
}
