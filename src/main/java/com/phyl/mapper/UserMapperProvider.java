package com.phyl.mapper;

import org.apache.ibatis.jdbc.SQL;

/**
 * Created by xh on 2017/4/10.
 */
public class UserMapperProvider {
    public String findUserList(final String name) {
        return new SQL() {
            {
                SELECT("id,name,age");
                FROM("user");
                WHERE("name='" + name + "'");
                ORDER_BY("id desc");
            }
        }.toString();
    }
}
