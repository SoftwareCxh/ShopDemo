package com.example.graduationproject.ui;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.graduationproject.R;
import com.example.graduationproject.base.CommActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends CommActivity {
    @BindView(R.id.to_login)
    LinearLayout ll_toLogin;

    @Override
    public int layoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {

    }


    @OnClick({R.id.to_login})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.to_login:
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }


    @Override
    public void initPresenter() {

    }
}
