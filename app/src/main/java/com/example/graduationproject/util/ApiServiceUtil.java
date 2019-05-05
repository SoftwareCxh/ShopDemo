package com.example.graduationproject.util;

import com.example.graduationproject.data.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceUtil {
    private static Retrofit retrofit;
    public static String BaseUrl="http://192.168.1.100:8080";
    private static Api api;

    public static Api getService(){
        if(api==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api= retrofit.create(Api.class);
        }
        return api;
    }
}
