package com.beidou.ybz.accountbook.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.CsIndexDataModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.other.MvpFragment;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.ProgressWebView1;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author: Bob on 2017/10/30 15:16
 * QQ:754444814
 * E-mail:754444814@qq.com
 */
public class LoanFragment extends MvpFragment<CommonPresenter> implements CommonView<CsIndexDataModel>{

    ProgressWebView1 mWebView;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.relativelayout)
    LinearLayout relativelayout;
    private String encMsg, signMsg;
    private String url;

    //appLogin(String userNo)js函数
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_loan;
    }

    @Override
    public void initView() {
        mWebView = new ProgressWebView1(getActivity(), null, tvTitle);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(lp);
        relativelayout.addView(mWebView);
    }

    @Override
    public void onResume() {
        super.onResume();
        request();
    }

    /**
     * 增值首页
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void request() {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel addOverseasRequestModel = new AddOverseasRequestModel();

        addOverseasRequestModel.setUserNo(spUtils.getUserId());

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
        url = ApiStores.incrementIndex + "?encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "1" + "&secretKeyId=" + spUtils.getSecretKeyId();
        Log.e("", "request: --------url----------" + url);
        mWebView.loadUrl(url);
    }

    @Override
    protected CommonPresenter createPresenter() {
        if (mActivity != null) {
            return new CommonPresenter(mActivity, this);
        } else {
            return new CommonPresenter(getActivity(), this);
        }
    }


    @Override
    public void getDataSuccess(CsIndexDataModel model) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
