package com.loopeer.android.librarys.horizontalverticalscrollview.app;

import android.app.Application;

import com.loopeer.android.librarys.horizontalverticalscrollview.bean.PriceOrderBean;

import java.util.ArrayList;

/**
 * Created by laiyingtang on 2016/8/27.
 */
public class MyApplication extends Application {
    private ArrayList<String> redList;  //红球集合
    private ArrayList<String> blueList;//蓝球集合
    private ArrayList<ArrayList<PriceOrderBean>> arrayListArrayList;//五个等级位数的list
    public static MyApplication application;

    public static MyApplication getInstace(){//单例
        if(application==null){
            application=new MyApplication();
        }
        return application;
    }

    public ArrayList<ArrayList<PriceOrderBean>> getArrayListArrayList() {
        return arrayListArrayList;
    }

    public void setArrayListArrayList(ArrayList<ArrayList<PriceOrderBean>> arrayListArrayList) {
        this.arrayListArrayList = arrayListArrayList;
    }

    public ArrayList<String> getRedList() {
        return redList;
    }

    public void setRedList(ArrayList<String> redList) {
        this.redList = redList;
    }

    public ArrayList<String> getBlueList() {
        return blueList;
    }

    public void setBlueList(ArrayList<String> blueList) {
        this.blueList = blueList;
    }
}
