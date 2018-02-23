package com.example.lovej.jlovem.jishiben;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovej.jlovem.R;

import java.util.List;



/**
 * Created by Administrator on 2018/2/20 0020.
 */

public class MainPageActivity extends AppCompatActivity  {

    private String name;
    private String showsex = "男";
    private String str = null;
    private SqlDao dao;
    private Student stu;
    private Student stu2;
    private List<Student> list;
    private MyAdapter adapter;
    private ListView listView1;
    private PopupWindow pw;
    private TextView delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        initView();
        initDate();
    }
    //初始化视图
    private void initView() {
        listView1=(ListView) findViewById(R.id.listView1);

    }
    private void intentMsg(){
        Intent intent = getIntent();
        if (intent.getStringExtra("class").trim().indexOf("JiShiBenActivity")==0){
            name = intent.getStringExtra("timu").trim();
            showsex = intent.getStringExtra("neirong").trim();
            str = intent.getStringExtra("timers");
            stu=new Student(name,showsex,str);

            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(showsex)){
                Toast.makeText(MainPageActivity.this, "添加信息不能为空", Toast.LENGTH_LONG).show();

            }else{

                boolean add = dao.add(stu);
                if(add){
                    list=dao.findAll();
                    adapter.notifyDataSetInvalidated();
                    Toast.makeText(MainPageActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainPageActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    //初始化数据
    private void initDate() {
        dao=new SqlDao(MainPageActivity.this);
        list=dao.findAll();
        adapter=new MyAdapter();
        listView1.setAdapter(adapter);
        intentMsg();
        /**
         * listview的条目点击事件
         */
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String name = list.get(position).getName();//这句是关键，通过postion下标拿到你list集合中的数据，list集合中有你的数据，.出来去接受就行了。
                String thing = list.get(position).getSex();
                String time = list.get(position).getTimers();
                Intent intent = new Intent(MainPageActivity.this, ListDetailActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("thing",thing);
                intent.putExtra("time",time);
                startActivity(intent);
            }
        });
        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            private String na;

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                na = list.get(position).getName();
                View v = View.inflate(MainPageActivity.this, R.layout.adapter_popu_window, null);
                if (pw != null) {
                    pw.dismiss();//让弹出的PopupWindow消失
                    pw = null;
                }
                pw = new PopupWindow(v, -2, -2);
                int [] location=new int[2];
                view.getLocationInWindow(location);
                pw.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                pw.showAtLocation(parent,Gravity.RIGHT+ Gravity.TOP, 20,location[1]-5 );//设置显示的位置
                ScaleAnimation animation = new ScaleAnimation(0.3f, 1f, 0.3f, 1f, Animation.RELATIVE_TO_SELF,
                        Animation.RELATIVE_TO_SELF);//弹出的动画
                animation.setDuration(400);//设置动画时间
                v.startAnimation(animation);//开启动画
                delete = (TextView)v.findViewById(R.id.tv_delete);
                /**
                 * 删除每一个item上的数据
                 */
                delete.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dao.delete(na);
                        list.remove(position);//移除item的条目
                        list=dao.findAll();//调用查询所有重新再查找一遍
                        adapter.notifyDataSetChanged();//更新适配器
                    }
                });
                return true;
            }
        });

        /**
         * listview的滑动监听
         * 当鼠标上下滑动的时候让PopupWindow消失
         */
        listView1.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if(pw!=null){
                    pw.dismiss();
                    pw=null;
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter{

        private String sex2;
        private View view;
        private String name2;
        private String timers2;
        @SuppressLint("ViewHolder") @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null ;//设置静态类使其初始化
            if(convertView==null){

                holder = new ViewHolder();//创建holder对象
                view = View.inflate(MainPageActivity.this, R.layout.item,null );

                holder.tv_name = (TextView) view.findViewById(R.id.tv_n);
                holder.tv_sex = (TextView) view.findViewById(R.id.tv_s);
                holder.tv_timers = (TextView) view.findViewById(R.id.tv_timers);

                view.setTag(holder);//用来保存一些数据结构。
            }else{
                view=convertView;//复用历史缓存
                holder=(ViewHolder) view.getTag();

            }
            name2 = list.get(position).getName();
            sex2 = list.get(position).getSex();
            timers2 = list.get(position).getTimers();

            holder.tv_name.setText(name2);
            holder.tv_sex.setText(sex2);
            holder.tv_timers.setText(timers2);

            return view;
        }
        @Override
        public int getCount() {
            return list.size();	//返回list集合中的数据个数
        }

        @Override
        public Object getItem(int position) {

            return null;
        }

        @Override
        public long getItemId(int position) {

            return 0;
        }



    }
    //ViewHolder静态类
    static class ViewHolder{
        TextView tv_name;
        TextView tv_sex;
        TextView tv_timers;
    }

}