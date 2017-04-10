package com.phyl.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phyl.bean.User;
import com.phyl.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by xh on 2017/4/10.
 */
@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/add")
    public void add() {
        //通过参数添加
        userMapper.addUser("param", 1);
        //通过map添加
        HashMap<Object, Object> map = new HashMap<>();
        map.put("name", "map");
        map.put("age", 2);
        userMapper.addUser1(map);
        //通过对象添加
        User user = new User();
        user.setName("obj");
        user.setAge(3);
        userMapper.addUser2(user);
    }

    /**
     * 更新
     *
     * @param id
     */
    @RequestMapping("update/{id}")
    public void update(@PathVariable int id) {
        userMapper.update("param_update" + UUID.randomUUID(), id);
    }

    /**
     * 删除
     */
    @RequestMapping("del/{id}")
    public void del(@PathVariable int id) {
        userMapper.del(id);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @RequestMapping("find/{id}")
    public String get(@PathVariable int id) {
        return userMapper.findByid(id).toString();
    }

    /**
     * 根据results
     *
     * @return
     */
    @RequestMapping("findall")
    public String getAll() {
        List<User> list = userMapper.findAll();
        return list.toString();
    }

    /**
     * 动态sql
     *
     * @param name
     * @return
     */
    @RequestMapping("get/{name}")
    public String find(@PathVariable String name) {
        List<User> userList = userMapper.findUserListByCondition(name);
        return userList.toString();
    }

    /**
     * 分页操作
     *
     * @param page
     * @return
     */
    @RequestMapping("findpage/{page}")
    public PageInfo findPage(@PathVariable int page) {
        PageHelper.startPage(page, 2);
        List<User> userList = userMapper.findAll();
        userList.forEach(System.out::println);
        return new PageInfo(userList);
    }
}
