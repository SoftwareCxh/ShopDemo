package com.example.graduationproject.ui.cart;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.graduationproject.R;
import com.example.graduationproject.base.Constant;
import com.example.graduationproject.data.GoodsInfo;
import com.example.graduationproject.util.ApiServiceUtil;
import com.example.graduationproject.util.ImageHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartListAdapter extends BaseAdapter {
    List<GoodsInfo> list;
    Context context;
    public CartListAdapter(List<GoodsInfo> list, Context context){
        this.list=list;
        this.context=context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.list_item_cart_goods,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        final GoodsInfo goodsInfo=list.get(position);
        viewHolder.goodsName.setText(goodsInfo.getGoodsName());
        ImageHelper.showRoundedImage(context,viewHolder.goodsImage, ApiServiceUtil.BaseUrl+goodsInfo.getGoodsImage());
        viewHolder.goodsNum.setText(String.valueOf(goodsInfo.getGoodsNum()));
        viewHolder.goodsPrice.setText("¥"+goodsInfo.getGoodsPrice());
        viewHolder.singleCheckBox.setChecked(goodsInfo.isChoosed());
        viewHolder.singleCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsInfo.setChoosed(((CheckBox) v).isChecked());
                viewHolder.singleCheckBox.setChecked(((CheckBox) v).isChecked());
                checkInterface.checkChild(position, ((CheckBox) v).isChecked());
            }
        });

        viewHolder.reduceGoodsNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doDecrease(position, viewHolder.goodsNum, viewHolder.singleCheckBox.isChecked());

            }
        });
        viewHolder.increaseGoodsNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doIncrease(position, viewHolder.goodsNum, viewHolder.singleCheckBox.isChecked());

            }
        });
        return convertView;
    }

    public class ViewHolder{
        @BindView(R.id.single_checkBox)
        CheckBox singleCheckBox;
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.goods_price)
        TextView goodsPrice;
        @BindView(R.id.reduce_goodsNum)
        TextView reduceGoodsNum;
        @BindView(R.id.goods_Num)
        TextView goodsNum;
        @BindView(R.id.increase_goods_Num)
        TextView increaseGoodsNum;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param childPosition 子元素的位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int childPosition, View showCountView, boolean isChecked);

        void doDecrease(int childPosition, View showCountView, boolean isChecked);

        void doUpdate(int childPosition, View showCountView, boolean isChecked);

        /**
         * 删除子Item
         *
         * @param groupPosition
         * @param childPosition
         */
    }
    public interface CheckInterface {
        /**
         * 子选框状态改变触发的事件
         *
         * @param childPosition 子元素的位置
         * @param isChecked     子元素的选中与否
         */
        void checkChild( int childPosition, boolean isChecked);
    }

    private ModifyCountInterface modifyCountInterface;
    private CheckInterface checkInterface;
    public ModifyCountInterface getModifyCountInterface() {
        return modifyCountInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    public CheckInterface getCheckInterface() {
        return checkInterface;
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }
}
