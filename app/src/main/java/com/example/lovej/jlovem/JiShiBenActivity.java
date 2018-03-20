package com.example.lovej.jlovem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lovej.jlovem.jishiben.MainPageActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/2/20 0020.
 */

public class JiShiBenActivity extends AppCompatActivity {
    EditText tiMu,neiRong;
    Button saveMsg;
    String tiMuStr = null, neiRongStr = null;
    private String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jishiben);
        tiMu = (EditText) findViewById(R.id.tiMuEditText);
        neiRong = (EditText)findViewById(R.id.neiRongEditText);
        saveMsg = (Button)findViewById(R.id.msgButton);
        saveMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                str = formatter.format(curDate);
                str = str.trim();
                tiMuStr = tiMu.getText().toString();
                neiRongStr = neiRong.getText().toString();
                if((TextUtils.isEmpty(tiMuStr))||TextUtils.isEmpty(neiRongStr)){
                    Toast.makeText(JiShiBenActivity.this,"输入的题目和内容不能为空！",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(JiShiBenActivity.this,MainPageActivity.class);
                    intent.putExtra("class","JiShiBenActivity");
                    intent.putExtra("timu",tiMuStr);
                    intent.putExtra("neirong",neiRongStr);
                    intent.putExtra("timers",str);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}
