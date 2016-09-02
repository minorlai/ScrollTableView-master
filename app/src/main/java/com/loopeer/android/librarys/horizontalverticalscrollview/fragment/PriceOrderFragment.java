package com.loopeer.android.librarys.horizontalverticalscrollview.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopeer.android.librarys.horizontalverticalscrollview.R;
import com.loopeer.android.librarys.horizontalverticalscrollview.adapter.FiveStartsAdapter;
import com.loopeer.android.librarys.horizontalverticalscrollview.adapter.FourStartsAdapter;
import com.loopeer.android.librarys.horizontalverticalscrollview.adapter.OneStartsAdapter;
import com.loopeer.android.librarys.horizontalverticalscrollview.adapter.ThirdStartsAdapter;
import com.loopeer.android.librarys.horizontalverticalscrollview.adapter.TwoStartsAdapter;
import com.loopeer.android.librarys.horizontalverticalscrollview.bean.PriceOrderBean;
import com.loopeer.android.librarys.horizontalverticalscrollview.dialog.DialogPriceActivity;
import com.loopeer.android.librarys.horizontalverticalscrollview.ui.PriceOrderActivity;
import com.loopeer.android.librarys.horizontalverticalscrollview.utils.ToastUtil;
import com.loopeer.android.librarys.horizontalverticalscrollview.utils.WeakHandler;
import com.loopeer.android.librarys.horizontalverticalscrollview.view.MyGridView;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by laiyingtang on 2016/8/31.
 */
