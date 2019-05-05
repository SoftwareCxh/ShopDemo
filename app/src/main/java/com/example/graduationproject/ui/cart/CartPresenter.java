package com.example.graduationproject.ui.cart;

import com.example.graduationproject.data.CartGoods;
import com.example.graduationproject.util.ApiServiceUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartPresenter implements CartContract.Presenter {
    CartContract.View view;

    public CartPresenter(CartContract.View view){
        this.view=view;
    }


    public void getData(String username){
        ApiServiceUtil.getService().getCartGoods(username).enqueue(new Callback<List<CartGoods>>() {
            @Override
            public void onResponse(Call<List<CartGoods>> call, Response<List<CartGoods>> response) {

            }

            @Override
            public void onFailure(Call<List<CartGoods>> call, Throwable t) {

            }
        });
    }
}
