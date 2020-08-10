package com.jdzhang1221.mybatissample.mapper;

import com.jdzhang1221.mybatissample.model.SysRole;
import com.jdzhang1221.mybatissample.model.SysRoleExtend;
import com.jdzhang1221.mybatissample.model.SysUser;
import org.apache.ibatis.annotations.Param;

import javax.management.relation.Role;
import java.util.List;

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
}
