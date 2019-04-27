package com.example.graduationproject.ui;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.graduationproject.R;
import com.example.graduationproject.base.BaseFragment;
import com.example.graduationproject.base.CommActivity;

public class MainActivity extends CommActivity {

    BaseFragment indexFragment,cartFragment,mineFragment;
    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setFragmentContainerId(R.id.container1);
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
