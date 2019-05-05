package com.example.graduationproject.ui.index;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.graduationproject.R;
import com.example.graduationproject.data.GoodsInfo;
import com.example.graduationproject.ui.cart.CartListAdapter;
import com.example.graduationproject.util.ApiServiceUtil;
import com.example.graduationproject.util.ImageHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends BaseAdapter{
    Context context;
    List<GoodsInfo> list;

    public ListAdapter(Context context, List<GoodsInfo> list){
        this.context=context;
        this.list=list;
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
            convertView=View.inflate(context,R.layout.list_item_goods,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        final GoodsInfo goodsInfo=list.get(position);
        viewHolder.goodsName.setText(goodsInfo.getGoodsName());
        ImageHelper.showRoundedImage(context,viewHolder.goodsImage, ApiServiceUtil.BaseUrl+goodsInfo.getGoodsImage());
        Log.d("Activity",ApiServiceUtil.BaseUrl+goodsInfo.getGoodsImage());
        viewHolder.goodsPrice.setText(goodsInfo.getGoodsPrice());
        viewHolder.resume.setText(goodsInfo.getResume());
        return convertView;
    }


    public class ViewHolder{

        @BindView(R.id.image)
        ImageView goodsImage;
        @BindView(R.id.name)
        TextView goodsName;
        @BindView(R.id.price)
        TextView goodsPrice;
        @BindView(R.id.resume)
        TextView resume;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
