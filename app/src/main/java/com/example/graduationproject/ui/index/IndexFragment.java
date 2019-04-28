package com.example.graduationproject.ui.index;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.graduationproject.R;
import com.example.graduationproject.base.CommFragment;
import com.example.graduationproject.data.GoodsInfo;
import com.example.graduationproject.util.ApiServiceUtil;
import com.example.graduationproject.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

import butterknife.BindView;

public class IndexFragment extends CommFragment {

    @BindView(R.id.banner)
    Banner banner;

    List<GoodsInfo> list;
    List<String> image;
    public static IndexFragment getIndexView() {
        IndexFragment indexFragment = new IndexFragment();
        return indexFragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView(LayoutInflater inflater, @Nullable ViewGroup container) {
       // startBanner();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {

            }
        });

    }

    @Override
    public void initPresenter() {

    }


    private void startBanner() {
        //设置banner样式(显示圆形指示器)
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置（指示器居右）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(image);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
}
