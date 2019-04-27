package com.example.graduationproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.example.graduationproject.util.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class CommActivity extends BaseFragmentActivity {
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setBeforeContentView() {
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void setAfterContentView() {
        unbinder = ButterKnife.bind(this);
    }

    public void showToast(String msg) {
        ToastUtils.showShort(this, msg);
    }

    public void showToast(int msgId) {
        ToastUtils.showShort(this, msgId);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
