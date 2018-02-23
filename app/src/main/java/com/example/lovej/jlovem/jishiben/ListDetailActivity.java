package com.example.lovej.jlovem.jishiben;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lovej.jlovem.R;

/**
 * Created by Administrator on 2018/2/22 0022.
 */

public class ListDetailActivity extends AppCompatActivity {
    TextView nameTV,thingTV,timeTV;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdetail);
        nameTV = (TextView)findViewById(R.id.nameTextView);
        thingTV = (TextView)findViewById(R.id.thingTextView);
        timeTV = (TextView)findViewById(R.id.timeTextView);
        Intent intent = getIntent();
        timeTV.setText(intent.getStringExtra("time"));
        nameTV.setText(intent.getStringExtra("name"));
        thingTV.setText(intent.getStringExtra("thing"));

    }
}
