package com.example.graduationproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public abstract class BaseFragmentActivity extends FragmentActivity{



    /**
     * 设置布局
     * @return
     */
    public abstract int layoutId();

    /**
     * 初始化组件
     */
    public abstract void initView();

    /**
     * 初始化Presenter
     * 例如 new XXXPresenter();
     */
    public abstract void initPresenter();

    /**
     * 在调用setContentView的方法之前
     */
    public abstract void setBeforeContentView();

    /**
     * 在调用setContentView的方法之后
     */
    public abstract void setAfterContentView();

    public BaseFragmentActivity mBaseFragmentActivity;

    /**
     * Fragment管理器
     */
    protected FragmentManager mFragmentManager = null;
    /**
     * Fragment的parent view,即Fragment的容器
     */
    protected int mFragmentContainer;
    /**
     * 当前显示的Fragment
     */
    public Fragment mCurrentFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseFragmentActivity = this;
        setBeforeContentView();
        int layoutId = layoutId();
        setContentView(layoutId);
        setAfterContentView();
        mFragmentManager = getSupportFragmentManager();
        initView();
        initPresenter();

    }

    /**
     * 需要在调用任何函数前设置
     *
     * @param container 用于放置fragment的布局id
     */
    public void setFragmentContainerId(int container) {
        mFragmentContainer = container;
    }

    /**
     * 显示Fragment，并且把上一个隐藏
     *
     * @param fragmentShow
     */
    public void showFragment(Fragment fragmentShow) {
        showFragmentInContainer(mFragmentContainer, fragmentShow, 0);
    }

    /**
     * 将fragmentShow显示在一个新的container上,而不覆盖mFragmentContainer。
     * 这种情况适用于Fragment中又嵌套Fragment
     *
     * @param container
     * @param fragmentShow
     */
    public void showFragmentInContainer(int container, Fragment fragmentShow, int anim) {
        checkContainer();

        if (mCurrentFragment != fragmentShow) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            if (anim != 0) {
                transaction.setCustomAnimations(anim, 0);
            }
            if (mCurrentFragment != null) {
                // 首先隐藏原来显示的Fragment
                transaction.hide(mCurrentFragment);
            }
            // 然后再显示传递进来的Fragment
            if (mFragmentManager.findFragmentByTag(fragmentShow.getClass().getName()) == null) {
                transaction
                        .add(container, fragmentShow, fragmentShow.getClass().getName());
            } else {
                transaction.show(fragmentShow);
            }
            transaction.commit();
            mCurrentFragment = fragmentShow;
        }
    }

    /**
     * 检查放置fragment的布局id
     */
    private void checkContainer() {
        if (mFragmentContainer <= 0) {
            throw new RuntimeException(
                    "在调用replaceFragment函数之前请调用setFragmentContainerId函数来设置fragment container id");
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
