package com.jit.zjw.mynote_55;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jit.zjw.mynote_55.entity.User;
import com.jit.zjw.mynote_55.service.UserInfoService;
import com.jit.zjw.mynote_55.service.impl.UserInfoServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class RegistActivity extends AppCompatActivity {

    private EditText et_regist_uname,et_regist_upwd,et_regist_checkcode;
    private CheckBox cb_regist_eat,cb_regist_sleep,cb_regist_play;
    private List<CheckBox> checkBoxHobbies = new ArrayList<CheckBox>();//将checkBox绑定在一起
    private TextView tv_regist_uname,tv_regist_upwd,tv_regist_checkcode,tv_regist_msg_checkcode;
    private String tempCode,strCode,uname,upwd;//临时存储生成的验证码(使用空格美化过），strCode(未美化）
    private boolean unameFlag,checkCodeFlag,upwdFlag;//用于表示用户名、验证码验证结果
    private UserInfoService userInfoService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        userInfoService = new UserInfoServiceImpl(RegistActivity.this);
        //初始化组件
        initComponent();
        //2、页面初始化
        initUI();
        //3、初始化功能
        init();

    }

    //组件注册
    private void initComponent(){
        et_regist_uname = (EditText)findViewById(R.id.et_regist_uname);
        et_regist_upwd = findViewById(R.id.et_regist_upwd);
        et_regist_checkcode = findViewById(R.id.et_regist_checkcode);
        cb_regist_eat = findViewById(R.id.cb_regist_eat);
        cb_regist_sleep = findViewById(R.id.cb_regist_sleep);
        cb_regist_play = findViewById(R.id.cb_regist_play);
        tv_regist_uname = findViewById(R.id.tv_regist_uname);
        tv_regist_upwd = findViewById(R.id.tv_regist_upwd);
        tv_regist_checkcode = findViewById(R.id.tv_regist_checkcode);
        tv_regist_msg_checkcode = findViewById(R.id.tv_regist_msg_checkcode);


    }

    //初始化日历
