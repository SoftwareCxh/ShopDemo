package com.example.graduationproject.ui.index;

import com.example.graduationproject.base.BasePresenter;
import com.example.graduationproject.base.CommView;
import com.example.graduationproject.data.GoodsInfo;

import java.util.List;

public interface IndexContract  {

    interface  View extends CommView<Presenter>{

        void setData(List<GoodsInfo> body);
    }


    interface Presenter extends BasePresenter{

    }
}
