package com.beidou.ybz.accountbook.mvp.other;


import android.content.Context;

import com.beidou.ybz.accountbook.mvp.main.BaseView;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.retrofit.AppClient;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by bob on 2017/3/24.
 * 集合时下最热门的rxjava+retrofit+okhttp+mvp
 */
public class BasePresenter<V extends BaseView> {
    public V mvpView;
    protected ApiStores apiStores;
    protected ApiStores apiStoresSearch;
    private CompositeSubscription mCompositeSubscription;

    public void attachView(Context context,V  mvpView) {
        this.mvpView = mvpView;
        apiStores = AppClient.retrofit(context).create(ApiStores.class);
        apiStoresSearch = AppClient.searchRetrofit(context).create(ApiStores.class);
    }


    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }


    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }


    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
