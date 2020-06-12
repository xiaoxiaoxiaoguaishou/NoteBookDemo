package com.jit.zjw.mynote_55.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jit.zjw.mynote_55.dao.UserInfoDao;
import com.jit.zjw.mynote_55.entity.User;
import com.jit.zjw.mynote_55.util.DataBaseHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2019/11/25.
 * 具体实现代码写在对应的实现类中
 */

public class UserInfoDaoImpl implements UserInfoDao {
    private DataBaseHelper dbHelper;
    public UserInfoDaoImpl(Context context){
        dbHelper = new DataBaseHelper(context);
    }



    @Override
    public User LoginUsers(String uname) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from tb_user where uname=?";
        Cursor cursor = db.rawQuery(sql,new String[]{uname+""});
        User user = new User();
        while (cursor.moveToNext()){
            user = (new User(
                    cursor.getInt(cursor.getColumnIndex("uid")),//笔记编号
                    cursor.getString(cursor.getColumnIndex("uname")),//用户编号
                    cursor.getString(cursor.getColumnIndex("upwd")),//标题
                    cursor.getString(cursor.getColumnIndex("ubirth")),//日期
                    cursor.getInt(cursor.getColumnIndex("usex")),//内容
                    cursor.getString(cursor.getColumnIndex("uhobby"))

            ));
        }
        db.close();
        cursor.close();
        return user;
    }

    @Override
    public void RegistUsers(User user) {
        String sql = "insert into tb_user (uname,upwd) values (?,?)";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql,new Object[]{user.getUname(),user.getUpwd()});
        db.close();
    }
}

