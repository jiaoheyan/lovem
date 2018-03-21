package com.example.lovej.jlovem;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
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
    /**
     * 系统下拉栏默认的通知(API 16+)
     */
    public void notificationAPI_16p(View view) {
        // 获取NotificationManager管理者对象
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 创建一个PendingIntent，和Intent类似，不同的是由于不是马上调用，需要在下拉状态条出发的Activity，所以采用的是PendingIntent,即点击Notification跳转启动到哪个Activity
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        // 通过Notification.Builder来创建通知，注意API Level 16之后才支持
        Notification notificationAPI_16p = new Notification.Builder(this)
                // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap icon)
                .setSmallIcon(R.mipmap.ic_launcher)
                // 设置在status bar上显示的提示文字
                .setTicker("TickerText:" + "您有新短消息，请注意查收！")
                // 设置在下拉status bar后显示的标题
                .setContentTitle("这里是标题（API 16+）")
                // 设置在下拉status bar后显示的内容
                .setContentText("这里是显示的内容")
                // 关联PendingIntent
                .setContentIntent(pendingIntent)
                // 设置在下拉status bar后显示的数字
                .setNumber(1)
                // 需要注意build()是在API level 16及之后增加的，API11可以使用getNotificatin()来替代
                .build();
        // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。
        notificationAPI_16p.flags |= Notification.FLAG_AUTO_CANCEL;
        // 通过通知管理器来发起通知
        manager.notify(1, notificationAPI_16p);
    }

}
