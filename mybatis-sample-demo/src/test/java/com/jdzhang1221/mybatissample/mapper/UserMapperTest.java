/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.jdzhang1221.mybatissample.mapper;

import com.jdzhang1221.mybatissample.model.SysRole;
import com.jdzhang1221.mybatissample.model.SysRoleExtend;
import com.jdzhang1221.mybatissample.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author zhangjundong
 * @date 2020/8/100:20
 */
public class UserMapperTest extends BaseMapperTest {
    @Test
    public void testSelectById(){
        //获取sqlSession
        SqlSession sqlSession=getSqlSession();
        try {
            //获取UserMapper接口
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //调用selectById方法，查询id=1的用户
            SysUser user=userMapper.selectById(1L);
            //user不为空
            Assert.assertNotNull(user);
            //userName=admin
            Assert.assertEquals("admin",user.getUserName());
        } finally {
            //不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //调用selectAll方法查询所有用户
            List<SysUser> userList=userMapper.selectAll();
            //结果不为空
            Assert.assertNotNull(userList);
            //用户数量大于0
            Assert.assertTrue(userList.size()>0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserId(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysRoleExtend> sysRoleList= userMapper.selectRoleByUserId(1L);
            Assert.assertNotNull(sysRoleList);
            Assert.assertTrue(sysRoleList.size()>0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysRole> sysRoleList= userMapper.selectRolesByUserIdAndRoleEnabled(1L,1);
            Assert.assertNotNull(sysRoleList);
            Assert.assertTrue(sysRoleList.size()>0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserAndRole(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser sysUser=new SysUser();
            sysUser.setId(1L);
            SysRole sysRole=new SysRole();
            sysRole.setEnabled(1);
            List<SysRole> sysRoleList= userMapper.selectRolesByUserAndRole(sysUser,sysRole);
            Assert.assertNotNull(sysRoleList);
            Assert.assertTrue(sysRoleList.size()>0);
        } finally {
            sqlSession.close();
        }
    }
}
