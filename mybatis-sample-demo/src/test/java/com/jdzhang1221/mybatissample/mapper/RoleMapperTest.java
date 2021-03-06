/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.jdzhang1221.mybatissample.mapper;

import com.jdzhang1221.mybatissample.model.SysPrivilege;
import com.jdzhang1221.mybatissample.model.SysRole;
import com.jdzhang1221.mybatissample.model.SysRoleExtend;
import com.jdzhang1221.mybatissample.type.Enabled;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author zhangjundong
 * @date 2020/8/1117:39
 */
public class RoleMapperTest extends BaseMapperTest{

    @Test
    public void testSelectById(){
        SqlSession sqlSession=getSqlSession();
        try {
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole= roleMapper.selectById(1L);
            Assert.assertNotNull(sysRole);
            Assert.assertEquals("管理员",sysRole.getRoleName());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById2(){
        SqlSession sqlSession=getSqlSession();
        try {
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole= roleMapper.selectById2(1L);
            Assert.assertNotNull(sysRole);
            Assert.assertEquals("管理员",sysRole.getRoleName());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession=getSqlSession();
        try {
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            List<SysRole> sysRoleList = roleMapper.selectAll();
            Assert.assertNotNull(sysRoleList);
            Assert.assertEquals(2,sysRoleList.size());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession=getSqlSession();
        try {
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole=new SysRole();
            sysRole.setRoleName("test_insert");
            sysRole.setEnabled(Enabled.enabled);
            sysRole.setCreateBy(1L);
            sysRole.setCreateTime(new Date());
            int result= roleMapper.insertUseSelectKey(sysRole);
            Assert.assertEquals(1,result);
            Assert.assertNotNull(sysRole.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession=getSqlSession();
        try {
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole=roleMapper.selectById(1L);
            sysRole.setRoleName("test_update");
            int result= roleMapper.updateById(sysRole);
            Assert.assertEquals(1,result);
            Assert.assertEquals("test_update",roleMapper.selectById(1L).getRoleName());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDelete(){
        SqlSession sqlSession=getSqlSession();
        try {
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole=roleMapper.selectById(1L);
            Assert.assertNotNull(sysRole);
            int result= roleMapper.deleteById(1L);
            Assert.assertEquals(1,result);
            Assert.assertNull(roleMapper.selectById(1L));
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserIdChoose(){
        SqlSession sqlSession=getSqlSession();
        try {
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            //将id=2的角色enabled赋值0
            SysRole sysRole=roleMapper.selectById(2L);
            sysRole.setEnabled(Enabled.disabled);
            roleMapper.updateById(sysRole);

            //获取用户id为1的角色
            List<SysRoleExtend> sysRoleExtendList=roleMapper.selectRoleByUserIdChoose(1L);
            for (SysRoleExtend sysRoleExtend:sysRoleExtendList){
                System.out.println("角色名："+sysRoleExtend.getRoleName());
                if(sysRoleExtend.getId().equals(1L)){
                    //第一个角色存在权限信息
                    Assert.assertNotNull(sysRoleExtend.getSysPrivilegeList());
                }else if(sysRoleExtend.getId().equals(2L))
                {
                    //第二个角色的权限为null
                    Assert.assertNull(sysRoleExtend.getSysPrivilegeList());
                    continue;
                }
                for (SysPrivilege sysPrivilege:sysRoleExtend.getSysPrivilegeList()){
                    System.out.println("权限名："+sysPrivilege.getPrivilegeName());
                }
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById(){
        SqlSession sqlSession=getSqlSession();
        try {
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole=roleMapper.selectById(2L);
            //Assert.assertEquals(Enabled.enabled,sysRole.getEnabled());

            sysRole.setEnabled(Enabled.disabled);
            if(sysRole.getEnabled()==Enabled.enabled || sysRole.getEnabled()==Enabled.disabled){
                roleMapper.updateById(sysRole);

                sysRole=roleMapper.selectById(2L);
                Assert.assertEquals(Enabled.disabled,sysRole.getEnabled());
            }
            else{
                throw new Exception("无效的enabled值");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {

        }
    }
}
