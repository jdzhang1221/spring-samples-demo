package com.jdzhang1221.mybatissample.mapper;

import com.jdzhang1221.mybatissample.model.Country;

import java.util.List;

/**
 * @author zhangjundong
 * @date 2020/8/923:07
 */
public interface CountryMapper {
    /**
     * 查询全部国家
     * @return
     */
    List<Country> selectAll();
}
