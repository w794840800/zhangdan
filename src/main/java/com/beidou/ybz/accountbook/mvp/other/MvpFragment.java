package com.beidou.ybz.accountbook.mvp.other;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beidou.ybz.accountbook.ui.fragment.BaseFragment;



/**
 * Created by WuXiaolong on 2016/3/30.
 * github:https://github.com/WuXiaolong/
 * 微信公众号：吴小龙同学
 * 个人博客：http://wuxiaolong.me/
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract P createPresenter();

    public void showLoading() {
        showProgressDialog();
    }

    public void hideLoading() {
        dismissProgressDialog();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
