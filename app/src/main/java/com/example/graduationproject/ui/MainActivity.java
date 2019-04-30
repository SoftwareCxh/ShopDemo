package com.example.graduationproject.ui;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.graduationproject.R;
import com.example.graduationproject.base.BaseFragment;
import com.example.graduationproject.base.CommActivity;
import com.example.graduationproject.base.Constant;
import com.example.graduationproject.data.User;
import com.example.graduationproject.ui.cart.CartFragment;
import com.example.graduationproject.ui.index.IndexFragment;
import com.example.graduationproject.ui.mine.MineFragment;
import com.example.graduationproject.util.ApiServiceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends CommActivity {

    BaseFragment indexFragment,cartFragment,mineFragment;
    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        if(Constant.login){
            ApiServiceUtil.getService().get(Constant.username).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user=response.body();
                    Constant.name=user.getName();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
        setFragmentContainerId(R.id.container1);
        indexFragment= IndexFragment.getIndexView();
        cartFragment= CartFragment.getCartView();
        mineFragment= MineFragment.getMineView();
        ((RadioGroup)findViewById(R.id.main_rg)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.main_meiniang_rb) {
                    showFragment(indexFragment);
                } else if (checkedId == R.id.main_discover_rb) {
                    showFragment(cartFragment);
                } else if (checkedId == R.id.main_me_rb) {
                    showFragment(mineFragment);
                }
            }
        });

        ((RadioButton) findViewById(R.id.main_meiniang_rb)).setChecked(true);

        showFragment(indexFragment);
    }

    @Override
    public void initPresenter() {

    }

}
