package com.loopeer.android.librarys.horizontalverticalscrollview;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.loopeer.android.librarys.horizontalverticalscrollview.adapter.BuyBlueAdapter;
import com.loopeer.android.librarys.horizontalverticalscrollview.utils.ToastUtil;
import com.loopeer.android.librarys.horizontalverticalscrollview.view.MyListView;

import java.util.ArrayList;

/**
 * Created by laiyingtang on 2016/8/26.
 */
public class BuyActivity extends Activity implements View.OnClickListener {
    private Button btn_back;
    private Button btn_save;
    private Toast toast;
    private Button btn_ordinary;//普通预约按钮
    private Button btn_selection;//机选预约按钮
    private XRecyclerView xRecyclerView;//list列表
    private CheckBox checkBox;
    private TextView tv_amount;//多少注标识
    private TextView tv_num;//倍投数量
    private Button btn_add;//增加按钮
    private Button btn_sub;//减去按钮
    private TextView tv_price;//下注价钱
    private Button btn_docum;//发起跟单按钮
    private Button btn_order;//预约按钮
    private String count;//倍投数量的content
    private ArrayList<String> blueList;//蓝球
    private ArrayList<String> redList;//红球
    private ArrayList<ArrayList<String>> blueRedList = new ArrayList<ArrayList<String>>();//蓝球红球的集合
    private BuyBlueAdapter buyAdapter;//适配器
    private Handler handler=new Handler();//刷新handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        initView();//初始化控件
        initClick();//初始化点击

        initListener();//刷新
        //蓝球的集合
        blueList = getIntent().getStringArrayListExtra("blueList");
        //红球的集合
        redList = getIntent().getStringArrayListExtra("redList");
        initData();//初始化数据
        System.out.println("蓝球：" + blueList + ";红球" + redList);
        //获取保存数据(红球和蓝球的集合)
        if (savedInstanceState != null) {
            blueList = savedInstanceState.getStringArrayList("blueList");
            redList = savedInstanceState.getStringArrayList("redList");
            blueRedList.add(redList);
            blueRedList.add(blueList);
        }

        if ((blueList != null && blueList.size() != 0) && (redList != null && redList.size() != 0)) {
            blueRedList.add(redList);
            blueRedList.add(blueList);
            xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);//下拉样式
            xRecyclerView.setLaodingMoreProgressStyle(ProgressStyle.BallScale);//上拉样式
            GridLayoutManager layoutManager = new GridLayoutManager(this, 1);//布局列表
            layoutManager.setOrientation(GridLayoutManager.VERTICAL);//布局排序
            xRecyclerView.setLayoutManager(layoutManager);//设置布局样式

            buyAdapter = new BuyBlueAdapter(blueRedList, this);
            xRecyclerView.setAdapter(buyAdapter);
            System.out.println("setAdapter");
            buyAdapter.notifyDataSetChanged();
        }

    }

    /**
     * 刷新
     */
    private void initListener() {
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //下拉
                        xRecyclerView.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //上拉
                        xRecyclerView.loadMoreComplete();
                    }
                },2000);
            }
        });
    }

    //初始化数据(注数、价格)
    private void initData() {
        if (blueList.size() == 1) {
            tv_amount.setText(blueList.size() + "");
            tv_price.setText(blueList.size() * 2 + "");
        } else {
            tv_amount.setText(blueList.size() * redList.size() + "");
            tv_price.setText(blueList.size() * redList.size() * 2 + "");
        }
    }


    //初始化点击
    private void initClick() {
        btn_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btn_ordinary.setOnClickListener(this);
        btn_selection.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_docum.setOnClickListener(this);
        btn_order.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_ordinary = (Button) findViewById(R.id.btn_ordinary);
        btn_selection = (Button) findViewById(R.id.btn_selection);
        xRecyclerView = (XRecyclerView) findViewById(R.id.listview);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        tv_amount = (TextView) findViewById(R.id.tv_amount);
        tv_num = (TextView) findViewById(R.id.tv_num);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        tv_price = (TextView) findViewById(R.id.tv_price);
        btn_docum = (Button) findViewById(R.id.btn_docum);
        btn_order = (Button) findViewById(R.id.btn_order);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back://返回
                finish();
                break;
            case R.id.btn_save://保存
                ToastUtil.ToaString(BuyActivity.this, "保存");
                break;
            case R.id.btn_ordinary://普通预约
                startActivity(new Intent(BuyActivity.this, GridViewActivity.class));
                break;
            case R.id.btn_selection://机选预约
                startActivity(new Intent(BuyActivity.this, GridViewActivity.class));
                break;
            case R.id.btn_add://增加按钮
                count = tv_num.getText().toString();
                int addNum = Integer.parseInt(count);
                if (addNum >= 0) {
                    addNum++;
                }
                tv_num.setText(addNum + "");//设置倍投数量
                break;
            case R.id.btn_sub://删减按钮
                count = tv_num.getText().toString();//先拿到倍投数量
                int subNum = Integer.parseInt(count);//转类型
                if (subNum > 0) {
                    subNum--;
                }
                tv_num.setText(subNum + "");//重新设置倍投数量
                break;
            case R.id.btn_docum://发起预约
                ToastUtil.ToaString(BuyActivity.this, "发起预约");
                break;
            case R.id.btn_order://预约
                ToastUtil.ToaString(BuyActivity.this, "预约");
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //蓝红球的集合
        blueList = getIntent().getStringArrayListExtra("blueList");
        //红球的集合
        redList = getIntent().getStringArrayListExtra("redList");
        // 切换屏幕方向会导致activity的摧毁和重建
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            System.out.println("屏幕切换");
        }
    }

    /**
     * 保存数据
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("blueList", blueList);
        outState.putStringArrayList("redList", redList);
    }
}
