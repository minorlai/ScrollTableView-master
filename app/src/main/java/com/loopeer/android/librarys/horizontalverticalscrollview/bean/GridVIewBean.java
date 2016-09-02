package com.loopeer.android.librarys.horizontalverticalscrollview.bean;

import java.util.ArrayList;

/**
 * Created by laiyingtang on 2016/8/25.
 */
public class GridVIewBean {
    String number;
    String node;
    ArrayList<String> redArray;//红球的缓存数
    ArrayList<String> blueArray;//篮球的缓存数

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public ArrayList<String> getRedArray() {
        return redArray;
    }

    public void setRedArray(ArrayList<String> redArray) {
        this.redArray = redArray;
    }

    public ArrayList<String> getBlueArray() {
        return blueArray;
    }

    public void setBlueArray(ArrayList<String> blueArray) {
        this.blueArray = blueArray;
    }
}
