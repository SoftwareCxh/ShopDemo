package com.example.graduationproject.ui.mine;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.example.graduationproject.R;
import com.example.graduationproject.base.CommActivity;
import com.example.graduationproject.base.Constant;
import com.example.graduationproject.data.OrderInfo;
import com.example.graduationproject.util.ApiServiceUtil;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetail extends CommActivity {
    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.listView)
    ListView listView;
    OrderListAdapter adapter;
    @Override
    public int layoutId() {
        return R.layout.activity_order_list;
    }

    @Override
    public void initView() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initPresenter();
            }
        });
    }

    @Override
    public void initPresenter() {
        ApiServiceUtil.getService().getOrderList(Constant.username).enqueue(new Callback<List<OrderInfo>>() {
            @Override
            public void onResponse(Call<List<OrderInfo>> call, Response<List<OrderInfo>> response) {
                setData(response.body());
            }

            @Override
            public void onFailure(Call<List<OrderInfo>> call, Throwable t) {

            }
        });
    }

    private void setData(List<OrderInfo> body) {
        refreshLayout.setRefreshing(false);
        adapter=new OrderListAdapter(this,body);
        listView.setAdapter(adapter);
    }
}
