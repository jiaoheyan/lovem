package com.example.lovej.jlovem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lovej.jlovem.jishiben.MainPageActivity;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

/**
 * Created by Administrator on 2018/2/24.
 */

public class TestActivity extends AppCompatActivity {
    Button openUrl,jsbBtn,jsb1Btn ,testBtn,jishibenBtn,zhaopianBtn;
    private PrinterTextView mPrinterTextView;
    FloatingActionButton fab1;
    LinearLayout ll;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        ll = (LinearLayout) findViewById(R.id.mengbanLL);

        mPrinterTextView = (PrinterTextView) findViewById(R.id.pt_my);
        mPrinterTextView.setPrintText("草原上有对狮子母子。小狮子问母狮子：“妈，幸福在哪里?”母狮子说：“幸福就在你的尾巴上。”\n" +
                "　　于是小狮子不断追着尾巴跑，但始终咬不到。母狮子笑道：“傻瓜!幸福不是这样得到的!只要你昂首向前走，幸福就会一直跟随着你!”。", 100, "|");
        mPrinterTextView.startPrint();

        openUrl = (Button) findViewById(R.id.openUrl);
        openUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.jlovel.top");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        jsbBtn = (Button) findViewById(R.id.jsbBtn);
        jsbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestActivity.this, JiShiBenActivity.class);
                startActivity(intent);
            }
        });
        jsb1Btn = (Button) findViewById(R.id.jsb1Btn);
        jsb1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestActivity.this, MainPageActivity.class);
                intent.putExtra("class", "TestActivity");
                startActivity(intent);
            }
        });
        testBtn = (Button) findViewById(R.id.testBtn);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        jishibenBtn = (Button) findViewById(R.id.jishibenBtn);
        jishibenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestActivity.this, JiShiBenActivity.class);
                startActivity(intent);
            }
        });
        zhaopianBtn = (Button) findViewById(R.id.zhaopianBtn);
        zhaopianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TestActivity.this, "还没做好，敬请期待！", Toast.LENGTH_LONG).show();

            }
        });
    }
}
