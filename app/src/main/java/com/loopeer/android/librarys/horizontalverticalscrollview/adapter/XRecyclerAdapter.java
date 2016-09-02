package com.loopeer.android.librarys.horizontalverticalscrollview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopeer.android.librarys.horizontalverticalscrollview.R;
import com.loopeer.android.librarys.horizontalverticalscrollview.bean.MenuBean;

import java.util.ArrayList;

/**
 * Created by laiyingtang on 2016/8/24.
 */
public class XRecyclerAdapter extends RecyclerView.Adapter<XRecyclerAdapter.ViewHolder> {

    private ArrayList<MenuBean> menuList;
    private Context context;

    public XRecyclerAdapter(ArrayList<MenuBean> menuList, Context context) {
        this.menuList = menuList;
        this.context = context;
    }

    //这里添加布局文件
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_img_text, parent, false));
        return viewHolder;
    }

    /**
     * 设置数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MenuBean menu = menuList.get(position);
        String title = menu.getTitle();
        String content = menu.getContent();
        holder.tv_title.setText(title);
        holder.tv_content.setText(content);
    }

    /**
     * 这里需要作出判断
     * @return
     */
    @Override
    public int getItemCount() {
        if (menuList == null || menuList.size() == 0) {
            return 0;
        }
        return menuList.size();
    }

    //接口
    private onClickListener item = null;
    //viewHolder类
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tv_title;
        TextView tv_content;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            //监听点击
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        //添加点击监听
        @Override
        public void onClick(View v) {
            if (item != null) {
                item.onItemClick(v, getLayoutPosition());
            }
        }
        //长按监听
        @Override
        public boolean onLongClick(View v) {
            if (item != null) {
                item.onItemLongClick(v, getLayoutPosition());
            }
            return false;
        }

    }
    //构造调用
    public void setOnItemClickListener(onClickListener click) {
        this.item = click;
    }
    //接口
    public interface onClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
}
