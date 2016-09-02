package com.loopeer.android.librarys.horizontalverticalscrollview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by laiyingtang on 2016/8/25.
 */
public class ActionBarAct extends AppCompatActivity {
    private Button btn_into;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.immerse_layout);

        View decorview = getWindow().getDecorView();//获取当前系统的DecorView
        //这种方式适用于API19以上
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;    //表示全屏（隐藏状态栏与导航栏）
        decorview.setSystemUiVisibility(option);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置状态栏透明

        if (Build.VERSION.SDK_INT >= 21) {//android5.0以上才可以使用
            View view = getWindow().getDecorView();//获取当前的DecorView
            int state = view.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | view.SYSTEM_UI_FLAG_LAYOUT_STABLE | view.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | view.SYSTEM_UI_FLAG_FULLSCREEN;//隐藏状态栏//同时隐藏状态栏和导航栏

            decorview.setSystemUiVisibility(state);
            getWindow().setStatusBarColor(Color.TRANSPARENT);//设置状态栏为透明(需要5.0以上才支持)
            getWindow().setNavigationBarColor(Color.TRANSPARENT);//隐藏导航栏
        }

        ActionBar actionBar = getSupportActionBar();//获取独立的actionbar
        actionBar.hide();//隐藏

        btn_into= (Button) findViewById(R.id.btn_into);
        btn_into.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActionBarAct.this,GridViewActivity.class));
            }
        });



    }

    /**
     * 要使用沉浸式模式的话，只要重写onWindowFocusChanged即可(会延迟)
     */
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE//状态栏
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//导航栏
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //全屏状态
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION   //隐藏导航栏
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN    //全屏
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);//
//        }
//        ActionBar actionBar = getSupportActionBar();//获取独立的actionbar
//        actionBar.hide();//隐藏
//    }
}
