package com.jit.zjw.mynote_55.dao;

import com.jit.zjw.mynote_55.entity.NoteInfo;

import java.util.List;

/**
 * Created by admin on 2019/11/26.
 */

public interface NoteInfoDao {
    //查询所有记事本信息（不管用户编号
    public List<NoteInfo> findAllNotes();
    //根据用户编号查用户记事本信息
    public List<NoteInfo> findAllNotesByUid(String uid);
    //根据笔记编号 删除笔记
    public void delNoteByNid(int nid);
    public NoteInfo findNotesByNid(int nid);
    //新增笔记方法
    public void addNote(NoteInfo note);
    //修改数据
    public boolean changeNotes(String title,String time,String content,int nid);
}
