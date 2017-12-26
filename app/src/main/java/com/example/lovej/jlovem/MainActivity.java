package com.example.lovej.jlovem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

/**
 * Created by Administrator on 2017/12/25 0025.
 */

public class MainActivity extends AppCompatActivity {
    Button loveY,disloveY;
    boolean isFlag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
}
