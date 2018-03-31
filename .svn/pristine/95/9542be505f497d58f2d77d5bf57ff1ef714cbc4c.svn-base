package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.adapter.AdapterStockOperationRecord;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.entity.StockRecordModel;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.EmptyView;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;

/**
 * Author: xu.yang on 2017/12/14
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:私募操作记录列表
 */
public class SimuOperationRecordActivity extends MvpActivity<CommonPresenter> implements CommonView<StockRecordModel> {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.bob_empty_view)
    EmptyView mEmptyView;
    @Bind(R.id.rv)
    RecyclerView rv;
    private String encMsg, signMsg, id;
    private AdapterStockOperationRecord mAdapter;
    private List<StockRecordModel.BodyBean.RecordListBean> recordListBeanList;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operationrecord);

        Intent in = getIntent();
        if(in != null){
            id = in.getStringExtra("id");
        }

        //下拉刷新条的颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorGold);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                request(id);
            }
        });

        mEmptyView.bindView(mSwipeRefreshLayout); // 设置bindView
        mEmptyView.buttonClick(this, "getData"); // 当button点击时调用哪个方法

        toolbar.setNavigationIcon(R.drawable.back_black);
        tvTitle.setText("操作记录");
        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.finishActivity(mActivity);
            }
        });

        mAdapter = new AdapterStockOperationRecord(R.layout.overseaslist_item,null);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);

        getData();
    }
    void getData(){
        request(id);
    }

    void request(String id) {
        mSwipeRefreshLayout.setRefreshing(true);
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());
//        requestModel.setId(id);
        requestModel.setPositionId(id);
        requestBody.setBody(requestModel);
        requestBody.setHeader(headerBean);
        Gson gson = new Gson();
        String json = gson.toJson(requestBody);
        LogUtils.logd("参数："+json);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.getfundrecordlist(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        if(count == 0){
            mEmptyView.loading();
        }
    }


    @Override
    public void getDataSuccess(StockRecordModel model) {
        count++;
        mSwipeRefreshLayout.setRefreshing(false);
        recordListBeanList = model.getBody().getRecordList();


        if (recordListBeanList != null && recordListBeanList.size() > 0) {
            mEmptyView.success();
            mAdapter.setNewData(recordListBeanList);
        }else{
            mEmptyView.setEmptyText("还没有操作记录哦～");
            mEmptyView.empty();
        }
    }

    @Override
    public void getDataFail(String msg) {
        mSwipeRefreshLayout.setRefreshing(false);
        toastShow(msg);
        mEmptyView.loadfail();
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onPageStart(mActivity,"私募操作记录页面");
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity,"私募操作记录页面");
    }

}
