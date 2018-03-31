package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.InsuranceListModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.InsuranceDetailModel;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.AlertDialogUtils;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: xu.yang on 2017/12/6
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module: 保险详情界面
 */
public class InsuranceDetailActivity extends MvpActivity<CommonPresenter> implements CommonView<InsuranceDetailModel> {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tvAmount)
    TextView tvAmount;
    @Bind(R.id.tvMemo)
    TextView tvMemo;
    @Bind(R.id.llMemo)
    LinearLayout llMemo;
    @Bind(R.id.tvYearBonus)
    TextView tvYearBonus;
    @Bind(R.id.llYearBonus)
    LinearLayout llYearBonus;
    @Bind(R.id.viewYearBonus)
    View viewYearBonus;
    private String encMsg, signMsg;
    private List<InsuranceListModel.BodyBean.ProListBean> proListBeanlist;
    AlertDialogUtils alertDialogUtils;
    private String id, name, memo, yearBonus, amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurancedetail);
        ButterKnife.bind(this);

        Intent in = getIntent();
        if (in != null) {
            id = in.getStringExtra("id");
            name = in.getStringExtra("name");
        }

        alertDialogUtils = new AlertDialogUtils(mActivity);
        toolbar.setNavigationIcon(R.drawable.back_black);
        tvTitle.setText(name);
        tvRight.setText("编辑");
        tvRight.setTextColor(getResources().getColor(R.color.txt_color));
        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.finishActivity(mActivity);
            }
        });

        //下拉刷新条的颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorGold);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                request(id);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        request(id);
        StatService.onPageStart(mActivity,"保险详情页面");
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity,"保险详情页面");
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this, this);
    }

    void request(String id) {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());
        requestModel.setId(id);

        requestBody.setBody(requestModel);
        requestBody.setHeader(headerBean);

        Gson gson = new Gson();
        String json = gson.toJson(requestBody);

        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.getinsurance(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }


    @OnClick({R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                Intent in = new Intent(mActivity, AddInsuranceAssets.class);
                in.putExtra("id", id);
                in.putExtra("name", name);
                in.putExtra("yearBonus", yearBonus);
                in.putExtra("memo", memo);
                in.putExtra("amount", amount);
                in.putExtra("from", "add");
                startActivity(in);
                overridePendingTransition(R.anim.left_in, 0);
                break;
        }
    }

    @Override
    public void getDataSuccess(InsuranceDetailModel model) {
        mSwipeRefreshLayout.setRefreshing(false);
        try {

            amount = model.getBody().getInsuDto().getAmount();
            tvAmount.setText(Utils.addCommaContainsPoint(amount));
            memo = model.getBody().getInsuDto().getMemo();
            yearBonus = model.getBody().getInsuDto().getYearBonus();

            if (memo == null || TextUtils.isEmpty(memo)) {
                llMemo.setVisibility(View.GONE);
            } else {
                llMemo.setVisibility(View.VISIBLE);
            }

            if (yearBonus == null || TextUtils.isEmpty(yearBonus)) {
                llYearBonus.setVisibility(View.GONE);
                viewYearBonus.setVisibility(View.GONE);
            } else {
                llYearBonus.setVisibility(View.VISIBLE);
                viewYearBonus.setVisibility(View.VISIBLE);
            }
            tvMemo.setText(memo);
            tvYearBonus.setText(yearBonus);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDataFail(String msg) {
        mSwipeRefreshLayout.setRefreshing(false);
        toastShow(msg);
    }


}
