package com.example.graduationproject.ui.mine;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.graduationproject.R;
import com.example.graduationproject.base.CommFragment;
import com.example.graduationproject.base.Constant;
import com.example.graduationproject.ui.LoginActivity;

import butterknife.BindView;

public class MineFragment extends CommFragment implements MineContract.View {
    @BindView(R.id.head_iv)
    ImageView iv_head;
    @BindView(R.id.name_tv)
    TextView tv_name;
    MinePresenter minePresenter;
    @BindView(R.id.all)
    TextView all;

    public static MineFragment getMineView() {
        MineFragment mineFragment = new MineFragment();
        return mineFragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(final LayoutInflater inflater, @Nullable ViewGroup container) {

        iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Constant.login) {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });

        if (Constant.login) {
            tv_name.setText(Constant.name);
        }

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constant.login) {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), OrderDetail.class);
                    startActivity(intent);
                }else{
                    showToast("请先登录");
                }

            }
        });
    }

    @Override
    public void initPresenter() {
        minePresenter = new MinePresenter(this);

    }
}
