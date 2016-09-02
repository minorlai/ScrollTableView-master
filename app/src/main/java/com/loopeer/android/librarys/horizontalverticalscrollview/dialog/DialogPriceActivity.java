package com.loopeer.android.librarys.horizontalverticalscrollview.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopeer.android.librarys.horizontalverticalscrollview.R;

import java.util.ArrayList;

/**
 * 价钱等级dialog
 * Created by laiyingtang on 2016/9/2.
 */
public class DialogPriceActivity extends Activity implements View.OnClickListener{
    private TextView tv_yuan;
    private TextView tv_jiao;
    private TextView tv_fen;
    private TextView tv_li;
    private ArrayList<TextView> textViews=new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_price);
        setFinishOnTouchOutside(true);//点击区域外部消失
        tv_yuan= (TextView) findViewById(R.id.tv_yuan);
        tv_jiao= (TextView) findViewById(R.id.tv_jiao);
        tv_fen= (TextView) findViewById(R.id.tv_fen);
        tv_li= (TextView) findViewById(R.id.tv_li);

        tv_yuan.setOnClickListener(this);
        tv_jiao.setOnClickListener(this);
        tv_fen.setOnClickListener(this);
        tv_li.setOnClickListener(this);

        textViews.add(tv_yuan);
        textViews.add(tv_jiao);
        textViews.add(tv_fen);
        textViews.add(tv_li);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_yuan:
                changeTvBg(tv_yuan);
                Intent yuan=new Intent();
                yuan.putExtra("price",tv_yuan.getText().toString());
                setResult(RESULT_OK,yuan);
                finish();
                break;
            case R.id.tv_jiao:
                changeTvBg(tv_jiao);
                Intent jiao=new Intent();
                jiao.putExtra("price",tv_jiao.getText().toString());
                setResult(RESULT_OK,jiao);
                finish();
                break;
            case R.id.tv_fen:
                changeTvBg(tv_fen);
                Intent fen=new Intent();
                fen.putExtra("price",tv_fen.getText().toString());
                setResult(RESULT_OK,fen);
                finish();
                break;
            case R.id.tv_li:
                changeTvBg(tv_li);
                Intent li=new Intent();
                li.putExtra("price",tv_li.getText().toString());
                setResult(RESULT_OK,li);
                finish();
                break;
        }
    }

    /**
     * 改变layoutList的背景
     */
    private void changeTvBg(TextView layout) {
        for (int i = 0; i < textViews.size(); i++) {
            if (textViews.get(i) == layout) {
                textViews.get(i).setBackgroundResource(R.color.chengse);//橙色（选中）
            } else {
                textViews.get(i).setBackgroundResource(R.color.white);//白色（未选中）
            }
        }
    }
}
