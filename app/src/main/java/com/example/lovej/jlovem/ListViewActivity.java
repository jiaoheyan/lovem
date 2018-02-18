package com.example.lovej.jlovem;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/18 0018.
 */

public class ListViewActivity extends Activity implements AdapterView.OnItemClickListener , AbsListView.OnScrollListener {
    private ListView listView;
    //创建简单适配器(并不简单,功能很强大)
    private SimpleAdapter simpleAdapter;
    //定义List泛型集合,里面存放的是Map键值对, listDate作为ListView的数据源
    private List<Map<String, Object>> listDate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏
        getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_listview);
        listView = (ListView) findViewById(R.id.listView);

        /*
         * 把ListView要显示的图片和文字声明出来,暂时存放在数组中,为插入到listDate做准备
         * 三个数组对应三个不同的显示信息,ListView每一个Item会根据你自定义的
         * 布局样式依次显示img[1],text1[1],text2[1]的内容
         * 其实我们还差一个,就是最右边的播放图标,由于每一个item的图标都是相同的,
         * 我们直接添加到集合中即可
         */
        int[] img = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        //图片右边的第一排文字
        String[] text1 = new String[]{"酷狗热歌", "DJ热碟", "网络红歌", "咖啡厅", "新歌", "经典", "抒情"};
        //图片右边的第二排文字
        String[] text2 = new String[]{"Beyond-光辉岁月", "许嵩-山水之间", "周杰伦-晴天", "许嵩-雅俗共赏", "周杰伦-龙卷风", "许嵩-千百度", "汪晨蕊-友情岁月"};

        /*
         * 实例化泛型集合listDate
         * 使用MyEclipse开发的朋友,你的实例化代码应该是这样的
         * listDate = new ArrayList<Map<String,Object>>();
         *
         * 但请注意:对于Android Studio来说这样写,你的代码底下会有烦人的波浪线,
         * 为什么会这样,有一种说法是:
         * 因为Android Studio需要的JDK版本是7.0及以上,这种JDK版本不支持此种写法
         */
        listDate = new ArrayList<>();

        /**
         * 利用for循环,把数据源添加到集合中(i不管小于哪个数组的长度都是可以的,
         * 三个数组的长度是相等的)
         * 第一层for循环是为了把相同的数据多次添加
         */
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < img.length; i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("img1", img[i]);
                map.put("text1", text1[i]);
                map.put("text2", text2[i]);
                map.put("img2", R.mipmap.ic_launcher);
                listDate.add(map);
            }
        }

        /*
         * 调用SimpleAdapter的构造方法
         * sim = new SimpleAdapter(context, data, resource, from, to);
         * context: 上下文
         * data:    数据源 List<? extends Map<String,?>> data
         *          一个Map所组成的List集合
         *          每一个Map(键-值对)中的键都必须包含所有在from中所指定的键
         * resource:ListView显示将要参照的布局文件ID
         * from:    Map中的键名
         * to:      绑定布局文件layout的ID,与from形成对应关系
         */
        simpleAdapter = new SimpleAdapter(ListViewActivity.this,
                listDate,
                R.layout.layout,
                new String[]{"img1", "text1", "text2", "img2"},
                new int[]{R.id.img1, R.id.t1, R.id.t2, R.id.img2});
        listView.setAdapter(simpleAdapter);

        //启用监听器
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);

    }

     /*
      * 拓展内容:如何监听ListView的每一项(Item)的事件(如点击播放)
      *          如何监听ListView的滚动事件(如淘宝的下拉刷新商品)
      * 监听器是程序和用户(或者系统)交互的桥梁
      * SimpleAdapter(适配器)是视图界面和数据源交互的桥梁
      */

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /**
         * 我们要实现的功能是:你点击了ListView的每一项时,会弹出该Item的文本信息
         *  listView.getItemAtPosition(position)得到的是一个Object对象,须把它转化成String类型
         *  方法调用后我们还应在onCreate()函数里面启用监听器
         */
        String text = "" + listView.getItemAtPosition(position);
        Toast.makeText(ListViewActivity.this, "position: " + position + "歌曲名: " + text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch(scrollState)
        {
            case SCROLL_STATE_FLING:

                Log.i("Main", "用户在手指离开屏幕之前，由于用力的划了一下，视图仍依靠惯性继续滑动");

                //模拟刷新功能
                Map<String, Object>map = new HashMap<>();
                map.put("img1", R.mipmap.ic_launcher);
                map.put("text1", "增加项");
                map.put("text2", "增加歌曲");
                map.put("img2", R.mipmap.ic_launcher);
                listDate.add(map);

                //通知视图界面更新
                simpleAdapter.notifyDataSetChanged();
                break;

            case SCROLL_STATE_IDLE:

                Log.i("Main","视图已经停止滑动");
                break;

            case SCROLL_STATE_TOUCH_SCROLL:

                Log.i("Main","手指没有离开屏幕，视图跟随手指滑动");
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
