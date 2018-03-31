package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.adapter.AdapterCurrency;
import com.beidou.ybz.accountbook.mvp.entity.CurrencyModel;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.ImeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.widget.ClearEditText;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: xu.yang on 2018/1/7
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:货币搜索界面
 */
public class CurrencySearchActivity extends BaseActivity {
    @Bind(R.id.cetSearch)
    ClearEditText cetSearch;
    @Bind(R.id.tvCancel)
    TextView tvCancel;
    @Bind(R.id.lv_search_result)
    RecyclerView lvSearchResult;
    @Bind(R.id.tvSearchFail)
    TextView tvSearchFail;
    private List<CurrencyModel.BodyBean.CurrencyListBean> mAllLists;
    private List<CurrencyModel.BodyBean.CurrencyListBean> mSearchResult;
    AdapterCurrency mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencysearch);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mAllLists = intent.getParcelableArrayListExtra("mAllLists");
        LogUtils.logd("mAllLists.size():" + mAllLists.size());

        mSearchResult = new ArrayList<>();
        mAdapter = new AdapterCurrency(R.layout.currency_item, null);
        lvSearchResult.setLayoutManager(new LinearLayoutManager(this));
        lvSearchResult.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("info", mSearchResult.get(position).getCurrencyNo());//currencyNo
                LogUtils.logd("搜索的："+mSearchResult.get(position).getCurrencyNo());
                setResult(RESULT_OK, intent);
                ActivityUtils.finishActivity(mActivity);
            }
        });

        cetSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String key = s.toString();
                if (TextUtils.isEmpty(key)) {
//                    clearBtn.setVisibility(View.GONE);
//                    emptyView.setVisibility(View.GONE);
//                    mResultListView.setVisibility(View.GONE);
                    tvSearchFail.setVisibility(View.GONE);
                    lvSearchResult.setVisibility(View.GONE);
                } else if(key != null && key.length() > 0) {
                    listFilter(s.toString());
                }
            }
        });

        //一进入显示软键盘
        cetSearch.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImeUtil.showSoftKeyboard(cetSearch);
            }
        },400);

    }

    void listFilter(String key){
        mSearchResult.clear();
        if(mAllLists != null && mAllLists.size() > 0){
            int size = mAllLists.size();
            for (int i = 0; i < size; i++) {
                if(mAllLists.get(i).getCurrencyName().contains(key) || mAllLists.get(i).getCurrencyNo().contains(key)){
                    mSearchResult.add(mAllLists.get(i));
                }
            }
            LogUtils.logd("mSearchResult:"+mSearchResult.size());
            if(mSearchResult.size() > 0){
                mAdapter.setKey(key);
                lvSearchResult.setVisibility(View.VISIBLE);
                tvSearchFail.setVisibility(View.GONE);
                mAdapter.setNewData(mSearchResult);
            }else{
                lvSearchResult.setVisibility(View.GONE);
                tvSearchFail.setVisibility(View.VISIBLE);
                tvSearchFail.setText("没找到\""+key+"\"相关结果");
            }
        }
    }

    @OnClick(R.id.tvCancel)
    public void onViewClicked() {
        ActivityUtils.finishActivity(mActivity);
        ImeUtil.hideSoftKeyboard(cetSearch);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onPageStart(mActivity, "货币搜索页面");
    }

    @Override
    protected void onPause() {
        super.onPause();
        ImeUtil.hideSoftKeyboard(cetSearch);
        StatService.onPageEnd(mActivity, "货币搜索页面");
    }
}
