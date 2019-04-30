package com.example.graduationproject.ui;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduationproject.R;
import com.example.graduationproject.base.CommActivity;
import com.example.graduationproject.util.ApiServiceUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends CommActivity {
    @BindView(R.id.to_login)
    LinearLayout ll_toLogin;
    @BindView(R.id.username)
    EditText et_username;
    @BindView(R.id.password)
    EditText et_password;
    @BindView(R.id.name)
    EditText et_name;
    @BindView(R.id.telephone)
    EditText et_telephone;
    @BindView(R.id.register)
    TextView tv_register;

    @BindView(R.id.username_root)
    TextInputLayout usernameInputLayout;
    @BindView(R.id.password_root)
    TextInputLayout passwordInputLayout;
    @BindView(R.id.name_root)
    TextInputLayout nameInputLayout;
    @BindView(R.id.telephone_root)
    TextInputLayout telephoneInputLayout;



    @Override
    public int layoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        et_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()==0){
                    usernameInputLayout.setError("账号不能为空");
                    usernameInputLayout.setErrorEnabled(true);
                }else{
                    usernameInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()==0){
                    passwordInputLayout.setError("密码不能为空");
                    passwordInputLayout.setErrorEnabled(true);
                }else {
                    passwordInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()==0){
                    nameInputLayout.setError("用户名不能为空");
                    nameInputLayout.setErrorEnabled(true);
                }else{
                    nameInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_telephone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()==11){
                    telephoneInputLayout.setErrorEnabled(false);
                }else{
                    telephoneInputLayout.setError("手机号格式错误");
                    telephoneInputLayout.setErrorEnabled(true);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @OnClick({R.id.to_login,R.id.register})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.to_login:
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.register:
                if(telephoneInputLayout.isErrorEnabled()||
                        nameInputLayout.isErrorEnabled()||
                        usernameInputLayout.isErrorEnabled()||
                        passwordInputLayout.isErrorEnabled()){
                    Toast.makeText(RegisterActivity.this,"请完善个人信息",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    String username=et_username.getText().toString();
                    String password=et_password.getText().toString();
                    String name=et_name.getText().toString();
                    String telephone=et_telephone.getText().toString();
                    /**
                     * 发起注册的网络请求
                     */
                    ApiServiceUtil.getService().register(username,password,name,telephone).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String s=response.body().string();
                                if("0".equals(s)){
                                    showToast("注册失败，用户名已存在。");
                                }else if("1".equals(s)){
                                    showToast("注册成功，即将前往登录。");
                                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
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
                break;

        }
    }


    @Override
    public void initPresenter() {

    }
}
