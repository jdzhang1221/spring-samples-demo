/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.jdzhang1221.mybatissample.type;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangjundong
 * @date 2020/8/1523:40
 */
public class EnabledTypeHandler implements TypeHandler<Enabled> {
    private final Map<Integer,Enabled> enabledMap=new HashMap<Integer, Enabled>();

    public EnabledTypeHandler(){
        for (Enabled enabled:Enabled.values()){
            enabledMap.put(enabled.getValue(),enabled);
        }
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Enabled enabled, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,enabled.getValue());
    }

    @Override
    public Enabled getResult(ResultSet resultSet, String s) throws SQLException {
        Integer value=resultSet.getInt(s);
        return enabledMap.get(value);
    }

    @Override
    public Enabled getResult(ResultSet resultSet, int i) throws SQLException {
        Integer value=resultSet.getInt(i);
        return enabledMap.get(value);
    }

    @Override
    public Enabled getResult(CallableStatement callableStatement, int i) throws SQLException {
        Integer value=callableStatement.getInt(i);
        return enabledMap.get(value);
    }
}
