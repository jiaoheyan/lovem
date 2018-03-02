package com.example.lovej.jlovem;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/2/22 0022.
 */

public class DengLuActivity extends Activity {
    EditText nameEt,pwdEt;
    Button dengluBtn;
    CheckBox jzmmCB;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏
        getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_denglu);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        initView();
        boolean isRemenber=sp.getBoolean("remember_password",false);
        if(isRemenber){
            //将账号和密码都设置到文本中
            String username=sp.getString("username","");
            String password=sp.getString("password","");
            nameEt.setText(username);
            pwdEt.setText(password);
            jzmmCB.setChecked(true);
        }
        dengluBtn = (Button)findViewById(R.id.dengluBtn);
        dengluBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (nameEt.getText().toString().indexOf("123") == 0&&pwdEt.getText().toString().indexOf("123") == 0){
                    Intent intent = new Intent(DengLuActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
//                }
//                else {
//                    Toast.makeText(DengLuActivity.this,"输入的账号或者密码错误，请重新输入！",Toast.LENGTH_LONG).show();
//                }

            }
        });

    }
    private void initView(){
        nameEt = (EditText)findViewById(R.id.nameEt);
        pwdEt = (EditText)findViewById(R.id.pwdEt);
        jzmmCB = (CheckBox)findViewById(R.id.jzmmCB);
    }
    public void imageSize(View view) {
        ImageView image_scale = (ImageView) findViewById(R.id.image_scale);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.size);
        image_scale.startAnimation(animation);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (nameEt.getText().toString().equals("123")&&pwdEt.getText().toString().equals("123")){
                    editor=sp.edit();
                    if (jzmmCB.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("username",nameEt.getText().toString());
                        editor.putString("password",pwdEt.getText().toString());
                        editor.commit();
                    }
                    else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(DengLuActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(DengLuActivity.this,"输入的账号或者密码错误，请重新输入！",Toast.LENGTH_LONG).show();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }

}
