package com.beidou.ybz.accountbook.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.ShareModel;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.AlertDialogUtils;
import com.beidou.ybz.accountbook.util.DialogClickListener;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.TestUrl;
import com.beidou.ybz.accountbook.widget.X5WebView;
import com.beidou.ybz.accountbook.widget.morewindow.MoreWindow;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.utils.TbsLog;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 带标题栏的webview
 */
public class X5TitleWebActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    /**
     * 作为一个浏览器的示例展示出来，采用android+web的模式
     */
    private X5WebView mWebView;
    private ViewGroup mViewParent;
    String url;
    private static final String mHomeUrl = "http://192.168.1.171:13006/trade//upload/upload.htm?encMsg=a/gixlx9lUpvMeyUAnG6u7AZ6GPGCp4OwOV+vWBBmEQYw5+ru5dKZMydZJ72WqBOQ02LVShfrgh3uWv8/TNSDx9GLFIEORNrGi9umPlZbnrr/poqUvQGssWHtvrvWdpwHjdLoUZbJBkCjAgMv8EEXury4d6Ky0qs01Gan2znI41yLyrmrqry0B2nbFLM6DWPJQH/iKs/NCVVh6FMY+uJt6Ua+VGEy9X9";//http://app.html5.qq.com/navi/index";
    private static final String TAG = "SdkDemo";
    private static final int MAX_LENGTH = 14;
    private boolean mNeedTestPage = false;

    private final int disable = 120;
    private final int enable = 255;

    private ProgressBar mPageLoadingProgressBar = null;

    private ValueCallback<Uri> uploadFile;

    private URL mIntentUrl;

    //低版本选取回来的是Uri
