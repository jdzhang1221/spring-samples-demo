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
import com.jdzhang1221.mybatissample.model.SysUserExtend;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

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

    @Test
    public void testSelectByUser(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //只按用户名查询
            SysUser sysUser=new SysUser();
            sysUser.setUserName("ad");
            List<SysUser> sysUserList= userMapper.selectByUser(sysUser);
            Assert.assertTrue(sysUserList.size()>0);
            //只按邮箱查询
            sysUser=new SysUser();
            sysUser.setUserEmail("test@mybatis.tk");
            sysUserList=userMapper.selectByUser(sysUser);
            Assert.assertTrue(sysUserList.size()>0);
            //同时按用户名和邮箱查询
            sysUser=new SysUser();
            sysUser.setUserName("ad");
            sysUser.setUserEmail("test@mybatis.tk");
            sysUserList=userMapper.selectByUser(sysUser);
            Assert.assertTrue(sysUserList.size()==0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByIdSelective(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser sysUser=new SysUser();
            //更新id=1的用户
            sysUser.setId(1L);
            //修改邮箱
            sysUser.setUserEmail("test@mybatis.tk");
            int result= userMapper.updateByIdSelective(sysUser);
            Assert.assertEquals(1,result);
            //查询id=1的用户
            sysUser=userMapper.selectById(1L);
            //修改后的名字没有变，但是邮箱变了
            Assert.assertEquals("admin",sysUser.getUserName());
            Assert.assertEquals("test@mybatis.tk",sysUser.getUserEmail());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertSelective(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser sysUser=new SysUser();
            sysUser.setUserName("test_selective");
            sysUser.setUserPassword("123456");
            sysUser.setUserInfo("test_userinfo");
            sysUser.setCreateTime(new Date());
            userMapper.insertSelective(sysUser);
            //获取刚刚插入的数据
            sysUser=userMapper.selectById(sysUser.getId());
            //因为没有指定userEmail，所以使用数据库的默认值
            Assert.assertEquals("test@mybatis.tk",sysUser.getUserEmail());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdOrUserName(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //按id查询
            SysUser query=new SysUser();
            query.setId(1L);
            query.setUserName("admin");
            SysUser sysUser= userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(sysUser);
            //按userName查询
            query.setId(null);
            sysUser=userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(sysUser);
            //id 和 userName 都为空
            query.setUserName(null);
            sysUser=userMapper.selectByIdOrUserName(query);
            Assert.assertNull(sysUser);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByUserWhere(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //只按用户名查询
            SysUser query=new SysUser();
            query.setUserName("ad");
            List<SysUser> sysUserList= userMapper.selectByUserWhere(query);
            Assert.assertNotNull(sysUserList);
            //按userName查询
            query=new SysUser();
            query.setUserEmail("admin@mybatis.tk");
            sysUserList=userMapper.selectByUserWhere(query);
            Assert.assertNotNull(sysUserList);
            //id 和 userName 都为空
            query=new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.tk");
            sysUserList=userMapper.selectByUserWhere(query);
            Assert.assertTrue(sysUserList.size()==0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByIdSelectiveSet(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser sysUser= userMapper.selectById(1L);
            sysUser.setUserEmail("test@mybatis.tk");
            int result= userMapper.updateByIdSelectiveSet(sysUser);
            Assert.assertEquals(1,result);

            sysUser=userMapper.selectById(1L);
            Assert.assertEquals("admin",sysUser.getUserName());
            Assert.assertEquals("test@mybatis.tk",sysUser.getUserEmail());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdList(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<Long> idList=new ArrayList<Long>();
            idList.add(1L);
            idList.add(1001L);
            List<SysUser> sysUserList= userMapper.selectByIdList(idList);
            Assert.assertEquals(2,sysUserList.size());
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testSelectByIdArray(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            Long[] idList=new Long[]{1L,1001L};
            List<SysUser> sysUserList= userMapper.selectByIdArray(idList);
            Assert.assertEquals(2,sysUserList.size());
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testinsertList(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUserList=new ArrayList<SysUser>();
            for (int i=0;i<2;i++){
                SysUser sysUser=new SysUser();
                sysUser.setUserName("test"+i);
                sysUser.setUserPassword("123456");
                sysUser.setUserEmail("test@mybatis.tk");
                sysUserList.add(sysUser);
            }
            int result= userMapper.insertList(sysUserList);
            for (SysUser sysUser:sysUserList){
                System.out.println(sysUser.getId());
            }
            Assert.assertEquals(2,result);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByMap(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("id",1L);
            map.put("user_password","12345678");
            map.put("user_email","test@mybatis.tk");

            int result= userMapper.updateByMap(map);
            Assert.assertEquals(1,result);

            SysUser sysUser=userMapper.selectById(1L);
            Assert.assertEquals("test@mybatis.tk",sysUser.getUserEmail());
            Assert.assertEquals("12345678",sysUser.getUserPassword());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdResultType(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            //注意这里使用1001这个用户，因为这个用户只有一个角色
            SysUserExtend sysUserExtend=userMapper.selectUserAndRoleByIdResultMap(1001L);
            Assert.assertNotNull(sysUserExtend);
            Assert.assertNotNull(sysUserExtend.getSysRole());
        } finally {
            sqlSession.close();
        }
    }
}
