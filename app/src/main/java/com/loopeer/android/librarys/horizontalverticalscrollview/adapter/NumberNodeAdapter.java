package com.loopeer.android.librarys.horizontalverticalscrollview.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.loopeer.android.librarys.horizontalverticalscrollview.R;
import com.loopeer.android.librarys.horizontalverticalscrollview.app.MyApplication;
import com.loopeer.android.librarys.horizontalverticalscrollview.bean.GridVIewBean;
import com.loopeer.android.librarys.horizontalverticalscrollview.fragment.BettingFragment;

import java.util.ArrayList;

import xyz.hanks.library.SmallBang;
import xyz.hanks.library.SmallBangListener;

/**
 * Created by laiyingtang on 2016/8/25.
 */
public class NumberNodeAdapter extends BaseAdapter {
    private ArrayList<GridVIewBean> gridList;
    private SmallBang smallBang;//点击动画的library类
    private Context context;
    public ArrayList<String> strings;


    public NumberNodeAdapter(ArrayList<GridVIewBean> gridList, Context context) {
        this.gridList = gridList;
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.number_node, null);
            holder.btn_number = (CheckBox) convertView.findViewById(R.id.btn_number);
            holder.tv_node = (TextView) convertView.findViewById(R.id.tv_node);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final GridVIewBean gridVIewBean = gridList.get(position);

        holder.btn_number.setText(gridVIewBean.getNumber());
        holder.tv_node.setText(gridVIewBean.getNode());
        strings=new ArrayList<String>();
        holder.btn_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 判断是否选中
                 */
                if (holder.btn_number.isChecked()) {
                    holder.btn_number.setBackgroundResource(R.drawable.solid_circular);
                    holder.btn_number.setTextColor(Color.WHITE);
                    strings.add(gridVIewBean.getNumber());

                } else {
                    holder.btn_number.setBackgroundResource(R.drawable.circular);
                    holder.btn_number.setTextColor(Color.BLACK);
                    int count=strings.indexOf(gridVIewBean.getNumber());
                    strings.remove(count);
                }
                MyApplication.getInstace().setRedList(strings);//设置红球的list
                System.out.println("选中的红球号码：----->" + strings.toString());
                //点击动画
                smallBang.bang(v, new SmallBangListener() {//可以用另一个方法指定大小：bang(final View view, float radius, SmallBangListener listener)
                    @Override
                    public void onAnimationStart() {//动画开始

                    }
                    @Override
                    public void onAnimationEnd() {//动画结束
                     // ToastUtil.ToaString(context,"号码:" + strings.toString());
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