//	private ValueCallback<Uri> uploadFile;
    //高版本选取回来的是Uri数组
    private ValueCallback<Uri[]> uploadFileAboveL;
    private final static int FILE_CHOOSER_RESULT_CODE = 9999;
    AlertDialogUtils alertDialogUtils;
    private String phoneNumber;
    private MoreWindow mMoreWindow;
    private ShareModel shareModel = new ShareModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		getWindow().setFormat(PixelFormat.TRANSLUCENT);

        Intent intent = getIntent();

        url = intent.getStringExtra("url");
        LogUtils.logd("加载的链接：" + url);
        if (intent != null) {
            try {
                mIntentUrl = new URL(intent.getData().toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {

            } catch (Exception e) {
            }
        }


        //
        try {
            if (Integer.parseInt(Build.VERSION.SDK) >= 11) {
                getWindow()
                        .setFlags(
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
        }

		/*
         * getWindow().addFlags(
		 * android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 */
        setContentView(R.layout.activity_x5titleweb);
        mViewParent = (ViewGroup) findViewById(R.id.webView1);


        mTestHandler.sendEmptyMessageDelayed(MSG_INIT_UI, 10);
        alertDialogUtils = new AlertDialogUtils(mActivity);
        alertDialogUtils.setOnDialogClickListener(new DialogClickListener() {
            @Override
            public void clickYes(int which) {

//                RxPermissions.getInstance(X5WebActivity.this)
//                        .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)//这里填写所需要的权限
//                        .subscribe(new Action1<Boolean>() {
//                            @Override
//                            public void call(Boolean aBoolean) {
//                                if (aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
//
//                                }
//                            }
//                        });
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phoneNumber));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void clickNo() {
            }
        });


//        shareModel.setTitle("%E6%BE%B3%E5%A4%A7%E5%88%A9%E4%BA%9A%E5%A2%A8%E5%B0%94%E6%9C%AC-The Peak%E7%B2%BE%E5%93%81%E5%85%AC%E5%AF%93");
//        shareModel.setContent("The Peak%E4%BD%8D%E4%BA%8E%E4%B8%96%E7%95%8C%E6%9C%80%E5%AE%9C%E5%B1%85%E5%9F%8E%E5%B8%82%E4%B9%8B%E4%B8%80%E7%9A%84%E5%9F%8E%E5%B8%82%E2%80%94%E2%80%94%E5%A2%A8%E5%B0%94%E6%9C%AC%EF%BC%8C%E4%BA%AB%E6%9C%89%E6%95%B4%E4%B8%AA%E5%9F%8E%E5%B8%.");
//        shareModel.setImgurl("http://www.360caifu.test/upload/prdcenterproduct/OP201801265316/e71ec374d4374ba590914b58ed2b3822.jpg");
//        shareModel.setHtmlurl(url);

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        try {
            super.onConfigurationChanged(newConfig);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 向webview发出信息
    private void enableX5FullscreenFunc() {

        if (mWebView.getX5WebViewExtension() != null) {
            Toast.makeText(this, "开启X5全屏播放模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", false);// true表示标准全屏，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            mWebView.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    private void disableX5FullscreenFunc() {
        if (mWebView.getX5WebViewExtension() != null) {
            Toast.makeText(this, "恢复webkit初始状态", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", true);// true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            mWebView.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    private void enableLiteWndFunc() {
        if (mWebView.getX5WebViewExtension() != null) {
            Toast.makeText(this, "开启小窗模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", false);// true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", true);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            mWebView.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    private void enablePageVideoFunc() {
        if (mWebView.getX5WebViewExtension() != null) {
            Toast.makeText(this, "页面内全屏播放模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", false);// true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", 1);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            mWebView.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    private void initProgressBar() {
        mPageLoadingProgressBar = (ProgressBar) findViewById(R.id.progressBar1);// new
        // ProgressBar(getApplicationContext(),
        // null,
        // android.R.attr.progressBarStyleHorizontal);
        mPageLoadingProgressBar.setMax(100);
//		mPageLoadingProgressBar.setProgressDrawable(this.getResources()
//				.getDrawable(R.drawable.color_progressbar));
    }


    void splitString(String ss){
        Map<String, String> paramMap = TestUrl.urlSplit(ss);
        LogUtils.loge("--------paramMap-------"+paramMap);
//        String s1 = ss.substring(ss.indexOf("?")+1);
//        LogUtils.loge("s1:"+s1);//title=title1&intro=intro2&image=image3"
//        String[] s2 = s1.split("&");
//        String title = null,intro = null,image = null;
//        for (int i = 0; i < s2.length; i++) {
//            title = s2[0].substring(s2[0].indexOf("=")+1);
//            intro = s2[1].substring(s2[1].indexOf("=")+1);
//            image = s2[2].substring(s2[2].indexOf("=")+1);
//        }
//
        try {
            if (paramMap.get("title")!=null){
                shareModel.setTitle(URLDecoder.decode(paramMap.get("title"), "utf-8"));
            }
            if (paramMap.get("intro")!=null){
                if (URLDecoder.decode(paramMap.get("intro"), "utf-8").length()>80){
                    shareModel.setContent(URLDecoder.decode(paramMap.get("intro"), "utf-8").substring(0,80));
                }else {
                    shareModel.setContent(URLDecoder.decode(paramMap.get("intro"), "utf-8"));
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (paramMap.get("image")!=null){
            if(paramMap.get("image").startsWith("/")){
                shareModel.setImgurl(ApiStores.CMS_IMG_URL2+paramMap.get("image"));
            }else{
                shareModel.setImgurl(ApiStores.CMS_IMG_URL+paramMap.get("image"));
            }
        }

        shareModel.setHtmlurl(url);
//
//        LogUtils.loge("title:"+title+"\nintro:"+intro+"\nimage:"+image);
    }

    private void init() {

        mWebView = new X5WebView(this, null);

        mViewParent.addView(mWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.FILL_PARENT,
                FrameLayout.LayoutParams.FILL_PARENT));

        initProgressBar();

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.logd("返回事件url:" + url);
                // 调用拨号程序
                //                en://trade/account/back?eventType=openPage&openMode=_self
                if (url.contains("en://trade/account/back?eventType=openPage&openMode=_self")) {//返回
                    ActivityUtils.finishActivity(mActivity);
                } else if (url.contains("en://trade/login/failed?eventType=openPage&openMode=_self")) {//未登录
                    ActivityUtils.startActivityRightInWithFrom(mActivity, LoginActivity.class, "forgetGes");
                    finish();
                } else if (url.contains("en://trade/pwd/change?eventType=openPage&openMode=_self")) {//修改密码
                    ActivityUtils.startActivityRightIn(mActivity, VertifyPayPasswdActivity.class);
                } else if (url.contains("en://trade/pwd/forget?eventType=openPage&openMode=_self")) {//忘记密码,修改完要跳回来
                    ActivityUtils.startActivityRightInWithFrom(mActivity,VerifyOldPhoneActivity.class,"forgetPayPasswdFromWeb");
                }else if (url.startsWith("en://goback")){//返回
                    ActivityUtils.finishActivity(mActivity);
                } else if (url.startsWith("en://share")){//分享
//                    if(shareModel == null){
                        splitString(url);
//                    }
                    showMoreWindow(mWebView);
                } else if (url.startsWith("tel:")){//拨打电话
                    phoneNumber = url.split(":")[1];
                    alertDialogUtils.setMessage("是否拨打电话"+ phoneNumber);
                    alertDialogUtils.show();
                }else if (url.startsWith("en://htjLogin")){
                    if (spUtils.getIsLogin()) {
                        mWebView.reload();//webview刷新
                    } else {
                        ActivityUtils.startActivityRightIn(X5TitleWebActivity.this, LoginActivity.class);
                    }
                }else {
                    view.loadUrl(url);
                    LogUtils.loge("哈哈哈哈哈或或或：：：：：" + url);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // mTestHandler.sendEmptyMessage(MSG_OPEN_TEST_URL);
                mTestHandler.sendEmptyMessageDelayed(MSG_OPEN_TEST_URL, 5000);// 5s?
//					changGoForwardButton(view);
				/* mWebView.showLog("test Log"); */
				if (spUtils.getIsLogin()){
                    mWebView.loadUrl("javascript:appLogin('" + spUtils.getUserId() + "')");
                }
                mWebView.loadUrl("javascript:appGoBack()");
                mWebView.loadUrl("javascript:appLogin()");
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsConfirm(WebView arg0, String arg1, String arg2,
                                       JsResult arg3) {
                return super.onJsConfirm(arg0, arg1, arg2, arg3);
            }

            View myVideoView;
            View myNormalView;
            CustomViewCallback callback;


            /**
             * 全屏播放配置
             */
            @Override
            public void onShowCustomView(View view,
                                         CustomViewCallback customViewCallback) {
//				FrameLayout normalView = (FrameLayout) findViewById(R.id.web_filechooser);
//				ViewGroup viewGroup = (ViewGroup) normalView.getParent();
//				viewGroup.removeView(normalView);
//				viewGroup.addView(view);
//				myVideoView = view;
//				myNormalView = normalView;
//				callback = customViewCallback;
            }

            @Override
            public void onHideCustomView() {
                if (callback != null) {
                    callback.onCustomViewHidden();
                    callback = null;
                }
                if (myVideoView != null) {
                    ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
                    viewGroup.removeView(myVideoView);
                    viewGroup.addView(myNormalView);
                }
            }

            @Override
            public boolean onJsAlert(WebView arg0, String arg1, String arg2,
                                     JsResult arg3) {
                /**
                 * 这里写入你自定义的window alert
                 */
                return super.onJsAlert(null, arg1, arg2, arg3);
            }


            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> valueCallback) {
                uploadFile = valueCallback;
                openImageChooserActivity();
            }

            // For Android  >= 3.0
            public void openFileChooser(ValueCallback valueCallback, String acceptType) {
                uploadFile = valueCallback;
                openImageChooserActivity();
            }

            //For Android  >= 4.1
            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
                Log.e("openFileChooser", ">= 4.1");
                uploadFile = valueCallback;
                openImageChooserActivity();
            }

            // For Android >= 5.0
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
                Log.e("openFileChooser", ">= 5.0");
                uploadFileAboveL = valueCallback;
                openImageChooserActivity();
                return true;
            }

            @Override
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
                tvTitle.setText(s);
            }
        });



        WebSettings webSetting = mWebView.getSettings();
        webSetting.setUserAgentString("ybz");
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);


        mWebView.getView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        mWebView.addJavascriptInterface(new WebViewJavaScriptFunction() {
            @Override
            public void onJsFunctionCalled(String tag) {
            }

            @JavascriptInterface
            public void onX5ButtonClicked() {
                enableX5FullscreenFunc();
            }

            @JavascriptInterface
            public void onCustomButtonClicked() {
                disableX5FullscreenFunc();
            }

            @JavascriptInterface
            public void onLiteWndButtonClicked() {
                enableLiteWndFunc();
            }

            @JavascriptInterface
            public void onPageVideoClicked() {
                enablePageVideoFunc();
            }
        }, "Android");


        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setPreFectch(true);
        long time = System.currentTimeMillis();
        if (mIntentUrl == null) {
            mWebView.loadUrl(url);
        } else {
            mWebView.loadUrl(mIntentUrl.toString());
        }

        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        TbsLog.d("time-cost", "cost time: "
                + (System.currentTimeMillis() - time));
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }

    //打开选取的方法
    private void openImageChooserActivity() {
        LogUtils.loge("openImageChooserActivity--------");
        RxPermissions.getInstance(X5TitleWebActivity.this)
//                .request(Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS)//这里填写所需要的权限
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)//这里填写所需要的权限
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
                            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                            i.addCategory(Intent.CATEGORY_OPENABLE);
                            i.setType("image/*");
                            startActivityForResult(Intent.createChooser(i, "请选择图片"), FILE_CHOOSER_RESULT_CODE);
                        }else{
                        }
                    }
                });

    }


    boolean[] m_selected = new boolean[]{true, true, true, true, false,
            false, true};

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
                if (Integer.parseInt(Build.VERSION.SDK) >= 16)
