package com.jit.zjw.mynote_55.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2019/11/26.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //自定义构造方法 会创建一个mynote_db作为名字的数据库
    public DataBaseHelper(Context context){
        super(context,"mynote_db",null,1);
    }

    //一般会在onCreate 进行数据库初始化 程序第一次创建是，调用DataBaseHelper构造方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("执行了数据库初始化");
        //1.用户表
        db.execSQL("create table if not exists tb_user(uid integer primary key autoincrement," +
                "uname String,upwd String,ubirth String,usex integar,uhobby String)");
        db.execSQL("insert into tb_user values(1,'neu','123','2019/11/28',1,'吃饭睡觉玩')");
        db.execSQL("insert into tb_user values(2,'jit','123','2019/11/28',1,'吃饭睡觉玩')");
        db.execSQL("insert into tb_user values(3,'zjw','123','2019/11/28',1,'吃饭睡觉玩')");
        //2.记事本表
        db.execSQL("create table if not exists tb_note(nid integer primary key autoincrement,"+
        "uid integer,title String,time String,content String)");
        db.execSQL("insert into tb_note values(1,1,'这是第一个标题','2019/11/16','这是我的第一个记事本条目')");
        db.execSQL("insert into tb_note values(2,1,'现在吃什么','2019/11/16','肉')");
        db.execSQL("insert into tb_note values(3,1,'下午喝什么','2019/11/16','抹茶奶绿')");
        db.execSQL("update tb_note set title='新标题',time='2019/11/27' where nid =1");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
