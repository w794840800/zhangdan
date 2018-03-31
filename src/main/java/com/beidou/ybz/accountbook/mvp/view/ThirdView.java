package com.beidou.ybz.accountbook.mvp.view;


import com.beidou.ybz.accountbook.mvp.main.BaseView;

/**
 * Created by bob on 2017/3/24.
 * 集合时下最热门的rxjava+retrofit+okhttp+mvp
 */
public interface ThirdView<T> extends BaseView {
    void ThirdSuccess(T model);
    void ThirdFail(String msg);

}
