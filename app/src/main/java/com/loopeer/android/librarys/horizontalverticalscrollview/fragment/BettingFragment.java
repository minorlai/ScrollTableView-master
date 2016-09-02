package com.loopeer.android.librarys.horizontalverticalscrollview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.loopeer.android.librarys.horizontalverticalscrollview.R;
import com.loopeer.android.librarys.horizontalverticalscrollview.adapter.BlueNumberNodeAdapter;
import com.loopeer.android.librarys.horizontalverticalscrollview.adapter.NumberNodeAdapter;
import com.loopeer.android.librarys.horizontalverticalscrollview.bean.GridVIewBean;

import java.util.ArrayList;

/**
 * 红球篮球的页面初始化数据
 * Created by laiyingtang on 2016/8/25.
 */
public class BettingFragment extends Fragment {
    private View rootView;
    private GridView red_gridview;
    private GridView blue_gridview;
    private ArrayList<GridVIewBean> gridViewList = new ArrayList<GridVIewBean>();
    private ArrayList<GridVIewBean> blueList = new ArrayList<GridVIewBean>();
    private BlueNumberNodeAdapter numberNodeAdapter;
    private BlueNumberNodeAdapter blueNumberNodeAdapter;

    private int type = 1;//1标识红球

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.gridview_layout, null);
        red_gridview = (GridView) rootView.findViewById(R.id.red_gridview);
        blue_gridview = (GridView) rootView.findViewById(R.id.blue_gridview);

        initData();//先初始化红球数据
        initBlueData();//篮球的数据
        numberNodeAdapter = new BlueNumberNodeAdapter(gridViewList, getActivity(),1);
        red_gridview.setAdapter(numberNodeAdapter);
        blue_gridview.setAdapter(numberNodeAdapter);
        return rootView;

    }


    //初始化
    private void initData() {
        GridVIewBean grid;
        for (int i = 1; i <= 35; i++) {
            grid = new GridVIewBean();
            grid.setNumber(i + "");
            grid.setNode(i + "");
            gridViewList.add(grid);
        }
    }

    /**
     * 篮球
     */
    private void initBlueData() {
        GridVIewBean gridBlue;
        for (int i = 1; i < 36; i++) {
            gridBlue = new GridVIewBean();
            gridBlue.setNumber(i + "");
            gridBlue.setNode(i + "");
            blueList.add(gridBlue);
        }
    }

}
