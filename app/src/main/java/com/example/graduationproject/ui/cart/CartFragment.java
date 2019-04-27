package com.example.graduationproject.ui.cart;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.graduationproject.R;
import com.example.graduationproject.base.CommFragment;

public class CartFragment extends CommFragment {

    public static CartFragment getCartView(){
        CartFragment cartFragment=new CartFragment();
        return cartFragment;
    }
    @Override
    public int layoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void initView(LayoutInflater inflater, @Nullable ViewGroup container) {

    }

    @Override
    public void initPresenter() {

    }
}
