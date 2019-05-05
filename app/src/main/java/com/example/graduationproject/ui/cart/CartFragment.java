package com.example.graduationproject.ui.cart;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graduationproject.R;
import com.example.graduationproject.base.CommFragment;
import com.example.graduationproject.base.Constant;

import butterknife.BindView;
import butterknife.OnClick;

public class CartFragment extends CommFragment implements CartContract.View {
    @BindView(R.id.actionBar_edit)
    TextView tv_edit;
    @BindView(R.id.all_checkBox)
    CheckBox cb_all;
    @BindView(R.id.total_price)
    TextView tv_totlaPrice;
    @BindView(R.id.go_pay)
    TextView tv_goPay;
    @BindView(R.id.order_info)
    LinearLayout ll_orderInfo;
    @BindView(R.id.share_info)
    LinearLayout ll_shareInfo;
    CartPresenter cartPresenter;

    //false就是编辑，ture就是完成
    private boolean flag = false;

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
        cartPresenter=new CartPresenter(this);
        cartPresenter.getData(Constant.username);
    }

    @OnClick({R.id.actionBar_edit})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.actionBar_edit:
                flag = !flag;
                setVisiable();
        }
    }

    private void setVisiable() {
        if (flag) {
            ll_orderInfo.setVisibility(View.GONE);
            ll_shareInfo.setVisibility(View.VISIBLE);
            tv_edit.setText(R.string.finish);
        } else {
            ll_orderInfo.setVisibility(View.VISIBLE);
            ll_shareInfo.setVisibility(View.GONE);
            tv_edit.setText(R.string.edit);
        }
    }
}
