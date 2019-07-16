package com.example.graduationproject.data;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @POST("/register")
    Call<ResponseBody> register(@Query("username")String username,@Query("password")String password,@Query("name")String name,@Query("telephone")String telephone);

    @POST("/login")
    Call<ResponseBody> login(@Query("username")String username,@Query("password")String password);

    @POST("/get")
    Call<User> get(@Query("username")String username);

    @GET("/getGoodsList")
    Call<List<GoodsInfo>> getGoodsList();

    @GET("/getBannerGoods")
    Call<List<GoodsInfo>> getBanner();


    @GET("/getCart")
    Call<List<GoodsInfo>> getCartGoods(@Query("username")String username);

    @POST("/deleteCartGoods")
    Call<ResponseBody> deletCartGoods(@Query("id")int id);

    @POST("/addCartGoods")
    Call<ResponseBody> addCartGoods(@Query("username")String username,@Query("name")String name);

    @GET("/getGoods")
    Call<GoodsInfo> getGoods(@Query("name")String name);



    //未测
    @POST("/addOrder")
    Call<ResponseBody> addOrder(@Query("username")String username,@Query("name")String name,@Query("num")int num);

    @POST("/getOrderList")
    Call<List<OrderInfo>> getOrderList(@Query("username")String username);

}
