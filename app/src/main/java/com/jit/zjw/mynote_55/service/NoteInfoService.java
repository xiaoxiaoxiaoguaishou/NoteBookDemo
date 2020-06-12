package com.jit.zjw.mynote_55.service;

import com.jit.zjw.mynote_55.entity.NoteInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2019/11/26.
 */

public interface NoteInfoService {
    //查询所有笔记
    public List<NoteInfo> findAllNotes();
    //查询所有笔记数据改造
    public List<Map<String,Object>> findAllNote2(int uid) throws Exception;
    //删除笔记的方法
    public boolean delNoteByid(int nid);
    //添加笔记的方法
    public boolean addNote(NoteInfo note);
    //根据id查询笔记
    public NoteInfo findNotesByNid(int nid);
    //修改笔记的方法
    public boolean changeNotes(String title,String time,String content,int nid);
}
