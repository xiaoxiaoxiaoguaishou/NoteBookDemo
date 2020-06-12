package com.jit.zjw.mynote_55.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jit.zjw.mynote_55.dao.NoteInfoDao;
import com.jit.zjw.mynote_55.entity.NoteInfo;
import com.jit.zjw.mynote_55.util.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/11/26.
 */

public class NoteInfoDaoImpl implements NoteInfoDao {

    private DataBaseHelper dbHelper;
    //改造构造方法，能够从Service读取到对应的Activity
    public NoteInfoDaoImpl(Context context){
        dbHelper = new DataBaseHelper(context);

    }
    @Override
    public List<NoteInfo> findAllNotes() {
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from tb_note";//尽量不要用* 请使用具体列名
        Cursor cursor = db.rawQuery(sql,null);
        List<NoteInfo> notes = new ArrayList<NoteInfo>();
        //将Cursor内容取出
        while(cursor.moveToNext()){
            notes.add(new NoteInfo(
                    cursor.getInt(cursor.getColumnIndex("nid")),
                    cursor.getInt(cursor.getColumnIndex("uid")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("content"))
                    ));
        }
        //关闭连接
        db.close();
        cursor.close();
        return notes;
    }

    @Override
    public List<NoteInfo> findAllNotesByUid(String uid) {
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from tb_note where uid=?";//尽量不要用* 请使用具体列名
        //new String[]{uid}对应sql语句中的问号
        Cursor cursor = db.rawQuery(sql,new String[]{uid});
        List<NoteInfo> notes = new ArrayList<NoteInfo>();
        //将Cursor内容取出
        while(cursor.moveToNext()){
            notes.add(new NoteInfo(
                    cursor.getInt(cursor.getColumnIndex("nid")),//笔记编号
                    cursor.getInt(cursor.getColumnIndex("uid")),//用户编号
                    cursor.getString(cursor.getColumnIndex("title")),//标题
                    cursor.getString(cursor.getColumnIndex("time")),//时间
                    cursor.getString(cursor.getColumnIndex("content"))//内容
            ));
        }
        //关闭连接
        db.close();
        cursor.close();
        return notes;
    }

    @Override
    public void delNoteByNid(int nid) {
        String sql = "delete from tb_note where nid=?";
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //new Object[]{nid}会赋值给问号
        //如果有多个？需要传入多个参数
        db.execSQL(sql,new Object[]{nid});
        db.close();
    }

    @Override
    public NoteInfo findNotesByNid(int nid) {
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from tb_note where nid=?";//尽量不要用* 请使用具体列名
        //new String[]{uid}对应sql语句中的问号
        Cursor cursor = db.rawQuery(sql,new String[]{nid+""});
        NoteInfo note = new NoteInfo();
        //将Cursor内容取出
        while(cursor.moveToNext()){
           note = new NoteInfo(
                    cursor.getInt(cursor.getColumnIndex("nid")),//笔记编号
                    cursor.getInt(cursor.getColumnIndex("uid")),//用户编号
                    cursor.getString(cursor.getColumnIndex("title")),//标题
                    cursor.getString(cursor.getColumnIndex("time")),//时间
                    cursor.getString(cursor.getColumnIndex("content"))//内容
            );
        }
        //关闭连接
        db.close();
        cursor.close();


        return note;
    }

    @Override
    public void addNote(NoteInfo note) {
        String sql = "insert into tb_note (uid,title,time,content)values(?,?,?,?)";
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //new Object[]{nid}会赋值给问号
        //如果有多个？需要传入多个参数
        db.execSQL(sql,new Object[]{note.getUid(),note.getTitle(),note.gettime(),note.getContent()});
        db.close();
    }

    @Override
    public boolean changeNotes(String title, String time, String content, int nid) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "update tb_note set title=?,time=?,content=? where nid=?";
        //Cursor cursor = db.rawQuery(sql,new String[]{title,time,content,nid+""});
        db.execSQL(sql,new String[]{title,time,content, String.valueOf(nid)});
        db.close();
        return true;
    }
}
