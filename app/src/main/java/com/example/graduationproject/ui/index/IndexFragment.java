package com.example.graduationproject.ui.index;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.graduationproject.R;
import com.example.graduationproject.base.CommFragment;

public class IndexFragment extends CommFragment {

    public static IndexFragment getIndexView(){
        IndexFragment indexFragment=new IndexFragment();
        return indexFragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView(LayoutInflater inflater, @Nullable ViewGroup container) {

    }

    @Override
    public void initPresenter() {

    }
}
