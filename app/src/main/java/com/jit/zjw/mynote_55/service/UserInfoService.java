package com.jit.zjw.mynote_55.service;

import com.jit.zjw.mynote_55.entity.User;

/**
 * Created by admin on 2019/11/28.
 */

public interface UserInfoService {
    //注册操作
    public boolean RegistUsers(User user);

    //登录操作
    public boolean LoginIntoNote(String uname,String upwd);

    //获取uid
    public int getUsersUid(String uname);
}
