package com.jit.zjw.mynote_55;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.jit.zjw.mynote_55.entity.NoteInfo;
import com.jit.zjw.mynote_55.service.NoteInfoService;
import com.jit.zjw.mynote_55.service.impl.NoteInfoServiceImpl;

import java.util.List;
import java.util.Map;

public class NoteMainActivity extends AppCompatActivity {

    private ListView lv_note;
    private NoteInfoService noteInfoService;
    private List<Map<String,Object>> notes;
    private SimpleAdapter simpleAdapter;//
    private int uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_main);
        Intent intent = getIntent();
        uid = intent.getIntExtra("uid",uid);
        noteInfoService = new NoteInfoServiceImpl(NoteMainActivity.this);
//        List<NoteInfo> notes = noteInfoService.findAllNotes();
//        System.out.println("获取到的数据数量："+notes.size());
//        System.out.println(notes.toString());
        //获取notes
        try{
            notes = noteInfoService.findAllNote2(uid);

        }catch (Exception e){

        }
        initComponent();
        init();//先处理数据 再将页面和数据结合

    }
    private void initComponent(){
        lv_note = (ListView)findViewById(R.id.lv_note);
    }
    private void init(){
        simpleAdapter = new SimpleAdapter(this,//当前Activity
                notes,//数据 使用List<Map<String,Object>>格式
                R.layout.lv_style_note,//需要使用的样式页面
                new String[]{"title","time","content"},//Map中的key
                new int[]{R.id.tv_title,R.id.tv_time,R.id.tv_content});//样式文件的id

        lv_note.setAdapter(simpleAdapter);//将适配器放入页面的ListView

        //点击事件  进入详情
        lv_note.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取nid
                int nid = (Integer) notes.get(position).get("nid");
                Intent intent = new Intent(NoteMainActivity.this,UpDateActivity.class);
                intent.putExtra("nid",nid);
                intent.putExtra("uid",uid);
                startActivity(intent);
            }
        });

        //长按事件  删除
        lv_note.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //弹出对话框
                //定义AlertDialog Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(NoteMainActivity.this);
                builder.setMessage("确认删除？");
                builder.setTitle("提示");
                //弹出对话框按钮设置
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //页面删除数据之前  先获取到每次点击的笔记编号
                        int nid = (Integer) notes.get(position).get("nid");
                        //ListView删除
                        //1.操作页面中显示的数据删除   position是长按事件的位置
                        if(notes.remove(position)!=null){
                            //2.调用删除方法  进行删除
                            noteInfoService.delNoteByid(nid);
                            System.out.println("删除成功！");
                        }else {
                            System.out.println("删除失败！");
                        }
                        //适配器重新适配
                        simpleAdapter.notifyDataSetChanged();
                        Toast.makeText(NoteMainActivity.this, "删除成功!", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(NoteMainActivity.this, "取消!", Toast.LENGTH_SHORT).show();
                    }
                });
                //创建对话框
                builder.create().show();


                return false;
            }
        });

    }

//    public void toAddNote(View v){
//
//        Intent intent = new Intent(NoteMainActivity.this,AddNoteActivity.class);
//        intent.putExtra("uid",uid);
//        startActivity(intent);
//        this.finish();
//
////        startActivity(new Intent(this,AddNoteActivity.class));
////        this.finish();
//    }

    public void toaddNote(View v){
        Intent intent = new Intent(NoteMainActivity.this,AddNoteActivity.class);
        startActivity(intent);

    }
}