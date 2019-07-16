package com.example.graduationproject.ui.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.graduationproject.R;
import com.example.graduationproject.base.Constant;
import com.example.graduationproject.data.OrderInfo;
import com.example.graduationproject.ui.cart.CartListAdapter;
import com.example.graduationproject.util.ApiServiceUtil;
import com.example.graduationproject.util.ImageHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListAdapter extends BaseAdapter {
    List<OrderInfo> list;
    Context context;
    public OrderListAdapter(Context orderDetail, List<OrderInfo> body) {
        context=orderDetail;
        list=body;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.list_item_order,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        OrderInfo orderInfo=list.get(position);
        viewHolder.goodsName.setText(orderInfo.getName());
        viewHolder.goodsPrice.setText("¥"+orderInfo.getPrice());
        viewHolder.num.setText("x"+orderInfo.getNum());
        viewHolder.state.setText(orderInfo.getState());
        ImageHelper.showRoundedImage(context, viewHolder.goodsImage,ApiServiceUtil.BaseUrl+orderInfo.getImage());
        return convertView;
    }

    public class ViewHolder{
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.goods_price)
        TextView goodsPrice;
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.state)
        TextView state;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