//    private void initCalendar(){
//        Calendar c = Calendar.getInstance();//确定日历实例
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH)+1;//注意Calendar.MONTH只有0-11  0代表1月
//        int day = c.get(Calendar.DAY_OF_MONTH);
//        int hour = c.get(Calendar.HOUR_OF_DAY);
//        int min = c.get((Calendar.MINUTE));
//        dp_regist_birth.init(year, month, day, new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                //显示内容容  或者改变之后的日期，记录到需要的变量中
//                Toast.makeText(RegistActivity.this, "选择的日期是："+year+"年"+(monthOfYear+1)+"月"
//                        +dayOfMonth+"日！", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    //初始化页面方法
    private void initUI(){
        //给用户名框左侧添加图像，并控制大小
        //获取图片资源
        Drawable dl = getResources().getDrawable(R.drawable.tx);
        //设置图片
        dl.setBounds(0,0,50,50);
        //将图片嵌入到对应组件中
        et_regist_uname.setCompoundDrawables(dl,null,null,null);
    }

    /**
     * 功能化的初始化
     */
    private void init(){
        //1.账号验证功能
        //initCalendar();
        et_regist_uname.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;//用于临时存储我们 输入的内容
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            //该方法会在编辑框内容变化时，触发
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;//每次获取输入内容
            }
            //该方法会在编辑框内容变化后，触发    进行验证
            @Override
            public void afterTextChanged(Editable s) {
                //利用正则表达式声明用户名验证规则
                String unameMode = "^[\\w]{6,9}$";//用户名允许6-9位数字、大写、小写字母
                if(temp.toString().matches(unameMode)){
                    tv_regist_uname.setText("你输入的用户名可用！");
                    tv_regist_uname.setTextColor(getResources().getColor(R.color.red));
                    unameFlag = true;
                }else{
                    tv_regist_uname.setText("请输入6-9位数字、大写、小写字母");
                    tv_regist_uname.setTextColor(getResources().getColor(R.color.chocolate));
                    unameFlag = false;

                }


            }

        });

        et_regist_upwd.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;//用于临时存储我们 输入的内容
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;//每次获取输入内容
            }

            @Override
            public void afterTextChanged(Editable s) {
                //利用正则表达式声明用户名验证规则
                String unameMode = "^[a-zA-Z]\\w{5,17}$";//密码(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)
                if(temp.toString().matches(unameMode)){
                    tv_regist_upwd.setText("你输入的密码可用！");
                    tv_regist_upwd.setTextColor(getResources().getColor(R.color.red));
                    upwdFlag = true;
                }else{
                    tv_regist_upwd.setText("请输入6-18位密码(以字母开头，只能包含字母、数字和下划线)");
                    tv_regist_upwd.setTextColor(getResources().getColor(R.color.chocolate));
                    upwdFlag = false;

                }
            }
        });

        //2.验证码处理
        tempCode = checkCode();//只执行一次
        tv_regist_checkcode.setText(tempCode);
        et_regist_checkcode.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private String strs[];
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //1.解析 由于为了美观 在验证码生成的时候添加了空格，这里需要进行对比，我们需要还原验证码
//                strs = tempCode.split(" ");
//                strCode = "";
//                for(int i = 0;i<strs.length;i++){
//                    strCode+= strs[i];
//                }
//                System.out.println("验证码："+strCode);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                //验证码判断
                if(strCode.equalsIgnoreCase(temp.toString())){
                    tv_regist_msg_checkcode.setText("验证码正确");
                    checkCodeFlag = true;
                }else{
                    tv_regist_msg_checkcode.setText("验证码错误");
                    checkCodeFlag = false;
                }

            }
        });
        //3.将单个checkBox放入集合
        checkBoxHobbies.add(cb_regist_eat);
        checkBoxHobbies.add(cb_regist_sleep);
        checkBoxHobbies.add(cb_regist_play);


    }

    //3.生成验证码的方法
    public String checkCode(){
        //规定验证码范围
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZqwertyuiopasdfghjklzxcvbnm0987654321".toCharArray();
        //2.从范围中随机取内容
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        strCode = "";
        for(int i=0;i<4;i++){
            int n = r.nextInt(chars.length);   //根据chars长度 随机选取下标
            sb.append(chars[n]+" ");//根据下标内容 放入到sb对象
            strCode+=chars[n];//不含空格的验证码
        }

        return sb.toString();
    }

    //重置方法
    public void resetInfo(View v){
        et_regist_uname.setText("");//清除用户框中内容
        et_regist_upwd.setText("");
        et_regist_checkcode.setText("");
        cb_regist_eat.setChecked(false);//多选框清空
        cb_regist_sleep.setChecked(false);
        cb_regist_play.setChecked(false);
    }
    //注册方法
    public void regist(View v){
        //System.out.println("用户名:"+et_regist_uname.getText().toString());//获取输入的用户名
        //System.out.println("密码："+et_regist_upwd.getText().toString());//获取输入的密码
        //注册方法执行之前，最好进行验证（暂时省略）
        //将获取到的内容放入实体类   以便之后操作
        //User user =new User(et_regist_uname.getText().toString(),et_regist_upwd.getText().toString());
        //System.out.println(user);
        //处理选中的爱好
        StringBuilder sb = new StringBuilder();
        for(CheckBox cb:checkBoxHobbies){
            //只需要统计被选中的爱好
            if(cb.isChecked()){
                if(cb.getText().toString().equals("吃饭")){
                    sb.append("吃饭"+"~");
                }else if(cb.getText().toString().equals("睡觉")){
                    sb.append("睡觉"+"~");
                }else{
                    sb.append("玩"+"~");
                }
            }
        }

        if(unameFlag&&checkCodeFlag&&upwdFlag&&sb.toString()!=""){
            Toast.makeText(RegistActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
            //执行注册操作（1.向SQLite数据库注册2.向服务器注册（okHttp））
            //调用业务逻辑层Service 进行处理
            uname = et_regist_uname.getText().toString();
            upwd = et_regist_upwd.getText().toString();
            User user = new User(uname,upwd);
            boolean b = userInfoService.RegistUsers(user);
            if(b){
                startActivity(new Intent(RegistActivity.this,MainActivity.class));
                RegistActivity.this.finish();
            }else {
                System.out.println("失败");
            }
        }else{
            Toast.makeText(RegistActivity.this, "注册信息有误", Toast.LENGTH_SHORT).show();
        }

        //执行注册操作，（1、向SQLite数据库注册 2、向服务器注册（okHTTP））
        //调用业务逻辑层Service进行处理

    }
    //刷新验证码
    public void refreshCode(View v){
        tempCode = checkCode();//重新 生成验证码
        tv_regist_checkcode.setText(tempCode);
    }

    public void back(View v){
        Intent intent = new Intent(RegistActivity.this,MainActivity.class);
        startActivity(intent);
        RegistActivity.this.finish();
    }

}
