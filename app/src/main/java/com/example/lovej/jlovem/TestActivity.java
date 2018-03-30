package com.example.lovej.jlovem;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
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
