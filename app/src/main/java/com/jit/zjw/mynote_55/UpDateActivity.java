package com.jit.zjw.mynote_55;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jit.zjw.mynote_55.entity.NoteInfo;
import com.jit.zjw.mynote_55.service.NoteInfoService;
import com.jit.zjw.mynote_55.service.impl.NoteInfoServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpDateActivity extends AppCompatActivity {

    private int nid,uid;
    private NoteInfoService noteInfoService;
    private EditText et_update_title,et_update_content;
    private NoteInfo notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date);
        Intent intent = getIntent();
        nid = intent.getIntExtra("nid",nid);
        uid = intent.getIntExtra("uid",uid);
        noteInfoService = new NoteInfoServiceImpl(UpDateActivity.this);
        et_update_title = findViewById(R.id.et_update_title);
        et_update_content = findViewById(R.id.et_update_content);
        init();
    }
    public void init(){
        notes = noteInfoService.findNotesByNid(nid);
        et_update_title.setText(notes.getTitle().toString());
        et_update_content.setText(notes.getContent().toString());
    }
    public void upDate(View v){
        Date now = new Date();//获取系统时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strNow = sdf.format(now);

        boolean b = noteInfoService.changeNotes(et_update_title.getText().toString(),strNow,et_update_content.getText().toString(),nid);
        if (b){
            startActivity(new Intent(UpDateActivity.this,NoteMainActivity.class));
            UpDateActivity.this.finish();
        }else {
            System.out.println("修改失败");
        }

    }
}
