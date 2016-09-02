package com.loopeer.android.librarys.horizontalverticalscrollview.ui;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopeer.android.librarys.horizontalverticalscrollview.R;
import com.loopeer.android.librarys.horizontalverticalscrollview.app.MyApplication;
import com.loopeer.android.librarys.horizontalverticalscrollview.bean.PriceOrderBean;
import com.loopeer.android.librarys.horizontalverticalscrollview.fragment.PriceOrderFragment;
import com.loopeer.android.librarys.horizontalverticalscrollview.utils.ToastUtil;

import java.util.ArrayList;

/**
 * Created by laiyingtang on 2016/8/31.
 */
public class PriceOrderActivity extends FragmentActivity implements View.OnClickListener {
    private Button btn_title;//重庆时时彩按钮
    private TextView tv_title;//title文本
    private Button btn_person;//客服
    private Button btn_back;//返回按钮
    private Button btn_clear;//清空按钮
    private TextView tv_num_notes;//多少注
    private TextView tv_price;//价钱
    private Button btn_bet;//投注
    private ArrayList<PriceOrderBean> fiveList = new ArrayList<PriceOrderBean>();
    private ArrayList<PriceOrderBean> fourList = new ArrayList<PriceOrderBean>();
    private ArrayList<PriceOrderBean> thirdList = new ArrayList<PriceOrderBean>();
    private ArrayList<PriceOrderBean> twoList = new ArrayList<PriceOrderBean>();
    private ArrayList<PriceOrderBean> oneList = new ArrayList<PriceOrderBean>();
    private ArrayList<ArrayList<PriceOrderBean>> allListData = new ArrayList<ArrayList<PriceOrderBean>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_order);
        initView();//初始化控件
        initClick();//监听初始化
        initFragment();//初始化fragment
    }

    //添加fragment
    private void initFragment() {
        PriceOrderFragment priceOrderFragment = new PriceOrderFragment();
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.replace(R.id.fragment, priceOrderFragment);
        transition.commit();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btn_title = (Button) findViewById(R.id.btn_title);
        tv_title = (TextView) findViewById(R.id.tv_title);
        btn_person = (Button) findViewById(R.id.btn_person);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        tv_num_notes = (TextView) findViewById(R.id.tv_num_notes);
        tv_price = (TextView) findViewById(R.id.tv_price);
        btn_bet = (Button) findViewById(R.id.btn_bet);
    }

    /**
     * 初始化监听
     */
    private void initClick() {
        btn_title.setOnClickListener(this);
        tv_title.setOnClickListener(this);
        btn_person.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_bet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_title:// 重庆时时彩
                break;
            case R.id.tv_title://title子玩法

                break;
            case R.id.btn_person://客服
                ToastUtil.ToaString(PriceOrderActivity.this, "人工客服");
                break;
            case R.id.btn_back://返回
                finish();
                break;
            case R.id.btn_clear://清空
                ArrayList<ArrayList<PriceOrderBean>> allData = MyApplication.getInstace().getArrayListArrayList();
                if (allData != null && allData.size() != 0) {
                    allData.clear();

                }
                break;
            case R.id.btn_bet://投注
                break;
        }
    }

    /**
     * 根据每个位数的list集合设置注数和价钱
     *
     * @param allNote
     */
    public void setNotePrice(ArrayList<PriceOrderBean> allNote, int type) {
        if (allNote == null || allNote.size() == 0) {
            return;
        } else {
            if (type == 5) {
                fiveList.clear();
                fiveList = allNote;
            } else if (type == 4) {
                fourList.clear();
                fourList = allNote;
            } else if (type == 3) {
                thirdList.clear();
                thirdList = allNote;
            } else if (type == 2) {
                twoList.clear();
                twoList = allNote;
            } else if (type == 1) {
                oneList.clear();
                oneList = allNote;
            }
            allListData.add(fiveList);
            allListData.add(fourList);
            allListData.add(thirdList);
            allListData.add(twoList);
            allListData.add(oneList);
            MyApplication.getInstace().setArrayListArrayList(allListData);
        }
    }

}
