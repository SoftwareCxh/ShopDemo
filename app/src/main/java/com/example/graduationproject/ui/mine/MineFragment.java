package com.example.graduationproject.ui.mine;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.graduationproject.R;
import com.example.graduationproject.base.CommFragment;

public class MineFragment extends CommFragment {
    public static MineFragment getMine(){
        MineFragment mineFragment=new MineFragment();
        return mineFragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(LayoutInflater inflater, @Nullable ViewGroup container) {

    }

    @Override
    public void initPresenter() {

    }
}
