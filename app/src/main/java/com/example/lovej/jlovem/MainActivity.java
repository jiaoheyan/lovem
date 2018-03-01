package com.example.lovej.jlovem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/12/25 0025.
 */

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{
    Button jishibenBtn,zhaopianBtn;
    FloatingActionButton fab;
    LinearLayout ll;
    boolean isFlag = false;
    private ViewPager vpGuide;
    private LinearLayout llpointgroup;
    int index = 0;
    private View viewRedPoint;
    private int mPointWidth;
    private static final int[] mImageIds = new int[]{R.drawable.splashbg,R.drawable.splashbg,
            R.drawable.splashbg};
    private ArrayList<ImageView> mImageViewList;
    private Timer timer;
    Handler mHandler  = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    index++;
                    System.out.println("==========index: "+index);
                    vpGuide.setCurrentItem(index%3);

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = (LinearLayout) findViewById(R.id.mengbanLL);
        fab = (FloatingActionButton) findViewById(R.id.fab1);
        timer = new Timer();//创建Timer对象
        //执行定时任务
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(1);
            }
        }, 2000, 2000);

        vpGuide = (ViewPager) findViewById(R.id.vp_guide);
        llpointgroup = (LinearLayout) findViewById(R.id.ll_point_group);
        viewRedPoint = findViewById(R.id.viewRedPoint);
        initView();
        vpGuide.setAdapter(new GuideAdapter());
        vpGuide.setOnPageChangeListener(new GuidePageListener());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFlag){
                    ll.setVisibility(View.VISIBLE);
                    jishibenBtn.setVisibility(View.VISIBLE);
                    zhaopianBtn.setVisibility(View.VISIBLE);
                }
                else {
                    ll.setVisibility(View.INVISIBLE);
                    jishibenBtn.setVisibility(View.INVISIBLE);
                    zhaopianBtn.setVisibility(View.INVISIBLE);
                }
                isFlag = !isFlag;

            }
        });
        jishibenBtn = (Button) findViewById(R.id.jishibenBtn);
        jishibenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JiShiBenActivity.class);
                startActivity(intent);
            }
        });
        zhaopianBtn = (Button) findViewById(R.id.zhaopianBtn);
        zhaopianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "还没做好，敬请期待！", Toast.LENGTH_LONG).show();

            }
        });
    }
    private void initView(){
        mImageViewList = new ArrayList<ImageView>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(mImageIds[i]);
            mImageViewList.add(image);
        }
        for (int i = 0; i < mImageIds.length; i++) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10,10);
            if(i > 0)
            {
                params.leftMargin = 10;
            }
            point.setLayoutParams(params);
            llpointgroup.addView(point);
        }
        llpointgroup.getViewTreeObserver().addOnGlobalLayoutListener
                (new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        llpointgroup.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                        mPointWidth = llpointgroup.getChildAt(1).getLeft() - llpointgroup.getChildAt(0).getLeft();
                    }
                });
    }

    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    class GuidePageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            index = position;
            int len = (int) (mPointWidth*positionOffset)+mPointWidth*position;
            RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) viewRedPoint.getLayoutParams();
            params.leftMargin = len;
            viewRedPoint.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            // TODO Auto-generated method stub

        }
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
