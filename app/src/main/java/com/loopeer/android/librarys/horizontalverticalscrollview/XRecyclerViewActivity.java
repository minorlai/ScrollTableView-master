package com.loopeer.android.librarys.horizontalverticalscrollview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.loopeer.android.librarys.horizontalverticalscrollview.adapter.XRecyclerAdapter;
import com.loopeer.android.librarys.horizontalverticalscrollview.bean.MenuBean;

import java.util.ArrayList;

/**
 * Created by laiyingtang on 2016/8/24.
 */
public class XRecyclerViewActivity extends Activity {
    private XRecyclerView xRecyclerView;
    private XRecyclerAdapter xRecyclerAdapter;
    private Handler handler = new Handler();
    private ArrayList<MenuBean> menuList = new ArrayList<MenuBean>();
    private String title[] = {"竞彩足球", "竞彩篮球", "双色球", "大乐透", "胜负彩", "任选九", "福彩3D", "北京单场", "排列三", "排列五", "七星彩", "七乐彩"};
    private String content[] = {"返奖率高达73%", "胜负玩法中奖率高", "奖池:908,084,049", "奖池:3,309,928", "2元最高可中500万", "奖金最高500万", "2元可中1040元", "猜中一场就有奖", "2元赢1040元", "五码赢10万", "奖池:12,503,577", "百万富翁生产线"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_listview);
        initData();//初始化数据
        initViewData();//初始化控件
        initListener();//监听
    }

    //监听事件
    private void initListener() {
        xRecyclerAdapter.setOnItemClickListener(new XRecyclerAdapter.onClickListener() {
            @Override
            public void onItemClick(View view, int position) {//点击
                Toast.makeText(XRecyclerViewActivity.this,"点击："+menuList.get(position-1).getTitle(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(XRecyclerViewActivity.this,ViewPagerActivity.class));
            }

            @Override
            public void onItemLongClick(View view, int position) {//长按
                Toast.makeText(XRecyclerViewActivity.this,"长按："+menuList.get(position-1).getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    //初始化
    private void initViewData() {
        xRecyclerView = (XRecyclerView) findViewById(R.id.xrecyclerview);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.LineScaleParty);//下拉样式
        xRecyclerView.setLaodingMoreProgressStyle(ProgressStyle.BallScale);//上拉样式
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);//布局列表
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);//布局排序
        xRecyclerView.setLayoutManager(layoutManager);//设置布局样式

        xRecyclerAdapter = new XRecyclerAdapter(menuList, this);
        xRecyclerView.setAdapter(xRecyclerAdapter);//关联适配器

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {//下拉
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xRecyclerView.refreshComplete();//下拉完成

                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {//上拉
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xRecyclerView.loadMoreComplete();//上拉完成

                    }
                }, 2000);
            }
        });


    }

    //获取数据
    private void initData() {
        MenuBean menuBean = null;
        for (int i = 0; i < 12; i++) {
            menuBean = new MenuBean();
            menuBean.setTitle(title[i]);
            menuBean.setContent(content[i]);
            menuList.add(menuBean);
        }
    }

}
