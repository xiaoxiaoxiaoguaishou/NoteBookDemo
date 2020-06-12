package com.jit.zjw.mynote_55.dao;

import com.jit.zjw.mynote_55.entity.User;

import java.util.List;

/**
 * 用户数据库操作的接口 只声明方法
 */

public interface UserInfoDao {
    /**
     * 查询单个用户
     *返回值User（单个对象）
     * 输入值String 如果需要查询的内容只有1个信息 使用简单数据类型 否则请使用对象
     */
    //public User findUserByName(String uname) throws Exception;
    /**
     * 查询所有用户
     * 是否需要查询条件
     * 返回值类型？返回结果是多个，使用List泛型<User> 如果只是单表查询直接用User
     * 多表查询 注意 请使用VO也就是写一个特殊的类，既包含User信息，也包含Order信息
     */
    //public List<User> findAllUser() throws Exception;
    //登录检查
    public User LoginUsers(String uname);
    //注册用户
    public void RegistUsers(User user);
}
