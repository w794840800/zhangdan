package com.beidou.ybz.accountbook.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.entity.YZMResponseModel;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.ImeUtil;
import com.beidou.ybz.accountbook.util.Utils;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestCsActivity extends MvpActivity<CommonPresenter> implements CommonView<YZMResponseModel> {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.Webview)
    WebView Webview;


    private String encMsg, signMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_cs);
        ButterKnife.bind(this);


        tvTitle.setText("财商");
        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImeUtil.hideSoftKeyboard(v);
                ActivityUtils.finishActivity(mActivity);
            }
        });

        request();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && Webview.canGoBack()) {
            Webview.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this, this);
    }

    @Override
    public void getDataSuccess(YZMResponseModel model) {

    }

    @Override
    public void getDataFail(String msg) {

    }


    void request() {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel addOverseasRequestModel = new AddOverseasRequestModel();
        addOverseasRequestModel.setUserNo(spUtils.getUserId());
        addOverseasRequestModel.setNickName("");
        addOverseasRequestModel.setPortraitUrl("");
        requestBody.setBody(addOverseasRequestModel);
        requestBody.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(requestBody);

        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        mvpPresenter.fqindex(encMsg, signMsg, "2", spUtils.getSecretKeyId());

        String url = "http://www.360caifu.test/ybz/api/" + "fq/index.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
        Log.e("", "request: --------utl----------" + url);
//        Webview.loadUrl(url);

        Webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 设置WevView要显示的网页
        WebSettings webSettings = Webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //是否使用WebView内置的放大机制，貌似设置了这条以后下面那条不用设置了
        webSettings.setBuiltInZoomControls(true);
        //设置WebView是否支持放大
        webSettings.setSupportZoom(true);
        //以下两条设置可以使页面适应手机屏幕的分辨率，完整的显示在屏幕上
        //设置是否使用WebView推荐使用的窗口
        webSettings.setUseWideViewPort(true);
        //设置WebView加载页面的模式
        webSettings.setLoadWithOverviewMode(true);

        Webview.setWebViewClient(new WebViewClient() {
            // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("", "shouldOverrideUrlLoading: -------rrr---------" + url);
                view.loadUrl(url);
                return true;
            }
        });
        Webview.loadUrl(url);
    }

}
