package com.example.graduationproject.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class CommFragment extends BaseFragment {
    private CommActivity mCommActivity;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCommActivity=(CommActivity) getActivity();
    }

    @Override
    public void setAfterContentView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroy() {
        if (unbinder != null)
            unbinder.unbind();
        super.onDestroy();

    }


    public void showToast(String msg) {
        mCommActivity.showToast(msg);
    }

    public void showToast(int msgId) {
        mCommActivity.showToast(msgId);
    }

}
