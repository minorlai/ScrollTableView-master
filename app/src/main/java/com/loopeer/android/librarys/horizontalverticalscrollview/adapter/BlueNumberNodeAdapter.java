package com.loopeer.android.librarys.horizontalverticalscrollview.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.loopeer.android.librarys.horizontalverticalscrollview.GridViewActivity;
import com.loopeer.android.librarys.horizontalverticalscrollview.R;
import com.loopeer.android.librarys.horizontalverticalscrollview.app.MyApplication;
import com.loopeer.android.librarys.horizontalverticalscrollview.bean.GridVIewBean;
import com.loopeer.android.librarys.horizontalverticalscrollview.fragment.BettingFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

import xyz.hanks.library.SmallBang;
import xyz.hanks.library.SmallBangListener;

/**
 * Created by laiyingtang on 2016/8/25.
 */
public class BlueNumberNodeAdapter extends BaseAdapter {
    private ArrayList<GridVIewBean> gridList;
    private SmallBang smallBang;//点击动画的library类
    private Toast toast = null;//吐司
    private Context context;
    private ArrayList<String> blueStrings = new ArrayList<String>();//蓝色球
    private ArrayList<String> redStrings = new ArrayList<String>();//红球
    private int type;//标识蓝红球

    public BlueNumberNodeAdapter(ArrayList<GridVIewBean> gridList, Context context, int type) {
        this.gridList = gridList;
        this.context = context;
        this.type = type;
        smallBang = SmallBang.attach2Window((Activity) context);//动画
    }

    @Override
    public int getCount() {
        if (gridList == null || gridList.size() == 0) {
            return 0;
        }
        return gridList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_node, null);
            holder.btn_number = (CheckBox) convertView.findViewById(R.id.btn_number);
            holder.tv_node = (TextView) convertView.findViewById(R.id.tv_node);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final GridVIewBean gridVIewBean = gridList.get(position);

        holder.btn_number.setText(gridVIewBean.getNumber());
        holder.tv_node.setText(gridVIewBean.getNode());
        holder.btn_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.btn_number.isChecked()) {
                    if (type == 1) {//1为红球
                        holder.btn_number.setBackgroundResource(R.drawable.solid_circular);
                        redStrings.add(gridVIewBean.getNumber());
                    } else {
                        holder.btn_number.setBackgroundResource(R.drawable.blue_solid_circular);
                        blueStrings.add(gridVIewBean.getNumber());
                    }
                    holder.btn_number.setTextColor(Color.WHITE);
                } else {
                    holder.btn_number.setBackgroundResource(R.drawable.circular);
                    holder.btn_number.setTextColor(Color.BLACK);
                    if (type == 1) {
                        int redCount = redStrings.indexOf(gridVIewBean.getNumber());//红球
                        redStrings.remove(redCount);
                    } else {
                        int count = blueStrings.indexOf(gridVIewBean.getNumber());
                        blueStrings.remove(count);
                    }
                }
                System.out.println("红球：" + redStrings + "*************" + "蓝球：" + blueStrings);
                MyApplication.getInstace().setRedList(redStrings);//红球的list
                MyApplication.getInstace().setBlueList(blueStrings);//设置蓝球的list
                //点击动画
                smallBang.bang(v, new SmallBangListener() {//可以用另一个方法指定大小：bang(final View view, float radius, SmallBangListener listener)
                    @Override
                    public void onAnimationStart() {//动画开始
                    }

                    @Override
                    public void onAnimationEnd() {//动画结束
//                        ToastStr("号码:" + gridVIewBean.getNumber());
                    }
                });
            }
        });
        return convertView;
    }

    public class ViewHolder {
        CheckBox btn_number;
        TextView tv_node;
    }
}
