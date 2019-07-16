package com.example.graduationproject.ui.cart;

import com.example.graduationproject.base.BasePresenter;
import com.example.graduationproject.base.CommView;
import com.example.graduationproject.data.CartGoods;
import com.example.graduationproject.data.GoodsInfo;

import java.util.List;

public interface CartContract {
    interface  Presenter extends BasePresenter{

    }

    interface  View extends CommView<Presenter>{

        void setData(List<GoodsInfo> body);
    }
}
