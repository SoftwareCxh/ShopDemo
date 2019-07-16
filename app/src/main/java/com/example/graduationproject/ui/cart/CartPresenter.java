package com.example.graduationproject.ui.cart;

import com.example.graduationproject.base.Constant;
import com.example.graduationproject.data.Api;
import com.example.graduationproject.data.CartGoods;
import com.example.graduationproject.data.GoodsInfo;
import com.example.graduationproject.util.ApiServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartPresenter implements CartContract.Presenter {
    CartContract.View view;

    public CartPresenter(CartContract.View view){
        this.view=view;
    }


    public void getData(String username){
        ApiServiceUtil.getService().getCartGoods(username).enqueue(new Callback<List<GoodsInfo>>() {
            @Override
            public void onResponse(Call<List<GoodsInfo>> call, Response<List<GoodsInfo>> response) {
                view.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<GoodsInfo>> call, Throwable t) {

            }
        });
    }

    public void delete(int id) {
        ApiServiceUtil.getService().deletCartGoods(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showToast("删除失败");
            }
        });
    }

    public void buy(int goodsNum,String name) {
        ApiServiceUtil.getService().addOrder(Constant.username,name,goodsNum).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showToast("购买失败");
            }
        });
    }
}
