package com.example.lovej.jlovem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lovej.jlovem.jishiben.MainPageActivity;

/**
 * Created by Administrator on 2017/12/25 0025.
 */

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{
    Button loveY,disloveY,openUrl,listViewBtn,jsbBtn,jsb1Btn;
    boolean isFlag = true;
    private PrinterTextView mPrinterTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPrinterTextView = (PrinterTextView) findViewById(R.id.pt_my);
        mPrinterTextView.setPrintText("草原上有对狮子母子。小狮子问母狮子：“妈，幸福在哪里?”母狮子说：“幸福就在你的尾巴上。”\n" +
                "　　于是小狮子不断追着尾巴跑，但始终咬不到。母狮子笑道：“傻瓜!幸福不是这样得到的!只要你昂首向前走，幸福就会一直跟随着你!”。", 100, "|");
        mPrinterTextView.startPrint();
        loveY = (Button)findViewById(R.id.loveY);
        loveY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        disloveY = (Button)findViewById(R.id.disloveY);
        disloveY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isFlag){
                    int[] location = new int[2];
                    int[] location1 = {550,700};
                    disloveY.getLocationInWindow(location);
                    int endY = location[1] - location1[1];

                    Animation TextTranslate = new TranslateAnimation(0,0,0,endY);
                    disloveY.setAnimation(TextTranslate);
                    TextTranslate.setDuration(1000);
                    TextTranslate.setFillAfter(true);
                    disloveY.startAnimation(TextTranslate);
                    isFlag = false;
                }
                else if (!isFlag){
                    int[] location = new int[2];
                    int[] location1 = {550,936};
                    disloveY.getLocationInWindow(location);
                    int endY = location1[1] - location[1];

                    Animation TextTranslate = new TranslateAnimation(0,0,0,-endY);
                    disloveY.setAnimation(TextTranslate);
                    TextTranslate.setDuration(1000);
                    TextTranslate.setFillAfter(true);
                    disloveY.startAnimation(TextTranslate);
                    isFlag = true;
                }

            }

        });
        openUrl = (Button)findViewById(R.id.openUrl);
        openUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.jlovel.top");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        listViewBtn = (Button)findViewById(R.id.listViewBtn);
        listViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ListViewActivity.class);
                startActivity(intent);
            }
        });
        jsbBtn = (Button)findViewById(R.id.jsbBtn);
        jsbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,JiShiBenActivity.class);
                startActivity(intent);
            }
        });
        jsb1Btn = (Button)findViewById(R.id.jsb1Btn);
        jsb1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainPageActivity.class);
                intent.putExtra("class","MainActivity");
                startActivity(intent);
            }
        });
    }
    public void imageSize(View view) {
        ImageView image_scale = (ImageView) findViewById(R.id.image_scale);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.size);
        image_scale.startAnimation(animation);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
