package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.widget.ProgressWebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: xu.yang on 2018/3/8
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:
 */
public class WebViewActivity extends BaseActivity {
    @Bind(R.id.btn_back)
    ImageButton btnBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.webview)
    WebView mWebView;
    boolean blockLoadingNetworkImage = false;
    //    private String requestUrl = "http://192.168.1.171:13006/trade/account/index.htm?encMsg=a/gixlx9lUpvMeyUAnG6u7AZ6GPGCp4OwOV+vWBBmEQYw5+ru5dKZMydZJ72WqBOQ02LVShfrgh3uWv8/TNSDx9GLFIEORNrvidjn3f+FXC4nOP3OEFs31n2fcw55BdYlW9bqp6lv5PRcpHILpfxC51dX47xjWq9AeSQGEv6ZCHV5M9aJH1waima3j7BimslXIDOBufhOMb91tbFjjYnL638dumi3jBm8kToCnbvAT2uR3qahCWAxSDk6rWFZsrQMrDFNZcRj5MUZHjXhD8FBiCASWol9nx4JgwpdcwOMkY+dRAjH57UczJeOusU8YFZkJq9zSb7Or1l++uIrTwKI0OgjcPrbIvjYLILgKloxlosu+27sb5nDSExnImhKk0FovOu+9OqDjE=";
    private String requestUrl = "http://192.168.1.171:13006/trade//upload/upload.htm?encMsg=a/gixlx9lUpvMeyUAnG6u7AZ6GPGCp4OwOV+vWBBmEQYw5+ru5dKZMydZJ72WqBOQ02LVShfrgh3uWv8/TNSDx9GLFIEORNrGi9umPlZbnrr/poqUvQGssWHtvrvWdpwHjdLoUZbJBkCjAgMv8EEXury4d6Ky0qs01Gan2znI41yLyrmrqry0B2nbFLM6DWPJQH/iKs/NCVVh6FMY+uJt6Ua+VGEy9X9";
    private ValueCallback<Uri> mUploadMessage;
    private static final int FILE_SELECT_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        ButterKnife.bind(this);

        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,
                                                    final String url) {
                LogUtils.loge("来自的url:" + url);
//                    view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mWebView.getSettings().setBlockNetworkImage(false);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });

        final WebSettings webSettings = mWebView.getSettings();
        webSettings.setBuiltInZoomControls(true);// ����
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 允许执行javascript语句
        webSettings.setJavaScriptEnabled(true);
//        mWebView.addJavascriptInterface(new WebTOANDROIDInterface(), "android");
        // webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置 缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);

        webSettings.setBlockNetworkImage(true);
        blockLoadingNetworkImage = true;
        webSettings.setDefaultFontSize(50);
        webSettings.setDefaultFixedFontSize(30);

        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);

        // WebView inside Browser doesn't want initial focus to be set.
        webSettings.setNeedInitialFocus(false);
        // Browser supports multiple windows
        webSettings.setSupportMultipleWindows(true);


        if (Build.VERSION.SDK_INT >= 19) {
            // webSettings.setLoadsImagesAutomatically(true);
            webSettings.setBlockNetworkImage(true);
        } else {
            // webSettings.setLoadsImagesAutomatically(false);
            // webSettings.setBlockNetworkImage(false);
        }

        mWebView.loadUrl(requestUrl);
    }

    private class MyWebChromeClient extends WebChromeClient {


        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {

            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, "File Chooser"), FILE_SELECT_CODE);

        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            startActivityForResult(Intent.createChooser(i, "File Browser"), FILE_SELECT_CODE);
        }

        // For Android 4.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, "File Chooser"), FILE_SELECT_CODE);

        }

        // For Android >= 5.0
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
//            mUploadMessage = filePathCallback;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, "File Chooser"), FILE_SELECT_CODE);
            return true;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case FILE_SELECT_CODE : {
                Uri uri = data.getData();
                Log.e("Tag", "Path:" + uri.toString());
                mUploadMessage.onReceiveValue(uri);
                mUploadMessage = null;
            }
            break;
        }
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
    }
}
