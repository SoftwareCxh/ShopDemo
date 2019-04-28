package com.example.graduationproject.ui.mine;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.graduationproject.R;
import com.example.graduationproject.base.CommFragment;
import com.example.graduationproject.ui.LoginActivity;
import com.youth.banner.loader.ImageLoader;

import butterknife.BindView;

public class MineFragment extends CommFragment {
    @BindView(R.id.head_iv)
    ImageView iv_head;

    public static MineFragment getMineView(){
        MineFragment mineFragment=new MineFragment();
        return mineFragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initPresenter() {

    }
}
