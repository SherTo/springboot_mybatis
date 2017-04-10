package com.phyl.mapper;

import com.phyl.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by xh on 2017/4/10.
 */
@Mapper
public interface UserMapper {
    /**
     * 通过参数添加
     *
     * @param name
     * @param age
     * @return
     */
    @Insert("insert into user(name,age) values(#{name},#{age})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")//返回主键
    int addUser(@Param("name") String name, @Param("age") int age);

    /**
     * 通过Map添加
     *
     * @param map
     * @return
     */
    @Insert("insert into user(name,age) values(#{name},#{age})")
    int addUser1(Map map);

    /**
     * 通过对象添加
     *
     * @param user
     * @return
     */
    @Insert("insert into user(name,age) values(#{name},#{age})")
    int addUser2(User user);

    /**
     * 通过参数更新
     *
     * @param name
     * @param id
     * @return
     */
    @Update("update user set name=#{name} where id=#{id}")
    int update(@Param("name") String name, @Param("id") int id);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from user where id=#{id}")
    void del(int id);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    User findByid(@Param("id") int id);

    /**
     * 根据results
     *
     * @return
     */
    @Select("select id, name,age from user")
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    List<User> findAll();

    /**
     * 动态sql(多个参数一样)
     * @param name
     * @return
     */
    @SelectProvider(type = UserMapperProvider.class, method = "findUserList")
    List<User> findUserListByCondition(String name);
}
