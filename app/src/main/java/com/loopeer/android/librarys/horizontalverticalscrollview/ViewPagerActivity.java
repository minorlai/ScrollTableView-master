package com.loopeer.android.librarys.horizontalverticalscrollview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopeer.android.librarys.horizontalverticalscrollview.bean.ADInfo;
import com.loopeer.android.librarys.horizontalverticalscrollview.utils.ViewFactory;
import com.loopeer.android.librarys.horizontalverticalscrollview.view.CycleViewPager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;

/**
 * Created by laiyingtang on 2016/8/24.
 */
public class ViewPagerActivity extends Activity {
    private ArrayList<ImageView> views = new ArrayList<ImageView>();
    private ArrayList<ADInfo> infos=new ArrayList<ADInfo>();
    private CycleViewPager cycleViewPager;
    private String[] url = {"http://img4.imgtn.bdimg.com/it/u=2430963138,1300578556&fm=23&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2755648979,3568014048&fm=23&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2272739960,4287902102&fm=23&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=1078051055,1310741362&fm=23&gp=0.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_banner);
        configImageLoader();
        initBanner();
    }

    private void initBanner(){
        cycleViewPager=(CycleViewPager) getFragmentManager().findFragmentById(R.id.fragment);

        for(int i=0;i<url.length;i++){
            ADInfo info=new ADInfo();
            info.setUrl(url[i]);
            info.setContent("图片"+i);
            infos.add(info);
        }
        //把最后一张图片添加到views中
        views.add(ViewFactory.getImageView(this,infos.get(infos.size()-1).getUrl()));
        for(int i=0;i<infos.size();i++){
            //获取所有图片的url
            views.add(ViewFactory.getImageView(this,infos.get(i).getUrl()));
        }
        views.add(ViewFactory.getImageView(this,infos.get(0).getUrl()));//获取第一张图片的url

        cycleViewPager.setCycle(true);//设置循环播放,在setData前调用

        cycleViewPager.setData(views,infos,imageCycleViewListener);//设置数据，并监听图片点击

        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认2s
        cycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    private CycleViewPager.ImageCycleViewListener imageCycleViewListener=new CycleViewPager.ImageCycleViewListener() {
        @Override
        public void onImageClick(ADInfo info, int postion, View imageView) {
           if(cycleViewPager.isCycle()){
               ADInfo adInfo=infos.get(postion-1);
               Toast.makeText(ViewPagerActivity.this,"点击图片:"+(postion-1)+"-->"+adInfo.getContent(),Toast.LENGTH_SHORT).show();
               startActivity(new Intent(ViewPagerActivity.this,ActionBarAct.class));
           }
        }
    };
    /**
     * 配置图片加载
     */
    private void configImageLoader(){
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
}
