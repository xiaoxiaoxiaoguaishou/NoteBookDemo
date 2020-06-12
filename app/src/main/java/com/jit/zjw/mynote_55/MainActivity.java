package com.jit.zjw.mynote_55;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jit.zjw.mynote_55.service.UserInfoService;
import com.jit.zjw.mynote_55.service.impl.UserInfoServiceImpl;
import com.jit.zjw.mynote_55.util.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    private EditText et_login_uname,et_login_upwd;
    private UserInfoService userInfoService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();//获取可写入数据库
        et_login_uname = findViewById(R.id.et_login_uname);
        et_login_upwd = findViewById(R.id.et_login_upwd);
        userInfoService = new UserInfoServiceImpl(MainActivity.this);
    }

    //登录成功后 跳转到主页面 是否可以提示 登录成功
    public void toNoteMain(View v){
        try{
            if(userInfoService.LoginIntoNote(et_login_uname.getText().toString(),et_login_upwd.getText().toString())){
                //startActivity(new Intent(this,NoteMainActivity.class));
                Intent intent = new Intent(MainActivity.this,NoteMainActivity.class);
                int uid = userInfoService.getUsersUid(et_login_uname.getText().toString());
                intent.putExtra("uid",uid);
                startActivity(intent);
                this.finish();
            }
        }catch (Exception e){
            Toast.makeText(this, "用户名密码错误", Toast.LENGTH_SHORT).show();
        }
    }
    public void addUser(View v){
        Intent intent = new Intent(MainActivity.this,RegistActivity.class);
        startActivity(intent);

    }


}
