package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.ProgressWebView1;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.OnClick;

public class MessageDetailActivity extends MvpActivity<CommonPresenter>{

    private String id;
    ProgressWebView1 mWebView;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.relativelayout)
    LinearLayout relativelayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        LogUtils.loge("--------haha---------"+id);
        msgdetail(id);

        mWebView = new ProgressWebView1(MessageDetailActivity.this, null, tvTitle);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(lp);
//        mWebView.setWebViewClient(new XieyiActivity.WebViewClient());
        relativelayout.addView(mWebView);
        String CS_URL = ApiStores.CAISHANG_URL + "sys/msgdetail.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
        LogUtils.loge("-------bbbb--------"+CS_URL);
        mWebView.loadUrl(CS_URL);
    }

    @Override
    protected CommonPresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.ib_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                ActivityUtils.finishActivity(MessageDetailActivity.this);
                break;
        }
    }

    private String encMsg,signMsg;
    private void msgdetail(String id) {
        RequestBody<AddOverseasRequestModel> changeMobileModelRequestBody = new RequestBody<AddOverseasRequestModel>();
        RequestBody.HeaderBean headerBean1 = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel addOverseasRequestModel = new AddOverseasRequestModel();

        addOverseasRequestModel.setId(id);

        changeMobileModelRequestBody.setBody(addOverseasRequestModel);
        changeMobileModelRequestBody.setHeader(headerBean1);
        Gson gson1 = new Gson();
        String json1 = gson1.toJson(changeMobileModelRequestBody);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json1);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        mvpPresenter.msgdetail(encMsg, signMsg, "2", spUtils.getSecretKeyId());
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
        StatService.onPageStart(mActivity,"消息详情页面");
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity,"消息详情页面");
    }

}
