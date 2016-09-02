package com.loopeer.android.librarys.horizontalverticalscrollview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.loopeer.android.librarys.horizontalverticalscrollview.R;
import com.loopeer.android.librarys.horizontalverticalscrollview.utils.ToastUtil;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

/**
 * Created by laiyingtang on 2016/8/27.
 */
public class BuyBlueAdapter extends RecyclerView.Adapter<BuyBlueAdapter.ViewHolder> {
    private ArrayList<String> blueList;//蓝球
    private ArrayList<String> redList;//红球
    private ArrayList<ArrayList<String>> blueRedList;//蓝红球的集合
    private Context context;
    private boolean isOnMeasure = true;

    public BuyBlueAdapter(ArrayList<ArrayList<String>> blueRedList, Context context) {
        this.blueRedList = blueRedList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder=new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_listview_item,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        redList = blueRedList.get(0);
        blueList = blueRedList.get(1);
        String red = StringUtils.strip(redList.toString(), "[]").replace(",", " ");//去除list的“[]”和逗号
        String blue = StringUtils.strip(blueList.toString(), "[]").replace(",", " ");
        System.out.println("getView调用了多少次？" + position);
        holder.tv_red_number.setText(Html.fromHtml("<font color=\"RED\">" + red + "</font>&nbsp<font color=\"BLUE\">" + blue + "</font>"));//第一个是红球的

        if (blueList.size() == 1) {
            holder.tv_single_compound.setText("单式" + blueList.size() + "注");
        } else if (blueList.size() > 1) {
            holder.tv_single_compound.setText("复式" + blueList.size() * redList.size() + "注");
        }
        //删除图标的监听
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blueRedList.remove(position);
                ToastUtil.ToaString(context,"点击的："+position);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (blueRedList == null || blueRedList.size() == 0) {
            return 0;
        }
        return blueRedList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        Button btn_delete;
        TextView tv_red_number;
        TextView tv_single_compound;

        public ViewHolder(View itemView) {
            super(itemView);
            btn_delete = (Button) itemView.findViewById(R.id.btn_delete);
            tv_red_number = (TextView) itemView.findViewById(R.id.tv_red_number);
            tv_single_compound = (TextView) itemView.findViewById(R.id.tv_single_compound);
        }
    }
}
