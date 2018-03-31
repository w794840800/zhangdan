package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.widget.ProgressWebView1;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XieyiActivity extends MvpActivity<CommonPresenter> {

    ProgressWebView1 mWebView;
    String url;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.relativelayout)
    LinearLayout relativelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xieyi);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected CommonPresenter createPresenter() {
        return null;
    }

    public void initView() {
        mWebView = new ProgressWebView1(XieyiActivity.this, null, tvTitle);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(lp);
//        mWebView.setWebViewClient(new XieyiActivity.WebViewClient());
        relativelayout.addView(mWebView);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        if (url != null) {
            mWebView.loadUrl(url);
        }
    }

    @OnClick({R.id.ib_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                ActivityUtils.finishActivity(XieyiActivity.this);
                break;
        }
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
