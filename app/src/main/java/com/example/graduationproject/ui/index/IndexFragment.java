package com.example.graduationproject.ui.index;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.graduationproject.R;
import com.example.graduationproject.base.CommFragment;
import com.example.graduationproject.data.GoodsInfo;
import com.example.graduationproject.util.ApiServiceUtil;
import com.example.graduationproject.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class IndexFragment extends CommFragment implements IndexContract.View{

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.goods_list)
    GridView listView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    GoodsPop goodsPop;
    List<GoodsInfo> list;
    List<GoodsInfo> bannerList;
    List<String> image;
    ListAdapter listAdapter;

    IndexPresenter indexPresenter;
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

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initPresenter();
            }
        });
    }

    @Override
    public void setData(List<GoodsInfo> list) {
        this.list=list;
        swipeRefreshLayout.setRefreshing(false);
        listAdapter = new ListAdapter(getContext(), list);
        listView.setAdapter(listAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    boolean enable = false;
                    if(listView != null && listView.getChildCount() > 0){
                        // check if the first item of the list is visible
                        boolean firstItemVisible = listView.getFirstVisiblePosition() == 0;
                        // check if the top of the first item is visible
                        boolean topOfFirstItemVisible = listView.getChildAt(0).getTop() == 0;
                        // enabling or disabling the refresh layout
                        enable = firstItemVisible && topOfFirstItemVisible;
                    }
                    swipeRefreshLayout.setEnabled(enable);

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goodsPop=new GoodsPop(getContext(),(GoodsInfo)parent.getItemAtPosition(position));
                goodsPop.show();

            }
        });

    }

    @Override
    public void setBanner(List<GoodsInfo> body) {
        bannerList=body;
        image=new ArrayList<>();
        for(GoodsInfo goodsInfo:bannerList){
            image.add(ApiServiceUtil.BaseUrl+goodsInfo.getGoodsImage());
        }
        startBanner();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                goodsPop=new GoodsPop(getContext(),bannerList.get(i));
                goodsPop.show();
            }
        });
    }

    @Override
    public void initPresenter() {
        indexPresenter =new IndexPresenter(this);
        indexPresenter.getData();

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
