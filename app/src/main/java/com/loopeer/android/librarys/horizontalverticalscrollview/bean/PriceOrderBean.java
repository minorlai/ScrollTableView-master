package com.loopeer.android.librarys.horizontalverticalscrollview.bean;

/**
 * Created by laiyingtang on 2016/8/31.
 */
public class PriceOrderBean {
    String title;
    String number;
    boolean isChoose;

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
