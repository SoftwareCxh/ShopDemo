package com.example.graduationproject.ui.index;

import com.example.graduationproject.data.GoodsInfo;
import com.example.graduationproject.util.ApiServiceUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndexPresenter implements IndexContract.Presenter {
    private  IndexContract.View view;

    public IndexPresenter(IndexContract.View view){
        this.view=view;

    }

    public void getData(){
        ApiServiceUtil.getService().getGoodsList().enqueue(new Callback<List<GoodsInfo>>() {
            @Override
            public void onResponse(Call<List<GoodsInfo>> call, Response<List<GoodsInfo>> response) {
                view.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<GoodsInfo>> call, Throwable t) {
                view.showToast("网络请求失败");
            }
        });
        ApiServiceUtil.getService().getBanner().enqueue(new Callback<List<GoodsInfo>>() {
            @Override
            public void onResponse(Call<List<GoodsInfo>> call, Response<List<GoodsInfo>> response) {
                view.setBanner(response.body());
            }

            @Override
            public void onFailure(Call<List<GoodsInfo>> call, Throwable t) {
                view.showToast("网络请求失败");
            }
        });
    }
}
