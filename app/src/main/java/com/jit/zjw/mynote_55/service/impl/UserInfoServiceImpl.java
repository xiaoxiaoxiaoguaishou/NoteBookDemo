package com.jit.zjw.mynote_55.service.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jit.zjw.mynote_55.dao.impl.UserInfoDaoImpl;
import com.jit.zjw.mynote_55.entity.User;
import com.jit.zjw.mynote_55.service.UserInfoService;

import com.jit.zjw.mynote_55.dao.UserInfoDao;
import com.jit.zjw.mynote_55.util.DataBaseHelper;

/**
 * Created by admin on 2019/11/28.
 */

public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoDao userInfoDao;
    private DataBaseHelper dbHelper;
    public UserInfoServiceImpl(Context context){
        userInfoDao = new UserInfoDaoImpl(context);
    }
    @Override
    public boolean RegistUsers(User user) {
        try {
            userInfoDao.RegistUsers(user);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean LoginIntoNote(String uname,String upwd) {

        User user = userInfoDao.LoginUsers(uname);
        if (null!=user&&user.getUpwd().equals(upwd)){
            return true;
        }
        return false;
    }

    @Override
    public int getUsersUid(String uname) {
        return 0;
    }
}
