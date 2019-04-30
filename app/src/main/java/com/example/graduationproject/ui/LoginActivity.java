package com.example.graduationproject.ui;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graduationproject.R;
import com.example.graduationproject.base.CommActivity;
import com.example.graduationproject.base.Constant;
import com.example.graduationproject.util.ApiServiceUtil;
import com.example.graduationproject.util.ToastUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends CommActivity {
    @BindView(R.id.to_register)
    LinearLayout ll_toRegister;
    @BindView(R.id.username)
    EditText et_username;
    @BindView(R.id.password)
    EditText et_password;
    @BindView(R.id.login)
    TextView tv_login;


    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.to_register,R.id.login})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.to_register:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login:
                final String username=et_username.getText().toString().trim();
                String password=et_password.getText().toString().trim();
                if("".equals(username)||"".equals(password)){
                    ToastUtils.showShort(getBaseContext(),"用户名或密码不能为空");
                }else{
                    /**
                     * 发起网络请求
                     */
                    ApiServiceUtil.getService().login(username,password).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String s=response.body().string();
                                if("0".equals(s)){
                                    showToast("用户名或密码错误");
                                }else if("1".equals(s)){
                                    showToast("登录成功");
                                    Constant.loginSuc(username);
                                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }

        }

    }

    @Override
    public void initPresenter() {

    }
}
