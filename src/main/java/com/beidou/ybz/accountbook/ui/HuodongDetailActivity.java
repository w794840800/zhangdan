package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.ShareModel;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.widget.ProgressWebView1;
import com.beidou.ybz.accountbook.widget.morewindow.MoreWindow;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.OnClick;

public class HuodongDetailActivity extends BaseActivity {
    ProgressWebView1 mWebView;
    String url,title;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.share)
    ImageButton mShareButton;
    @Bind(R.id.top)
    RelativeLayout top;
    @Bind(R.id.relativelayout)
    LinearLayout relativelayout;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    private MoreWindow mMoreWindow;
    private ShareModel shareModel;
    private String mFailingUrl;
    private String shareString;
    private String trueUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huodong_detail);
        initView();
    }

    public void initView() {
        mWebView = new ProgressWebView1(HuodongDetailActivity.this, null, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(lp);
        mWebView.setWebViewClient(new WebViewClient());
        relativelayout.addView(mWebView);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        //http://act.360caifu.test/ybz/sign/index?share=0//0-不分享
        if(url != null && url.contains("?")){
            trueUrl = url.substring(0,url.indexOf("?"));
            shareString = url.substring(url.indexOf("?")+1);
            LogUtils.loge("shareString前:"+shareString);
            if(shareString != null && shareString.contains("=")) {
                shareString = shareString.substring(shareString.indexOf("=") + 1);
            }
            LogUtils.loge("shareString后:"+shareString);
            if(shareString != null && shareString.equals("0")){
                //不分享
                mShareButton.setVisibility(View.GONE);
            }else{
                //分享
                mShareButton.setVisibility(View.VISIBLE);
            }
        }else{
            trueUrl = url;
            mShareButton.setVisibility(View.VISIBLE);
        }

        LogUtils.logd("trueUrl:" + trueUrl);
        title = intent.getStringExtra("title");
        tvTitle.setText(title);
        LogUtils.logd("url:" + url);
//        if (url != null) {
//            mWebView.loadUrl(url + "?userNo=" + spUtils.getUserId() + "&userName=" + spUtils.getNickName()
//                    + "&mobile=" + spUtils.getPhone() + "&headImg=" + spUtils.getPortraitUrl() + "&token=" + spUtils.getdeviceToken());
//        }

        shareModel = new ShareModel();
        shareModel.setContent("有本账APP，记在这里财心安。");
        shareModel.setHtmlurl(url);
        shareModel.setTitle(tvTitle.getText().toString());
        shareModel.setImgurl(ApiStores.APP_LOGO);
        mWebView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
    }

    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void errorReload() {
            Logger.i("reload:" + mFailingUrl);

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (mFailingUrl != null) {
                        LogUtils.logd("mFailingUrl:" + mFailingUrl);
                        mWebView.loadUrl(mFailingUrl);
                    }
                }
            });
        }
    }

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            LogUtils.logd("url::" + url);
            if (url.startsWith("en://signLogin")) {//登录
//                Intent in = new Intent(mActivity, LoginActivity.class);
//                startActivity(in);
//                overridePendingTransition(R.anim.left_in, 0);
                ActivityUtils.startActivityRightInWithFrom(mActivity, LoginActivity.class, "huodongdetail");//forgetGes

            } else if (url.startsWith("en://signShare")) {//分享
                shareModel.setContent("有本账APP，记在这里财心安。");
//                en://signShare?url=http://act.360caifu.test/ybz/sign/joingroup/15&name=360%E8%B4%A2%E5%AF%8C
                LogUtils.loge("-------有好友邀请您参加有本账活动啦----" + url.split("\\=")[1]);
                shareModel.setHtmlurl(url.split("\\=")[1]);
                shareModel.setTitle("我在星巴克看见你的微笑。"+spUtils.getNickName()+"邀请你来有本账一起赢取星巴克免费兑换券！");
                shareModel.setImgurl(ApiStores.APP_LOGO);
                showMoreWindow(mWebView);
            } else {
                view.loadUrl(url);
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
//            tv.setText(view.getTitle());

            Log.d("WebView", "onPageFinished ");
//            view.loadUrl("javascript:window.local_obj.showSource('<head>'+" +
//                    "document.getElementsByTagName('p')[0].innerText+'</head>');");
//
//            view.loadUrl("javascript:window.local_obj.showSource(" +
//                    "document.getElementById('js_content').innerText);");

        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            mFailingUrl = failingUrl;
//            Logger.d(failingUrl);
            //加载出错的自定义界面
            view.loadUrl("file:///android_asset/error.html");
        }
    }

    @OnClick({R.id.ib_back, R.id.share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                ActivityUtils.finishActivity(HuodongDetailActivity.this);
                break;
            case R.id.share:
                showMoreWindow(mWebView);
                break;
        }
    }

    private void showMoreWindow(View view) {
        if (null == mMoreWindow) {
            mMoreWindow = new MoreWindow(mActivity, "web", shareModel);
            mMoreWindow.init();
        }
        mMoreWindow.showMoreWindow(view, 100);
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

    @Override
    protected void onResume() {
        super.onResume();
        if (url != null) {
            LogUtils.loge("--------活动-------"+trueUrl + "?userNo=" + spUtils.getUserId() + "&userName=" + spUtils.getNickName()
                    + "&mobile=" + spUtils.getPhone() + "&headImg=" + spUtils.getPortraitUrl() + "&token=" + spUtils.getdeviceToken());

            mWebView.loadUrl(trueUrl + "?userNo=" + spUtils.getUserId() + "&userName=" + spUtils.getNickName()
                    + "&mobile=" + spUtils.getPhone() + "&headImg=" + spUtils.getPortraitUrl() + "&token=" + spUtils.getdeviceToken());
        }
        StatService.onPageStart(mActivity, "活动详情页面");
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity, "活动详情页面");
    }
}
