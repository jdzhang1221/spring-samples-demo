package com.jdzhang1221.mybatissample.mapper;

import com.jdzhang1221.mybatissample.model.SysUser;

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
}
