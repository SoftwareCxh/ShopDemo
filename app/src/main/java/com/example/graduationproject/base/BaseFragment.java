package com.example.graduationproject.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int layoutId();

    /**
     * 初始化组件
     */
    protected abstract void initView(LayoutInflater inflater, @Nullable ViewGroup container);

    /**
     * 初始化Presenter
     */
    public abstract void initPresenter();

    /**
     * 获取布局后
     *
     * @param view
     */
    public abstract void setAfterContentView(View view);

    protected View rootV;

    private BaseFragmentActivity mBaseFragmentActivity;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootV == null)
            rootV = inflater.inflate(layoutId(), container, false);
        setAfterContentView(rootV);
        initPresenter();
        initView(inflater,container);
        return rootV;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBaseFragmentActivity = (BaseFragmentActivity) context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
