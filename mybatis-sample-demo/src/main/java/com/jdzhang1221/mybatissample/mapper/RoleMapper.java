package com.jdzhang1221.mybatissample.mapper;

import com.jdzhang1221.mybatissample.model.SysRole;
import com.jdzhang1221.mybatissample.model.SysRoleExtend;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhangjundong
 * @date 2020/8/922:56
 */
public interface RoleMapper {

    @Select("select id,role_name,enabled,create_by,create_time from sys_role where id=#{id}")
    SysRole selectById(Long id);

    @Results(id="roleResultMap",value = {
            @Result(property = "id",column = "id",id=true),
            @Result(property = "roleName",column = "role_name"),
            @Result(property = "enabled",column = "enabled"),
            @Result(property = "createBy",column = "create_by"),
            @Result(property = "createTime",column = "create_time")
    })
    @Select("select id,role_name,enabled,create_by,create_time from sys_role where id=#{id}")
    SysRole selectById2(Long id);

    @ResultMap("roleResultMap")
    @Select("select * from sys_role")
    List<SysRole> selectAll();

    @Insert({"INSERT INTO sys_role(role_name,enabled,create_by,create_time) ",
    "VALUES (#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})"})
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(SysRole sysRole);

    @Insert({"INSERT INTO sys_role(role_name,enabled,create_by,create_time) ",
            "VALUES (#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})"})
    @SelectKey(statement = "SELECT LAST_INSERT_ID()",keyColumn = "id",keyProperty = "id",resultType = Long.class,before = false)
    int insertUseSelectKey(SysRole sysRole);

    @Update({"UPDATE sys_role"," set role_name=#{roleName},enabled=#{enabled},create_by=#{createBy},",
    "create_time=#{createTime,jdbcType=TIMESTAMP}"," where id=#{id}"})
    int updateById(SysRole sysRole);

    @Delete("delete from sys_role where id=#{id}")
    int deleteById(Long id);

    /**
     * 根据用户id获取用户的角色信息
     * @param userId
     * @return
     */
    List<SysRoleExtend> selectRoleByUserIdChoose(Long userId);
}

