package com.example.graduationproject.ui.cart;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.graduationproject.R;
import com.example.graduationproject.base.CommFragment;
import com.example.graduationproject.base.Constant;
import com.example.graduationproject.data.CartGoods;
import com.example.graduationproject.data.GoodsInfo;
import com.example.graduationproject.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CartFragment extends CommFragment implements CartContract.View, CartListAdapter.CheckInterface, CartListAdapter.ModifyCountInterface {
    @BindView(R.id.actionBar_edit)
    TextView tv_edit;
    @BindView(R.id.all_checkBox)
    CheckBox cb_all;
    @BindView(R.id.total_price)
    TextView tv_totalPrice;
    @BindView(R.id.go_pay)
    TextView tv_goPay;
    @BindView(R.id.order_info)
    LinearLayout ll_orderInfo;
    @BindView(R.id.share_info)
    LinearLayout ll_shareInfo;
    @BindView(R.id.goods_list)
    ListView listView;
    @BindView(R.id.del_goods)
    TextView del_goods;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    CartPresenter cartPresenter;
    CartListAdapter cartListAdapter;
    List<GoodsInfo> goods;
    //false就是编辑，ture就是完成
    private boolean flag = false;

    private double mtotalPrice = 0.00;
    private int mtotalCount = 0;

    public static CartFragment getCartView() {
        CartFragment cartFragment = new CartFragment();
        return cartFragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        cb_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCheckAll();
            }
        });
        del_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDelete();
                calulate();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initPresenter();
            }
        });
    }

    private void doBuy() {
        boolean flag=false;
        for (int j = 0; j <goods.size() ; j++) {
            if (goods.get(j).isChoosed()) {
                GoodsInfo goodsInfo=goods.get(j);
                cartPresenter.buy(goodsInfo.getGoodsNum(),goodsInfo.getGoodsName());
                flag=true;
            }
        }
        if(flag){
            showToast("支付成功");
            doDelete();
        }else{
            showToast("请选择至少一样商品");
        }

    }

    @Override
    public void initPresenter() {
        cartPresenter = new CartPresenter(this);
        if (Constant.username == null || "".equals(Constant.username)) {
            showToast("请先登录");
        } else {
            cartPresenter.getData(Constant.username);
        }

    }

    @OnClick({R.id.actionBar_edit,R.id.go_pay})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionBar_edit:
                flag = !flag;
                setVisiable();
                break;
            case R.id.go_pay:
                showBuyDialog();
                break;
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

    @Override
    public void setData(List<GoodsInfo> body) {
        swipeRefreshLayout.setRefreshing(false);
        goods = body;
        cartListAdapter = new CartListAdapter(body, getContext());
        cartListAdapter.setModifyCountInterface(this);
        cartListAdapter.setCheckInterface(this);
        listView.setAdapter(cartListAdapter);
    }

    private void calulate() {
        mtotalPrice = 0.00;
        mtotalCount = 0;
        for (int i = 0; i < goods.size(); i++) {
            GoodsInfo good = goods.get(i);
            if (good.isChoosed()) {
                mtotalCount++;
                mtotalPrice += good.getGoodsPrice() * good.getGoodsNum();
            }
        }
        tv_totalPrice.setText("￥" + mtotalPrice + "");
        tv_goPay.setText("去支付(" + mtotalCount + ")");
    }

    /**
     * 全选和反选
     * 错误标记：在这里出现过错误
     */
    private void doCheckAll() {
        for (int i = 0; i < goods.size(); i++) {
            goods.get(i).setChoosed(cb_all.isChecked());//这里出现过错误
        }
        cartListAdapter.notifyDataSetChanged();
        calulate();
    }

    private void doDelete() {
        List<GoodsInfo> toBeDeleteChilds = new ArrayList<>(); //待删除的组元素

        for (int j = 0; j < goods.size(); j++) {
            if (goods.get(j).isChoosed()) {
                toBeDeleteChilds.add(goods.get(j));
                cartPresenter.delete(goods.get(j).getCartId());
            }
        }
        goods.removeAll(toBeDeleteChilds);
        //重新设置购物车
        cartListAdapter.notifyDataSetChanged();

    }

    @Override
    public void doIncrease(int childPosition, View showCountView, boolean isChecked) {
        GoodsInfo goodsInfo = (GoodsInfo) cartListAdapter.getItem(childPosition);
        int count = goodsInfo.getGoodsNum();
        count++;
        goodsInfo.setGoodsNum(count);
        ((TextView) showCountView).setText(String.valueOf(count));
        cartListAdapter.notifyDataSetChanged();
        calulate();
    }

    @Override
    public void doDecrease(int childPosition, View showCountView, boolean isChecked) {
        GoodsInfo goodsInfo = (GoodsInfo) cartListAdapter.getItem(childPosition);
        int count = goodsInfo.getGoodsNum();
        if (count == 1) {
            return;
        }
        count--;
        goodsInfo.setGoodsNum(count);
        ((TextView) showCountView).setText("" + count);
        cartListAdapter.notifyDataSetChanged();
        calulate();
    }

    @Override
    public void doUpdate(int childPosition, View showCountView, boolean isChecked) {
        GoodsInfo goodsInfo = (GoodsInfo) cartListAdapter.getItem(childPosition);
        int count = goodsInfo.getGoodsNum();
        ((TextView) showCountView).setText(String.valueOf(count));
        cartListAdapter.notifyDataSetChanged();
        calulate();
    }

    @Override
    public void checkChild(int childPosition, boolean isChecked) {
        calulate();
    }

    public void showBuyDialog(){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(getContext());
        dialog.setTitle("提示");
        dialog.setMessage("确认支付?");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doBuy();
                calulate();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