public class PriceOrderFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TextView tv_date;
    private TextView tv_hour, tv_minute, tv_second;
    //万位到个位的adapter
    private OneStartsAdapter oneStartsAdapter;
    private TwoStartsAdapter twoStartsAdapter;
    private ThirdStartsAdapter thirdStartsAdapter;
    private FourStartsAdapter fourStartsAdapter;
    private FiveStartsAdapter fiveStartsAdapter;
    private ArrayList<PriceOrderBean> fiveList = new ArrayList<PriceOrderBean>();
    private ArrayList<PriceOrderBean> fourList = new ArrayList<PriceOrderBean>();
    private ArrayList<PriceOrderBean> thirdList = new ArrayList<PriceOrderBean>();
    private ArrayList<PriceOrderBean> twoList = new ArrayList<PriceOrderBean>();
    private ArrayList<PriceOrderBean> oneList = new ArrayList<PriceOrderBean>();
    private MyGridView wan_gridview;
    private MyGridView qian_gridview;
    private MyGridView bai_gridview;
    private MyGridView shi_gridview;
    private MyGridView ge_gridview;
    //6个筛选按钮
    private Button btn_wan_quan, btn_qian_quan, btn_bai_quan, btn_shi_quan, btn_ge_quan;
    private Button btn_wan_da, btn_qian_da, btn_bai_da, btn_shi_da, btn_ge_da;
    private Button btn_wan_xiao, btn_qian_xiao, btn_bai_xiao, btn_shi_xiao, btn_ge_xiao;
    private Button btn_wan_ji, btn_qian_ji, btn_bai_ji, btn_shi_ji, btn_ge_ji;
    private Button btn_wan_ou, btn_qian_ou, btn_bai_ou, btn_shi_ou, btn_ge_ou;
    private Button btn_wan_clear, btn_qian_clear, btn_bai_clear, btn_shi_clear, btn_ge_clear;
    //底部加倍
    private Button btn_bonus;
    private Button btn_sub;
    private EditText et_number;
    private Button btn_add;
    private RelativeLayout layout_price;//元
    private TextView btn_bottom_price;//字体

    private static int BTN_FIVE = 5;
    private static int BTN_FOUR = 4;
    private static int BTN_THIRD = 3;
    private static int BTN_TWO = 2;
    private static int BTN_ONE = 1;
    private int checkNum; // 记录选中的条目数量
    private List<String> listItemID = new ArrayList<String>();
    private String count;//储存倍数
    private static int REQUEST = 100;//请求码
    private PriceOrderActivity priceOrderActivity=new PriceOrderActivity();

    private MyCountDownTimer mc;
    private long hour, minute, second;

    private WeakHandler weakHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mc = new MyCountDownTimer(getCountDownTime(minute, second) * 1000, 1000);
                    mc.start();
                    break;
                case 1:
                    ToastUtil.ToaString(getActivity(), "获取时间失败请查看您的网络");
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_price_order, null);
        initView(view);//初始化控件
        initClick();//初始化点击
        initGridData();//初始化gridview的数据
        getBeijingTime();//获取时间
        return view;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        tv_hour = (TextView) view.findViewById(R.id.tv_hour);
        tv_minute = (TextView) view.findViewById(R.id.tv_minute);
        tv_second = (TextView) view.findViewById(R.id.tv_second);
        //万位
        wan_gridview = (MyGridView) view.findViewById(R.id.wan_gridview);
        btn_wan_quan = (Button) view.findViewById(R.id.btn_wan_quan);
        btn_wan_da = (Button) view.findViewById(R.id.btn_wan_da);
        btn_wan_xiao = (Button) view.findViewById(R.id.btn_wan_xiao);
        btn_wan_ji = (Button) view.findViewById(R.id.btn_wan_ji);
        btn_wan_ou = (Button) view.findViewById(R.id.btn_wan_ou);
        btn_wan_clear = (Button) view.findViewById(R.id.btn_wan_clear);
        //千位
        qian_gridview = (MyGridView) view.findViewById(R.id.qian_gridview);
        btn_qian_quan = (Button) view.findViewById(R.id.btn_qian_quan);
        btn_qian_da = (Button) view.findViewById(R.id.btn_qian_da);
        btn_qian_xiao = (Button) view.findViewById(R.id.btn_qian_xiao);
        btn_qian_ji = (Button) view.findViewById(R.id.btn_qian_ji);
        btn_qian_ou = (Button) view.findViewById(R.id.btn_qian_ou);
        btn_qian_clear = (Button) view.findViewById(R.id.btn_qian_clear);

        //百位
        bai_gridview = (MyGridView) view.findViewById(R.id.bai_gridview);
        btn_bai_quan = (Button) view.findViewById(R.id.btn_bai_quan);
        btn_bai_da = (Button) view.findViewById(R.id.btn_bai_da);
        btn_bai_xiao = (Button) view.findViewById(R.id.btn_bai_xiao);
        btn_bai_ji = (Button) view.findViewById(R.id.btn_bai_ji);
        btn_bai_ou = (Button) view.findViewById(R.id.btn_bai_ou);
        btn_bai_clear = (Button) view.findViewById(R.id.btn_bai_clear);

        //十位
        shi_gridview = (MyGridView) view.findViewById(R.id.shi_gridview);
        btn_shi_quan = (Button) view.findViewById(R.id.btn_shi_quan);
        btn_shi_da = (Button) view.findViewById(R.id.btn_shi_da);
        btn_shi_xiao = (Button) view.findViewById(R.id.btn_shi_xiao);
        btn_shi_ji = (Button) view.findViewById(R.id.btn_shi_ji);
        btn_shi_ou = (Button) view.findViewById(R.id.btn_shi_ou);
        btn_shi_clear = (Button) view.findViewById(R.id.btn_shi_clear);
        //个位
        ge_gridview = (MyGridView) view.findViewById(R.id.ge_gridview);
        btn_ge_quan = (Button) view.findViewById(R.id.btn_ge_quan);
        btn_ge_da = (Button) view.findViewById(R.id.btn_ge_da);
        btn_ge_xiao = (Button) view.findViewById(R.id.btn_ge_xiao);
        btn_ge_ji = (Button) view.findViewById(R.id.btn_ge_ji);
        btn_ge_ou = (Button) view.findViewById(R.id.btn_ge_ou);
        btn_ge_clear = (Button) view.findViewById(R.id.btn_ge_clear);

        btn_bonus = (Button) view.findViewById(R.id.btn_bonus);
        btn_sub = (Button) view.findViewById(R.id.btn_sub);
        et_number = (EditText) view.findViewById(R.id.et_number);
        btn_add = (Button) view.findViewById(R.id.btn_add);
        layout_price = (RelativeLayout) view.findViewById(R.id.layout_price);
        btn_bottom_price = (TextView) view.findViewById(R.id.btn_bottom_price);
    }

    //为adapter初始化数据
    private void initGridData() {
        PriceOrderBean priceOrderBean;
        for (int i = 0; i < 10; i++) {
            priceOrderBean = new PriceOrderBean();
            priceOrderBean.setNumber(i + "");
            fiveList.add(priceOrderBean);
            fourList.add(priceOrderBean);
            thirdList.add(priceOrderBean);
            twoList.add(priceOrderBean);
            oneList.add(priceOrderBean);
        }
        //万位view
        fiveStartsAdapter = new FiveStartsAdapter(fiveList, getActivity());
        wan_gridview.setAdapter(fiveStartsAdapter);
        //千位
        fourStartsAdapter = new FourStartsAdapter(fourList, getActivity());
        qian_gridview.setAdapter(fourStartsAdapter);
        //百位
        thirdStartsAdapter = new ThirdStartsAdapter(thirdList, getActivity());
        bai_gridview.setAdapter(thirdStartsAdapter);
        //十位
        twoStartsAdapter = new TwoStartsAdapter(twoList, getActivity());
        shi_gridview.setAdapter(twoStartsAdapter);
        //个位
        oneStartsAdapter = new OneStartsAdapter(oneList, getActivity());
        ge_gridview.setAdapter(oneStartsAdapter);
    }

    //初始化点击
    private void initClick() {
        //万位
        btn_wan_quan.setOnClickListener(this);
        btn_wan_da.setOnClickListener(this);
        btn_wan_xiao.setOnClickListener(this);
        btn_wan_ji.setOnClickListener(this);
        btn_wan_ou.setOnClickListener(this);
        btn_wan_clear.setOnClickListener(this);
        //千位
        btn_qian_quan.setOnClickListener(this);
        btn_qian_da.setOnClickListener(this);
        btn_qian_xiao.setOnClickListener(this);
        btn_qian_ji.setOnClickListener(this);
        btn_qian_ou.setOnClickListener(this);
        btn_qian_clear.setOnClickListener(this);
        //百位
        btn_bai_quan.setOnClickListener(this);
        btn_bai_da.setOnClickListener(this);
        btn_bai_xiao.setOnClickListener(this);
        btn_bai_ji.setOnClickListener(this);
        btn_bai_ou.setOnClickListener(this);
        btn_bai_clear.setOnClickListener(this);
        //十位
        btn_shi_quan.setOnClickListener(this);
        btn_shi_da.setOnClickListener(this);
        btn_shi_xiao.setOnClickListener(this);
        btn_shi_ji.setOnClickListener(this);
        btn_shi_ou.setOnClickListener(this);
        btn_shi_clear.setOnClickListener(this);
        //个位
        btn_ge_quan.setOnClickListener(this);
        btn_ge_da.setOnClickListener(this);
        btn_ge_xiao.setOnClickListener(this);
        btn_ge_ji.setOnClickListener(this);
        btn_ge_ou.setOnClickListener(this);
        btn_ge_clear.setOnClickListener(this);
        //加减
        btn_sub.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        layout_price.setOnClickListener(this);

    }

    /**
     * 所有的点击的监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //万位
            case R.id.btn_wan_quan://全
                setChooseView(fiveList, fiveStartsAdapter, BTN_FIVE);
                break;
            case R.id.btn_wan_da://大
                setBigView(fiveList, fiveStartsAdapter, BTN_FIVE);
                break;
            case R.id.btn_wan_xiao://小
                setSmallView(fiveList, fiveStartsAdapter, BTN_FIVE);
                break;
            case R.id.btn_wan_ji://奇
                setOddView(fiveList, fiveStartsAdapter, BTN_FIVE);
                break;
            case R.id.btn_wan_ou://偶
                setEvenView(fiveList, fiveStartsAdapter, BTN_FIVE);
                break;
            case R.id.btn_wan_clear://清
                setCancleView(fiveList, fiveStartsAdapter, BTN_FIVE);
                break;
            //千位
            case R.id.btn_qian_quan://全
                setChooseView(fourList, fourStartsAdapter, BTN_FOUR);
                break;
            case R.id.btn_qian_da://大
                setBigView(fourList, fourStartsAdapter, BTN_FOUR);
                break;
            case R.id.btn_qian_xiao://小
                setSmallView(fourList, fourStartsAdapter, BTN_FOUR);
                break;
            case R.id.btn_qian_ji://奇
                setOddView(fourList, fourStartsAdapter, BTN_FOUR);
                break;
            case R.id.btn_qian_ou://偶
                setEvenView(fourList, fourStartsAdapter, BTN_FOUR);
                break;
            case R.id.btn_qian_clear://清
                setCancleView(fourList, fourStartsAdapter, BTN_FOUR);
                break;
            //百位
            case R.id.btn_bai_quan:
                setChooseView(thirdList, thirdStartsAdapter, BTN_THIRD);
                break;
            case R.id.btn_bai_da:
                setBigView(thirdList, thirdStartsAdapter, BTN_THIRD);
                break;
            case R.id.btn_bai_xiao:
                setSmallView(thirdList, thirdStartsAdapter, BTN_THIRD);
                break;
            case R.id.btn_bai_ji:
                setOddView(thirdList, thirdStartsAdapter, BTN_THIRD);
                break;
            case R.id.btn_bai_ou:
                setEvenView(thirdList, thirdStartsAdapter, BTN_THIRD);
                break;
            case R.id.btn_bai_clear:
                setCancleView(thirdList, thirdStartsAdapter, BTN_THIRD);
                break;
            //十位
            case R.id.btn_shi_quan:
                setChooseView(twoList, twoStartsAdapter, BTN_TWO);
                break;
            case R.id.btn_shi_da:
                setBigView(twoList, twoStartsAdapter, BTN_TWO);
                break;
            case R.id.btn_shi_xiao:
                setSmallView(twoList, twoStartsAdapter, BTN_TWO);
                break;
            case R.id.btn_shi_ji:
                setOddView(twoList, twoStartsAdapter, BTN_TWO);
                break;
            case R.id.btn_shi_ou:
                setEvenView(twoList, twoStartsAdapter, BTN_TWO);
                break;
            case R.id.btn_shi_clear:
                setCancleView(twoList, twoStartsAdapter, BTN_TWO);
                break;
            //个位
            case R.id.btn_ge_quan:
                setChooseView(oneList, oneStartsAdapter, BTN_ONE);
                break;
            case R.id.btn_ge_da:
                setBigView(oneList, oneStartsAdapter, BTN_ONE);
                break;
            case R.id.btn_ge_xiao:
                setSmallView(oneList, oneStartsAdapter, BTN_ONE);
                break;
            case R.id.btn_ge_ji:
                setOddView(oneList, oneStartsAdapter, BTN_ONE);
                break;
            case R.id.btn_ge_ou:
                setEvenView(oneList, oneStartsAdapter, BTN_ONE);
                break;
            case R.id.btn_ge_clear:
                setCancleView(oneList, oneStartsAdapter, BTN_ONE);
                break;
            //加减元
            case R.id.btn_sub:
                count = et_number.getText().toString();//先拿到倍投数量
                int subNum = Integer.parseInt(count);//转类型
                if (subNum > 0) {
                    subNum--;
                }
                et_number.setText(subNum + "");//重新设置倍投数量
                break;
            case R.id.btn_add:
                count = et_number.getText().toString();
                int addNum = Integer.parseInt(count);
                if (addNum >= 0) {
                    addNum++;
                }
                et_number.setText(addNum + "");//设置倍投数量
                break;
            case R.id.layout_price:
                Intent intent = new Intent(getActivity(), DialogPriceActivity.class);
                startActivityForResult(intent, REQUEST);
                break;
        }
    }

    //全部选中状态
    private void setChooseView(ArrayList<PriceOrderBean> list, BaseAdapter adapter, int type) {
        // 遍历gridViewList的长度，将MyAdapter中的map值全部设为true
        for (int i = 0; i < list.size(); i++) {
            isChooseWhichAdapter(i, type);
        }
        //priceOrderActivity.setNotePrice(list,type);
        adapter.notifyDataSetChanged();//刷新适配器
    }

    //全部取消选中
    private void setCancleView(ArrayList<PriceOrderBean> list, BaseAdapter adapter, int type) {
        // 遍历gridViewList的长度，将MyAdapter中的map值全部设为true
        for (int i = 0; i < list.size(); i++) {
            isCancleWhichAdapter(i, type);
        }
        adapter.notifyDataSetChanged();//刷新适配器
    }

    //选大
    private void setBigView(ArrayList<PriceOrderBean> list, BaseAdapter adapter, int type) {
        // 遍历gridViewList的长度，将MyAdapter中的map值全部设为true
        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(list.get(i).getNumber()) > 4) {
                isChooseWhichAdapter(i, type);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(list.get(i).getNumber()) < 5) {
                isCancleWhichAdapter(i, type);
            }
        }
      //  priceOrderActivity.setNotePrice(list,type);
        adapter.notifyDataSetChanged();//刷新适配器
    }

    //选小
    private void setSmallView(ArrayList<PriceOrderBean> list, BaseAdapter adapter, int type) {
        // 遍历gridViewList的长度，将MyAdapter中的map值全部设为true
        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(list.get(i).getNumber()) < 5) {
                isChooseWhichAdapter(i, type);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(list.get(i).getNumber()) > 4) {
                isCancleWhichAdapter(i, type);
            }
        }
      //  priceOrderActivity.setNotePrice(list,type);
        adapter.notifyDataSetChanged();//刷新适配器
    }

    //选奇
    private void setOddView(ArrayList<PriceOrderBean> list, BaseAdapter adapter, int type) {
        // 遍历gridViewList的长度，将MyAdapter中的map值全部设为true
        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(list.get(i).getNumber()) % 2 != 0) {
                isChooseWhichAdapter(i, type);//选中状态
            }
        }
        // 遍历gridViewList的长度，将MyAdapter中的map值全部设为true
        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(list.get(i).getNumber()) % 2 == 0) {
                isCancleWhichAdapter(i, type);//取消
            }
        }
       // priceOrderActivity.setNotePrice(list,type);
        adapter.notifyDataSetChanged();//刷新适配器
    }

    //选偶
    private void setEvenView(ArrayList<PriceOrderBean> list, BaseAdapter adapter, int type) {
        // 遍历gridViewList的长度，将MyAdapter中的map值全部设为true
        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(list.get(i).getNumber()) % 2 == 0) {
                isChooseWhichAdapter(i, type);
            }
        }
        // 遍历gridViewList的长度，将MyAdapter中的map值全部设为true
        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(list.get(i).getNumber()) % 2 != 0) {
                isCancleWhichAdapter(i, type);
            }
        }
       // priceOrderActivity.setNotePrice(list,type);
        adapter.notifyDataSetChanged();//刷新适配器
    }


    /**
     * 选中时判断是哪一个adapter操作
     */
    private void isChooseWhichAdapter(int i, int type) {
        if (type == FiveStartsAdapter.type) {
            FiveStartsAdapter.getIsSelected().put(i, true);
        } else if (type == FourStartsAdapter.type) {
            FourStartsAdapter.getIsSelected().put(i, true);
        } else if (type == ThirdStartsAdapter.type) {
            ThirdStartsAdapter.getIsSelected().put(i, true);
        } else if (type == TwoStartsAdapter.type) {
            TwoStartsAdapter.getIsSelected().put(i, true);
        } else if (type == OneStartsAdapter.type) {
            OneStartsAdapter.getIsSelected().put(i, true);
        }
    }

    /**
     * 需要取消时判断是哪一个adapter
     */
    private void isCancleWhichAdapter(int i, int type) {
        if (type == FiveStartsAdapter.type) {
            FiveStartsAdapter.getIsSelected().put(i, false);
        } else if (type == FourStartsAdapter.type) {
            FourStartsAdapter.getIsSelected().put(i, false);
        } else if (type == ThirdStartsAdapter.type) {
            ThirdStartsAdapter.getIsSelected().put(i, false);
        } else if (type == TwoStartsAdapter.type) {
            TwoStartsAdapter.getIsSelected().put(i, false);
        } else if (type == OneStartsAdapter.type) {
            OneStartsAdapter.getIsSelected().put(i, false);
        }
    }

    //将时间倒计时时间显示在textview中
    public void getTime(long second) {
        if (second < 60) {
            tv_hour.setText("00 : ");
            tv_minute.setText("00 : ");
            tv_second.setText(formatData(second));
        } else if (second < 60 * 60) {
            tv_hour.setText("00 : ");
            tv_minute.setText(formatData(second / 60) + " : ");
            tv_second.setText(formatData(second % 60));
        } else if (second < 60 * 60 * 24) {
            tv_hour.setText(formatData(second / 3600) + " : ");
            tv_minute.setText(formatData(second % 3600 / 60) + " : ");
            tv_second.setText(formatData(second % 3600 % 60));
        }
    }

    //转换为每五分钟进行倒计时，0或5为倒计时终点
    private static long getCountDownTime(long minute, long second) {
        Log.e("time", minute + " : " + second);
        long t = minute % 10;
        long countTime = 0;
        if (t >= 5) {
            countTime = (10 - t) * 60 - second;//休息时间
        }
        else {
            countTime = (5 - t) * 60 - second;
        }
        return countTime;
    }

    //模式化数据，将个位数补零(个位数字小于两位补零)
    public String formatData(long second) {
        String s = Long.toString(second);
        if (second < 10) {
            s = "0" + s;
        }
        return s;
    }

    //获取北京时间
    public void getBeijingTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.bjtime.cn");
                    URLConnection uc = url.openConnection();
                    uc.setConnectTimeout(2000);//2秒超时
                    uc.connect();
                    long ld = uc.getDate();
                    Date date = new Date(ld);
                    hour = date.getHours();
                    minute = date.getMinutes();
                    second = date.getSeconds();
                    System.out.println("北京时间  时："+hour+"，   分："+minute+",   秒："+second);
                    weakHandler.sendEmptyMessage(0);
                } catch (Exception me) {
                    weakHandler.sendEmptyMessage(1);
                }
            }
        }).start();
    }

    //异步操作
    class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            getTime(millisUntilFinished / 1000);
        }
        @Override
        public void onFinish() {
            getTime(0);
            ToastUtil.ToaString(getActivity(), "开奖");
            mc = new MyCountDownTimer(5 * 60 * 1000, 1000);//配置时间（5分钟）
            mc.start();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST && data != null) {
            String content = data.getStringExtra("price");
            if (!content.equals("")) {
                btn_bottom_price.setText(content);
            }
        }
    }
}


