package com.goafter.learnviewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huashuolee on 2015/7/30.
 * 导航页的demo
 */
public class Guiding extends Activity {
    private List<View> views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guiding);
        initViews();
        Button btnEnter = (Button)views.get(2).findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guiding.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews(){
        //获取每一个views，并把这些views添加到ArrayList。
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(layoutInflater.inflate(R.layout.one,null));
        views.add(layoutInflater.inflate(R.layout.two,null));
        views.add(layoutInflater.inflate(R.layout.three,null));

        // New 一个ViewPagerAdapter对象，并把views 传进去
        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(views, this);

        //获ViewPager 并且setAdapter
        ViewPager vp = (ViewPager)findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
    }
}
