package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.FundDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.mvp.view.OtherView;
import com.beidou.ybz.accountbook.mvp.view.ThirdView;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.ImeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.ProgressWebView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author: Bob on 2018/03/07
 * QQ:754444814
 * E-mail:754444814@qq.com
 * Module:活期理财H5界面
 */
public class CurrentFinancialWebActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    ProgressWebView mWebView;
    @Bind(R.id.relativelayout)
    LinearLayout relativelayout;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tvEdit)
    TextView tvEdit;
    String url, from, id, stockCode, stockName, positionNumber, brokerName, memo;
    private String mFailingUrl;
    private String murl = "http://192.168.1.171:13006/trade/account/index.htm?encMsg=a/gixlx9lUpvMeyUAnG6u3abDxn2nCAkhbbKcFWuirHYtpg/W3Ki1xMINBOZ+ZGY4JxQnEfdR5mrXl/TFLUJ3ofcoNZ/NGl2MZc2BmavGdgdLJyLRV+B2DVxKt+ZJzuizJfTkx0wZ/f3j7F/AbVyZMx6DQ87uVPXtdOTpRrRAJXTRSwTW4+VbNupAsHupA/tNxWptBpTagDi1VwzNLf6uG1dL+ye0JfJ/4et3L5iPAyuxVFI6en+9cVRsKjNOuY8xDJq4ze+nh9kO+o0dZY0aJdqa1o1jT+5+nJTFhFJxMpwsGi4nkgx8CkeTvn8FfithUcr1y67sbsP1N4Hmk1RMfvoGh7SfIVrEX89kRgjwj/HaqLX7obWTwLda/8BaTIuDGJYjy2dxj4=";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currentfinancial_web);
        initView();
    }

    public void initView() {
        mWebView = new ProgressWebView(CurrentFinancialWebActivity.this, null, tvTitle);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        mWebView.setLayoutParams(lp);

        mWebView.setWebViewClient(new WebViewClient());

        relativelayout.addView(mWebView);

        Intent intent = getIntent();

        url = intent.getStringExtra("url");
//        imageUrl = intent.getStringExtra("imageurl");
//        title = intent.getStringExtra("title");
//        isShare = intent.getBooleanExtra("isShare", false);
//        //from,id,stockCode,stockName,positionNumber,brokerName,memo
//        from = intent.getStringExtra("from");
//        id = intent.getStringExtra("id");
//        stockCode = intent.getStringExtra("stockCode");
//        stockName = intent.getStringExtra("stockName");
//        positionNumber = intent.getStringExtra("positionNumber");
//        brokerName = intent.getStringExtra("brokerName");
//        memo = intent.getStringExtra("memo");
//
//        if (stockCode == null || TextUtils.isEmpty(stockCode)) {
//            tvSubtitle.setVisibility(View.GONE);
//        } else {
//            tvSubtitle.setVisibility(View.VISIBLE);
//            tvSubtitle.setText(stockCode);
//        }

//        if (from != null && !TextUtils.isEmpty(from)) {
//            tvEdit.setVisibility(View.VISIBLE);
//        } else {
//            tvEdit.setVisibility(View.INVISIBLE);
//        }

        Logger.e(url);
        if (url != null) {
            mWebView.loadUrl(url);
        }
//        LogUtils.loge("tvTitle.getText():" + tvTitle.getText().toString());
        mWebView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");


    }

    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void errorReload() {
            Logger.i("reload:"+mFailingUrl);

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (mFailingUrl != null) {
                        LogUtils.logd("mFailingUrl:"+mFailingUrl);
                        mWebView.loadUrl(mFailingUrl);
                    }
                }
            });
        }
    }

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            LogUtils.logd("返回事件url:"+url);
            // 调用拨号程序
            //                en://trade/account/back?eventType=openPage&openMode=_self
            if (url.contains("en://trade/account/back?eventType=openPage&openMode=_self")) {//返回
              ActivityUtils.finishActivity(mActivity);
            }else if (url.contains("en://trade/login/failed?eventType=openPage&openMode=_self")) {//未登录
                ActivityUtils.startActivityRightInWithFrom(mActivity,LoginActivity.class,"forgetGes");
            }else if(url.contains("en://trade/pwd/change?eventType=openPage&openMode=_self")){//修改密码
                ActivityUtils.startActivityRightIn(mActivity,VertifyPayPasswdActivity.class);
            } else {
                view.loadUrl(url);
                LogUtils.loge("哈哈哈哈哈或或或：：：：：" + url);
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            mFailingUrl = failingUrl;
            Logger.d(failingUrl);
            //加载出错的自定义界面
            view.loadUrl("file:///android_asset/error.html");
        }
    }


    @Override
    public boolean onKeyDown(int keyCoder, KeyEvent event) {
        Logger.e("mWebView.canGoBack():" + (mWebView.canGoBack()));
        if (mWebView.canGoBack() && keyCoder == KeyEvent.KEYCODE_BACK) {
            mWebView.goBack();
            return false;
        } else {
//        if (keyCoder == KeyEvent.KEYCODE_BACK) {
            ActivityUtils.finishActivity(CurrentFinancialWebActivity.this);
//            ActivityUtils.finishActivity(WebActivity.this);// 按了返回键，但已经不能返回(mWebView.canGoBack()=false)，则执行退出确认
            return true;
        }
//        return false;
    }


    @OnClick({R.id.ib_back, R.id.tvEdit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                ActivityUtils.finishActivity(CurrentFinancialWebActivity.this);
                ImeUtil.hideSoftKeyboard(tvEdit);
                break;
            case R.id.tvEdit:

                break;
        }
    }




    @Override
    protected void onPause() {
        super.onPause();
        ImeUtil.hideSoftKeyboard(tvEdit);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.clearCache(true); //清空缓存
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (relativelayout != null) {
                    relativelayout.removeView(mWebView);
                }
                mWebView.removeAllViews();
                mWebView.destroy();
            } else {
                mWebView.removeAllViews();
                mWebView.destroy();
                if (relativelayout != null) {
                    relativelayout.removeView(mWebView);
                }
            }
            mWebView = null;
        }

    }

}
