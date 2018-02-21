package com.example.lovej.jlovem;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

/**
 * Created by Administrator on 2018/2/21 0021.
 */

public class UnlockViewActivity extends Activity {
    private UnlockView passWord;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_unlock);
        passWord = (UnlockView) this.findViewById(R.id.wp_ninelock);
        passWord.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        System.out.println(passWord.getOrbitString());
                        break;

                    default:
                        break;
                }
                return false;
            }
        });

    }
}
