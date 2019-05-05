package com.example.graduationproject.base;

public interface CommView<P> extends BaseView<P> {


    void showToast(String msg);

    void showToast(int msgId);

}
