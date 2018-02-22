package com.example.lovej.jlovem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/2/22 0022.
 */

public class DengLuActivity extends Activity {
    EditText nameEt,pwdEt;
    Button dengluBtn;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏
        getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_denglu);
        nameEt = (EditText)findViewById(R.id.nameEt);
        pwdEt = (EditText)findViewById(R.id.pwdEt);
        dengluBtn = (Button)findViewById(R.id.dengluBtn);
        dengluBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameEt.getText().toString().indexOf("123") == 0&&pwdEt.getText().toString().indexOf("123") == 0){
                    Intent intent = new Intent(DengLuActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(DengLuActivity.this,"输入的账号或者密码错误，请重新输入！",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
