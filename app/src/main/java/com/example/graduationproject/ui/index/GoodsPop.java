package com.example.graduationproject.ui.index;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.graduationproject.R;
import com.example.graduationproject.base.Constant;
import com.example.graduationproject.data.GoodsInfo;
import com.example.graduationproject.util.ApiServiceUtil;
import com.example.graduationproject.util.ImageHelper;
import com.example.graduationproject.util.ToastUtils;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoodsPop {
    private View view;
    private Context mActivity;
    private Dialog dialog;
    GoodsInfo goodsInfo;

    public GoodsPop(Context activity, GoodsInfo goodsInfo){
        mActivity=activity;
        this.goodsInfo=goodsInfo;
        init();
    }

    private void init() {
        view= LayoutInflater.from(mActivity).inflate(R.layout.pop_goods,null);
        dialog=new Dialog(mActivity,R.style.AskDialog);

        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);//点击屏幕外面dialog会消失
        Window dialogWindow = dialog.getWindow();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // lp.y = 20;//设置Dialog距离底部的距离
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height=ViewGroup.LayoutParams.MATCH_PARENT;
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        ImageView image=view.findViewById(R.id.image);
        TextView name=view.findViewById(R.id.name);
        TextView price=view.findViewById(R.id.price);
        TextView resume=view.findViewById(R.id.resume);
        final TextView add=view.findViewById(R.id.add);
        ImageHelper.showRoundedImage(mActivity,image, ApiServiceUtil.BaseUrl+goodsInfo.getGoodsImage());
        name.setText(goodsInfo.getGoodsName());
        price.setText("¥"+goodsInfo.getGoodsPrice());
        resume.setText(goodsInfo.getResume());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Constant.username==null){
                    ToastUtils.showShort(mActivity,"请先登录");
                    return;
                }
                ApiServiceUtil.getService().addCartGoods(Constant.username,goodsInfo.getGoodsName()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String s=response.body().string();
                            if("1".equals(s)){
                                ToastUtils.showShort(mActivity,"添加成功");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

    }

    public void show(){
        dialog.show();

    }
}
