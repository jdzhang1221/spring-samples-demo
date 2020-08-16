/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.jdzhang1221.mybatissample.mapper;

import com.jdzhang1221.mybatissample.model.SysRole;
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
            //获取usermapper接口
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //查询id=1用户
            sysUser1=userMapper.selectById(1L);
            //对当前获取的对象重新赋值
            sysUser1.setUserName("New Name");
            //再次查询获取id相同的用户
            SysUser sysUser2=userMapper.selectById(1L);
            //虽然没有更新数据库，但是这个用户名和sysuser1重新赋值的名字相同
            Assert.assertEquals("New Name", sysUser2.getUserName());
            //无论如何，sysuser2和sysuser1完全就是同一个实例
            Assert.assertEquals(sysUser1, sysUser2);
        } finally {
            //关闭sqlsession
            sqlSession.close();
        }
        System.out.println("开启新的sqlSession ");
        //开始另一个新的Session
        sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //查询id=1的用户
            SysUser sysUser2=userMapper.selectById(1L);
            //第二个session获取的用户名仍然是admin
            Assert.assertNotEquals("New Name",sysUser2.getUserName());
            //这里的sysuser2和前一个session查询的结果是两个不同的实例
            Assert.assertNotEquals(sysUser1,sysUser2);
            //执行删除操作
            userMapper.deleteById(1L);
            //获取sysUser3
            SysUser sysUser3=userMapper.selectById(1L);
            //这里的sysUser2和sysUser3是两个不同的实例
            Assert.assertNotEquals(sysUser2,sysUser3);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testL2Cache(){
        SqlSession sqlSession=getSqlSession();
        SysRole sysRole1=null;
        try {
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            //查询id=1用户
            sysRole1=roleMapper.selectById(1L);
            //对当前获取的对象重新赋值
            sysRole1.setRoleName("New Name");
            //再次查询获取id相同的用户
            SysRole sysRole2=roleMapper.selectById(1l);
            //虽然没有更新数据库，但是这个用户名和ssysRole1重新赋值的名字相同
            Assert.assertEquals("New Name",sysRole2.getRoleName());
            //无论如何，sysRole1和sysRole2完全就是同一个实例
            Assert.assertEquals(sysRole1,sysRole2);
        } finally {
            sqlSession.close();
        }
        System.out.println("开始新的sqlSession");
        sqlSession=getSqlSession();
        try {
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole2=roleMapper.selectById(1L);
            //第二个session获取的用户是New Name
            Assert.assertEquals("New Name",sysRole2.getRoleName());
            //这里的role2和前一个session查询的结果是两个不同的实例
            Assert.assertNotEquals(sysRole1,sysRole2);
            //获取role3
            SysRole sysRole3=roleMapper.selectById(1L);
            //这里的role2和role3是两个不同的实例
            Assert.assertNotEquals(sysRole2,sysRole3);
        } finally {
            sqlSession.close();
        }
    }
}
