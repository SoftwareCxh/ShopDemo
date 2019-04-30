package com.example.graduationproject.data;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @POST("register")
    Call<ResponseBody> register(@Query("username")String username,@Query("password")String password,@Query("name")String name,@Query("telephone")String telephone);

    @POST("login")
    Call<ResponseBody> login(@Query("username")String username,@Query("password")String password);

    @POST("get")
    Call<User> get(@Query("username")String username);

}
