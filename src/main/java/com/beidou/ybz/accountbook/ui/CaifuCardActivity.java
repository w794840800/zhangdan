package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.adapter.AdapterCaifucard;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.CaifuCardModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.entity.UserNoModel;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.ImeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: xu.yang on 2018/3/2
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:我的-财富卡
 */
public class CaifuCardActivity extends MvpActivity<CommonPresenter> implements CommonView<CaifuCardModel> {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rvCaifuCard)
    RecyclerView rvCaifuCard;
    @Bind(R.id.llEmptyView)
    LinearLayout llEmptyView;
    private String encMsg, signMsg;
    private AdapterCaifucard mAdapter;
    private List<CaifuCardModel.BodyBean.CardListBean> cardListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caifucard);
        ButterKnife.bind(this);

        tvTitle.setText("我的财富卡");
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



        rvCaifuCard.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AdapterCaifucard(R.layout.caifucarddlist_item, null);
        rvCaifuCard.setAdapter(mAdapter);

    }

    /**
     * 我的财富卡列表接口
     */
    private void getCaifucardlist() {
        RequestBody<UserNoModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        UserNoModel requestModel = new UserNoModel();

        requestModel.setUserNo(spUtils.getUserId());
        requestBody.setBody(requestModel);
        requestBody.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(requestBody);

        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.getCaifucardlist(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }


    @Override
    public void getDataSuccess(CaifuCardModel model) {
        cardListBean = model.getBody().getCardList();
        if (cardListBean != null && cardListBean.size() > 0) {
            rvCaifuCard.setVisibility(View.VISIBLE);
            llEmptyView.setVisibility(View.GONE);
            mAdapter.setNewData(model.getBody().getCardList());
        } else {
            rvCaifuCard.setVisibility(View.GONE);
            llEmptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getDataFail(String msg) {
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this, this);
    }

    @OnClick(R.id.btnBindCard)
    public void onViewClicked() {
        requestUrl();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCaifucardlist();
    }

    void requestUrl() {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();//10051547
        requestModel.setUserNo(spUtils.getUserId());//spUtils.getUserId()
//        requestModel.setSource("1000");

        requestBody.setBody(requestModel);
        requestBody.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(requestBody);
        LogUtils.loge("请求参数：" + json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(ApiStores.key, ApiStores.iv, json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = ApiStores.CaifubaoOpenAccount + "?encMsg=" + encMsg;
        LogUtils.loge("请求url：" + url);
        // requestUrl(id,stockCode,stockName,positionNumber,brokerName,memo);from
        Intent in = new Intent(mActivity, X5WebActivity.class);//CurrentFinancialWebActivity
        in.putExtra("url", url);
        startActivity(in);
        overridePendingTransition(R.anim.left_in, 0);
    }
}
