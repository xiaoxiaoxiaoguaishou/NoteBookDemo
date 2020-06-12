package com.jit.zjw.mynote_55.entity;

import java.util.Date;

/**
 * 用于实现用户登录操作 以及根据用户查自己备忘录功能
 *
 */

public class User {
    private int uid;//用户编号 必填 自动生成
    private String uname;//用于登录
    private String upwd;//用于登录
    private String ubirth;//不必填
    private int usex;//选填
    private String uhobby;//选填
    //private String uimg;//存用户头像地址 选做
    //如果对于用户还有其他操作 可以自定义属性

    //构造方法 set get toString
    public User(){

    }

    public User(String uname, String upwd) {
        this.uname = uname;
        this.upwd = upwd;
    }

    public User(String uname, String upwd, String ubirth, int usex, String uhobby) {
        this.uname = uname;
        this.upwd = upwd;
        this.ubirth = ubirth;
        this.usex = usex;
        this.uhobby = uhobby;
    }

    public User(int uid, String uname, String upwd, String ubirth, int usex, String uhobby) {
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
        this.ubirth = ubirth;
        this.usex = usex;
        this.uhobby = uhobby;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", ubirth='" + ubirth + '\'' +
                ", usex=" + usex +
                ", uhobby='" + uhobby + '\'' +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUbirth() {
        return ubirth;
    }

    public void setUbirth(String ubirth) {
        this.ubirth = ubirth;
    }

    public int getUsex() {
        return usex;
    }

    public void setUsex(int usex) {
        this.usex = usex;
    }

    public String getUhobby() {
        return uhobby;
    }

    public void setUhobby(String uhobby) {
        this.uhobby = uhobby;
    }
}
