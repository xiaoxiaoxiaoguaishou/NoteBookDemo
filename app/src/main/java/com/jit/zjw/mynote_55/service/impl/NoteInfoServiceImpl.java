package com.jit.zjw.mynote_55.service.impl;

import android.content.Context;

import com.jit.zjw.mynote_55.dao.NoteInfoDao;
import com.jit.zjw.mynote_55.dao.impl.NoteInfoDaoImpl;
import com.jit.zjw.mynote_55.entity.NoteInfo;
import com.jit.zjw.mynote_55.service.NoteInfoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2019/11/26.
 */

public class NoteInfoServiceImpl implements NoteInfoService {

    private NoteInfoDao noteInfoDao;

    public NoteInfoServiceImpl(Context context){
        noteInfoDao = new NoteInfoDaoImpl(context);
    }

    @Override
    public List<NoteInfo> findAllNotes() {
        //如果有业务逻辑请添加
        return noteInfoDao.findAllNotes();
    }

    @Override
    public List<Map<String, Object>> findAllNote2(int uid) throws Exception {
        List<Map<String,Object>> notes = new ArrayList<Map<String,Object>>();
        //数据改造Dao
        List<NoteInfo> notesList = noteInfoDao.findAllNotes();
        for(int i=0;i<notesList.size();i++){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("nid",notesList.get(i).getNid());
            map.put("uid",notesList.get(i).getUid());
            map.put("title",notesList.get(i).getTitle());
            map.put("time",notesList.get(i).gettime());
            //会将所有的内容全显示 细节改造 例如 最多只显示内容的前5个字 其他内容需要点击后查看
            String content= notesList.get(i).getContent();
//            if(content.length()>5){
//                content = content.substring(0,4);
//                System.out.println("截取后的内容："+content);
//            }
            map.put("content",content);

            notes.add(map);
        }
        return notes;
    }

    @Override
    public boolean delNoteByid(int nid) {
        //借助异常处理 对没有返回值的方法 进行判断
        try{
            noteInfoDao.delNoteByNid(nid);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }

    @Override
    public boolean addNote(NoteInfo note) {
        //借助异常处理 对没有返回值的方法 进行判断
        try{
            noteInfoDao.addNote(note);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean changeNotes(String title, String time, String content, int nid) {
        if(noteInfoDao.changeNotes(title,time,content,nid)){
            return true;
        }
        return false;
    }

    @Override
    public NoteInfo findNotesByNid(int nid) {
        return noteInfoDao.findNotesByNid(nid);
    }
}
/*
创建Thread需要重写run()方法 启动需要调用start（）
Thread Run able都与线程有关
使用Sqlite数据库需要哪些内容
继承 创建 调用

 */