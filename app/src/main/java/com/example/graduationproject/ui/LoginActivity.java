package com.example.graduationproject.ui;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graduationproject.R;
import com.example.graduationproject.base.CommActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends CommActivity {
    @BindView(R.id.to_register)
    LinearLayout ll_toRegister;


    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.to_register})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.to_register:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }

    @Override
    public void initPresenter() {

    }
}
