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

import java.util.Date;
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

    @Test
    public void testInsert(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //创建一个user对象
            SysUser sysUser=new SysUser();
            sysUser.setUserName("test1");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("test@mybatis.tk");
            sysUser.setUserInfo("test info");
            //正常情况下应该读入一张图片存到byte数组中
            sysUser.setHeadImg(new byte[]{1,2,3});
            sysUser.setCreateTime(new Date());
            //将新建对象插入数据库中，特别注意这里的返回值result是执行的SQL影响的行数
            int result= userMapper.insert(sysUser);
            //只插入1条数据
            Assert.assertEquals(1,result);
            //id为null，没有给id赋值，并且没有配置回写id的值
            Assert.assertNull(sysUser.getId());
        } finally {
            //为了不影响其他测试，这里选择回滚
            //由于默认的sqlSessionFactory.openSession()是不自动提交的
            //因此不手动执行commit也不会提交到数据库
            sqlSession.rollback();
            //不要忘记关闭sqlsession
            sqlSession.close();
        }
    }

    @Test
    public void testInsertUseGeneratedKeys(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //创建一个user对象
            SysUser sysUser=new SysUser();
            sysUser.setUserName("test1");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("test@mybatis.tk");
            sysUser.setUserInfo("test info");
            sysUser.setHeadImg(new byte[]{1,2,3});
            sysUser.setCreateTime(new Date());
            int result= userMapper.insertUseGeneratedKeys(sysUser);
            //只插入1条数据
            Assert.assertEquals(1,result);
            //因为id回写，所以id不为null
            Assert.assertNotNull(sysUser.getId());
        } finally {
            sqlSession.commit();
            //不要忘记关闭sqlsession
            sqlSession.close();
        }
    }

    @Test
    public void testInsertUseSelectKey(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //创建一个user对象
            SysUser sysUser=new SysUser();
            sysUser.setUserName("test1");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("test@mybatis.tk");
            sysUser.setUserInfo("test info");
            sysUser.setHeadImg(new byte[]{1,2,3});
            sysUser.setCreateTime(new Date());
            int result= userMapper.insertUseSelectKey(sysUser);
            //只插入1条数据
            Assert.assertEquals(1,result);
            //因为id回写，所以id不为null
            Assert.assertNotNull(sysUser.getId());
        } finally {
            sqlSession.commit();
            //不要忘记关闭sqlsession
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //从数据库查询一个user对象
            SysUser sysUser=userMapper.selectById(1L);
            //当前username为admin
            Assert.assertEquals("admin",sysUser.getUserName());
            //修改用户名
            sysUser.setUserName("admin_test");
            //修改邮箱
            sysUser.setUserEmail("test@mybatis.tk");
            //更新数据，特别注意，这里的返回值result是执行的SQL影响的行数
            int result=userMapper.updateById(sysUser);
            //只更新一条数据
            Assert.assertEquals(1,result);
            //根据当前id查询修改后的数据
            sysUser=userMapper.selectById(1L);
            //修改后的名字是admintest
            Assert.assertEquals("admin_test",sysUser.getUserName());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user1=userMapper.selectById(1L);
            Assert.assertNotNull(user1);
            Assert.assertEquals(1,userMapper.deleteById(1L));
            Assert.assertNull(userMapper.selectById(1L));

            SysUser user2=userMapper.selectById(1001L);
            Assert.assertNotNull(user2);
            Assert.assertEquals(1,userMapper.deleteById(user2));
            Assert.assertNull(userMapper.selectById(1001L));
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