//					changGoForwardButton(mWebView);
                    return true;
            } else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }


    //选取回执
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (null == uploadFile && null == uploadFileAboveL) return;
            Uri result = data == null || resultCode != Activity.RESULT_OK ? null : data.getData();
            if (uploadFileAboveL != null) {
                Log.e("onActivityResult", "uploadFileAboveL != null");
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (uploadFile != null) {
                Log.e("onActivityResult", "uploadFile != null");
                uploadFile.onReceiveValue(result);
                uploadFile = null;
            }
        }
    }

    //这里intent.getClipData()方法需要在api16以上才能使用这个
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != FILE_CHOOSER_RESULT_CODE || uploadFileAboveL == null)
            return;
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                String dataString = intent.getDataString();

                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    LogUtils.loge("dataString:" + dataString);
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        uploadFileAboveL.onReceiveValue(results);
        uploadFileAboveL = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent == null || mWebView == null || intent.getData() == null)
            return;
        mWebView.loadUrl(intent.getData().toString());
    }

    @Override
    protected void onDestroy() {
        if (mTestHandler != null)
            mTestHandler.removeCallbacksAndMessages(null);
        if (mWebView != null)
            mWebView.destroy();
        super.onDestroy();
    }

    public static final int MSG_OPEN_TEST_URL = 0;
    public static final int MSG_INIT_UI = 1;
    private final int mUrlStartNum = 0;
    private int mCurrentUrl = mUrlStartNum;
    private Handler mTestHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_OPEN_TEST_URL:
                    if (!mNeedTestPage) {
                        return;
                    }

                    String testUrl = "file:///sdcard/outputHtml/html/"
                            + Integer.toString(mCurrentUrl) + ".html";
                    if (mWebView != null) {
                        mWebView.loadUrl(testUrl);
                    }

                    mCurrentUrl++;
                    break;
                case MSG_INIT_UI:
                    init();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public interface WebViewJavaScriptFunction {

        void onJsFunctionCalled(String tag);
    }


    @OnClick(R.id.ib_back)
    public void onViewClicked() {
        ActivityUtils.finishActivity(mActivity);
    }

    private void showMoreWindow(View view) {
        if (null == mMoreWindow) {
            mMoreWindow = new MoreWindow(mActivity, "web", shareModel);
            mMoreWindow.init();
        }
        mMoreWindow.showMoreWindow(view, 100);
    }
}
