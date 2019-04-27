package com.example.graduationproject.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author 作者:daniu
 * @date 创建时间:2016/1/7 10:30
 * @Description:(这里用一句话描述这个类的作用)
 **/
public class ToastUtils {
    private static Toast mToast;

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void show(Context context, CharSequence message, int duration) {
        if(mToast == null) {
            mToast = Toast.makeText(context, message, duration);
            mToast.setGravity(Gravity.CENTER,0,0);
        } else {
            mToast.setText(message);
            mToast.setDuration(duration);
        }

        mToast.show();
    }

    public static void show(Context context, int messageId, int duration) {
        if(mToast == null) {
            mToast = Toast.makeText(context, messageId, duration);
            mToast.setGravity(Gravity.CENTER,0,0);
        } else {
            mToast.setText(messageId);
            mToast.setDuration(duration);
        }

        mToast.show();
    }

    public static void showShort(Context context, CharSequence message) {
        show(context, message, Toast.LENGTH_SHORT);
    }

    public static void showShort(Context context, String messageId) {
        show(context, messageId, Toast.LENGTH_SHORT);
    }

    public static void showShort(Context context, int messageId) {
        show(context, messageId, Toast.LENGTH_SHORT);
    }

    public static void showLong(Context context, CharSequence message) {
        show(context, message, Toast.LENGTH_LONG);
    }

    public static void showLong(Context context, int messageId) {
        show(context, messageId, Toast.LENGTH_LONG);
    }


}

