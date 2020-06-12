package com.jit.zjw.mynote_55;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jit.zjw.mynote_55.entity.NoteInfo;
import com.jit.zjw.mynote_55.service.NoteInfoService;
import com.jit.zjw.mynote_55.service.impl.NoteInfoServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    //了解下Edit table使用

    private EditText et_title,et_content;
    private NoteInfoService noteInfoService;
    private int uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Intent intent = getIntent();
        uid = intent.getIntExtra("uid",uid);
        initComponent();
        noteInfoService = new NoteInfoServiceImpl(AddNoteActivity.this);
    }
    private void initComponent(){
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);
    }
    public void addNote(View v){
        //1.获取需要的内容
        //用户编号怎么得到？ a登录后将用户编号查询到 然然后放入Application中 然后这里获取
        //时间处理 根据系统时间改造
        Date now = new Date();//获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strNow = sdf.format(now);//将日期类型转换为String 方便存入数据库
        NoteInfo note = new NoteInfo(uid,et_title.getText().toString(),strNow,et_content.getText().toString());
        //2.调用新增方法
        boolean b = noteInfoService.addNote(note);
        //3.实现页面跳转
        if(b){
            startActivity(new Intent(AddNoteActivity.this,NoteMainActivity.class));
            AddNoteActivity.this.finish();
        }{
            System.out.println("添加失败！");
        }
    }
}
