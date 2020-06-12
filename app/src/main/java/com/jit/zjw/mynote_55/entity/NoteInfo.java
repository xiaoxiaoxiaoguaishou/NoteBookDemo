package com.jit.zjw.mynote_55.entity;

import java.util.Date;

/**
 * Created by admin on 2019/11/26.
 */

public class NoteInfo {

    private int nid;//笔记编号 主键
    private int uid;//关联的用户编号
    private String title;//标题
    private String time;//时间
    private String content;//neirong
    //天气心情之类

    public NoteInfo() {
    }

    public NoteInfo(int nid, int uid, String title, String time, String content) {
        this.nid = nid;
        this.uid = uid;
        this.title = title;
        this.time = time;
        this.content = content;
    }

    public NoteInfo(int uid, String title, String time, String content) {
        this.uid = uid;
        this.title = title;
        this.time = time;
        this.content = content;
    }

    @Override
    public String toString() {
        return "NoteInfo{" +
                "nid=" + nid +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
