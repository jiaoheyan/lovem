package com.example.lovej.jlovem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2018/2/20 0020.
 */

public class JiShiBenActivity extends AppCompatActivity {
    EditText tiMu,neiRong;
    Button saveMsg;
    String tiMuStr = null, neiRongStr = null;
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
                tiMuStr = tiMu.getText().toString();
                neiRongStr = neiRong.getText().toString();
                Intent intent = new Intent(JiShiBenActivity.this,ListViewActivity.class);
                intent.putExtra("timu",tiMuStr);
                intent.putExtra("neirong",neiRongStr);
                startActivity(intent);
            }
        });
    }
}
