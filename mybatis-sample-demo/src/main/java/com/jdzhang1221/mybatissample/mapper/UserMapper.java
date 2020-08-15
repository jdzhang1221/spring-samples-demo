package com.jdzhang1221.mybatissample.mapper;

import com.jdzhang1221.mybatissample.model.SysRole;
import com.jdzhang1221.mybatissample.model.SysRoleExtend;
import com.jdzhang1221.mybatissample.model.SysUser;
import com.jdzhang1221.mybatissample.model.SysUserExtend;
import org.apache.ibatis.annotations.Param;

import javax.management.relation.Role;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjundong
 * @date 2020/8/922:53
 */
public interface UserMapper {
    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    SysUser selectById(Long id);

    /**
     * 查找全部用户
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 根据用户Id获取角色信息
     * @param userId
     * @return
     */
    List<SysRoleExtend> selectRoleByUserId(Long userId);

    /**
     * 根据用户id和角色的enabled状态获取用户角色
     * @param userId
     * @param enabled
     * @return
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId, @Param("enabled") Integer enabled);

    List<SysRole> selectRolesByUserAndRole(@Param("user") SysUser user, @Param("role") SysRole role);

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);

    /**
     * 新增用户-使用userGeneratedKeys方式
     * @param sysUser
     * @return
     */
    int insertUseGeneratedKeys(SysUser sysUser);

    /**
     * 新增用户-使用selectKey方式
     * @param sysUser
     * @return
     */
    int insertUseSelectKey(SysUser sysUser);

    /**
     * 根据主键更新
     * @param sysUser
     * @return
     */
    int updateById(SysUser sysUser);

    /**
     * 通过主键删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    int deleteById(SysUser sysUser);

    /**
     * 根据动态条件查询用户信息
     * @return
     */
    List<SysUser> selectByUser(SysUser sysUser);

    /**
     * 根据主键选择性更新用户信息
     * @param sysUser
     * @return
     */
    int updateByIdSelective(SysUser sysUser);

    /**
     * 根据传入的参数值动态插入列
     * @param sysUser
     * @return
     */
    int insertSelective(SysUser sysUser);

    /**
     * 根据用户Id或用户名查询
     * @param sysUser
     * @return
     */
    SysUser selectByIdOrUserName(SysUser sysUser);

    /**
     * 根据动态条件查询用户信息
     * @param sysUser
     * @return
     */
    List<SysUser> selectByUserWhere(SysUser sysUser);

    /**
     * 根据主键选择性更新用户信息
     * @return
     */
    int updateByIdSelectiveSet(SysUser sysUser);

    /**
     * 根据用户id集合查询用户
     * @param idList
     * @return
     */
    List<SysUser> selectByIdList(@Param("idList") List<Long> idList);

    /**
     * 根据用户id数组查询用户
     * @param idArray
     * @return
     */
    List<SysUser> selectByIdArray(Long[] idArray);

    /**
     * 批量插入用户信息
     * @param userList
     * @return
     */
    int insertList(List<SysUser> userList);

    /**
     * 通过map更新列
     * @param map
     * @return
     */
    int updateByMap(@Param("userMap") Map<String,Object> map);

    /**
     * 根据用户id获取用户信息和用户的角色信息
     * @param id
     * @return
     */
    SysUserExtend selectUserAndRoleByIdResultMap(Long id);

    /**
     * 根据用户id获取用户信息和用户的角色信息,嵌套查询方式
     * @param id
     * @return
     */
    SysUserExtend selectUserAndRoleByIdSelect(Long id);

    /**
     * 获取所有的用户以及对应的所有角色
     * @return
     */
    List<SysUserExtend> selectAllUserAndRoles();

    /**
     * 通过嵌套查询获取指定用户的信息以及用户的角色和权限信息
     * @param id
     * @return
     */
    SysUserExtend selectAllUserAndRolesSelect(Long id);
}
