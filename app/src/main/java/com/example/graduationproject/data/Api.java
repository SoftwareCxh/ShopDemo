package com.example.graduationproject.data;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

public interface Api {

    @POST("")
    Call<ResponseBody> login();

}
