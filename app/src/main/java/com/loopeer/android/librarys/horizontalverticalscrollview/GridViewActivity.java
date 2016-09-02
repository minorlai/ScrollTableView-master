package com.loopeer.android.librarys.horizontalverticalscrollview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopeer.android.librarys.horizontalverticalscrollview.adapter.BlueNumberNodeAdapter;
import com.loopeer.android.librarys.horizontalverticalscrollview.adapter.NumberNodeAdapter;
import com.loopeer.android.librarys.horizontalverticalscrollview.app.MyApplication;
import com.loopeer.android.librarys.horizontalverticalscrollview.bean.GridVIewBean;
import com.loopeer.android.librarys.horizontalverticalscrollview.fragment.BettingFragment;
import com.loopeer.android.librarys.horizontalverticalscrollview.utils.ToastUtil;

import java.util.ArrayList;

/**
 * Created by laiyingtang on 2016/8/25.
 */
public class GridViewActivity extends FragmentActivity implements View.OnClickListener {
    private Button btn_back;//返回
    private TextView tv_periods;//title
    private Button btn_login;//登录
    private Toast toast;
    private Button btn_choosed;//"选好了"按钮
    public ArrayList<String> blueList;//蓝球的list
    public ArrayList<String> redList;//红球的list
    private TextView tv_number;//多少注
    private TextView tv_price;//多少钱

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_betting);
        btn_back = (Button) findViewById(R.id.btn_back);
        tv_periods = (TextView) findViewById(R.id.tv_periods);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_choosed = (Button) findViewById(R.id.btn_choosed);
        tv_number= (TextView) findViewById(R.id.tv_number);
        tv_price= (TextView) findViewById(R.id.tv_price);

        btn_back.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_choosed.setOnClickListener(this);
        initFragment();//加入fragment
    }

    //替换
    private void initFragment() {
        BettingFragment bettingFragment = new BettingFragment();
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, bettingFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_login:
                ToastUtil.ToaString(GridViewActivity.this, "以后再登录吧！现在臣妾做不到啊!");
                break;
            case R.id.btn_choosed:
                redList = MyApplication.getInstace().getRedList();
                blueList = MyApplication.getInstace().getBlueList();
                System.out.println("红球："+redList+"---蓝球:"+blueList);
                if (redList == null || redList.size() == 0) {
                    ToastUtil.ToaString(GridViewActivity.this, "请选择红球");
                } else if (redList.size() < 6) {
                    ToastUtil.ToaString(GridViewActivity.this, "至少选6个红球！");
                } else if (blueList == null || blueList.size() == 0) {
                    ToastUtil.ToaString(GridViewActivity.this, "至少选1个蓝球！");
                } else {
                    Intent intent = new Intent(GridViewActivity.this, BuyActivity.class);
                    intent.putStringArrayListExtra("blueList", blueList);
                    intent.putStringArrayListExtra("redList", redList);
                    startActivity(intent);
                }
                break;
        }
    }

}
