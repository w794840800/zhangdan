package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.StockSearchModel;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.adapter.AdapterStockSearch;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author: xu.yang on 2017/12/4
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:
 */
public class StockSearchMoreActivity extends MvpActivity<CommonPresenter> {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rvSearch)
    RecyclerView rvSearch;
//    @Bind(R.id.tvMoreHint)
//    TextView tvMoreHint;
    private AdapterStockSearch mAdapter;
    private List<StockSearchModel.ResultBean> resultBeans;
    private String stockName,stockCode,keyword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocksearchmore);
        ButterKnife.bind(this);

        tvTitle.setText("更多搜索");
        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.finishActivity(mActivity);
            }
        });

        Intent in = getIntent();
        resultBeans = in.getParcelableArrayListExtra("resultBeans");
        keyword = in.getStringExtra("key");


        initView();
    }

    @Override
    protected CommonPresenter createPresenter() {
        return null;
    }

    void initView() {
        rvSearch.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AdapterStockSearch(R.layout.overseaslist_item, null);
        rvSearch.setAdapter(mAdapter);
        mAdapter.setKey(keyword);
        mAdapter.setNewData(resultBeans);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                stockName = resultBeans.get(position).getName();
                stockCode = resultBeans.get(position).getCode();
                Intent in = new Intent();
                in.putExtra("stockName",stockName);
                in.putExtra("stockCode",stockCode);

                setResult(RESULT_OK, in);
                ActivityUtils.finishActivity(mActivity);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onPageStart(mActivity,"搜索更多页面");
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity,"搜索更多页面");
    }



}
