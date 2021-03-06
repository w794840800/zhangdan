package com.beidou.ybz.accountbook.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddAssetsModel;
import com.beidou.ybz.accountbook.mvp.entity.AssetClassModel;
import com.beidou.ybz.accountbook.mvp.entity.IndexModel;
import com.beidou.ybz.accountbook.mvp.other.MvpFragment;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.ui.AddCredit;
import com.beidou.ybz.accountbook.ui.AddReimbursement;
import com.beidou.ybz.accountbook.ui.BaseApplication;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.adapter.AdapterLicai;
import com.beidou.ybz.accountbook.ui.AddArrears;
import com.beidou.ybz.accountbook.ui.AddLoan;
import com.beidou.ybz.accountbook.widget.EmptyView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Author: Bob on 2017/12/5
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:添加资产 -> 应收应付fragment
 */
public class PaymentFragment extends BaseFragment {
    @Bind(R.id.bob_empty_view)
    EmptyView mEmptyView;
    @Bind(R.id.rvLicai)
    RecyclerView rvLicai;
    private AdapterLicai mAdapter;
    private List<AddAssetsModel> addAssetsModelList;
    private ArrayList<AssetClassModel.BodyBean.ZwwlListBean> zwwlListBeanList;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_licai;
    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if (bundle != null) {
            zwwlListBeanList = bundle.getParcelableArrayList("zwwlListBeanList");
            LogUtils.logd("zwwlListBeanList:" + (zwwlListBeanList == null));
        }
        mEmptyView.bindView(rvLicai); // 设置bindView
        mEmptyView.buttonClick(this, "request"); // 当button点击时调用哪个方法
        addAssetsModelList = new ArrayList<>();
        if (zwwlListBeanList != null && zwwlListBeanList.size() > 0) {
            mEmptyView.success();
            for (int i = 0; i < zwwlListBeanList.size(); i++) {
                addAssetsModelList.add(new AddAssetsModel(zwwlListBeanList.get(i).getAmount(),
                        zwwlListBeanList.get(i).getNameView(),
                        zwwlListBeanList.get(i).getSyrs(),
                        BaseApplication.getInstance().getClassifiBackground(zwwlListBeanList.get(i).getNameValue())
                ));
            }
        } else {
            mEmptyView.setEmptyText("该资产分类已全部添加～");
            mEmptyView.assetsAddAll();
        }

        rvLicai.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mAdapter = new AdapterLicai(R.layout.addzichan_licai_item, addAssetsModelList);
        rvLicai.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent in = null;
                switch (zwwlListBeanList.get(position).getNameValue()) {
                    case "0017":
                        StatService.onEvent(mActivity, "点击添加列表借款资产", "[开始添加借款]", 1);
                        in = new Intent(mActivity, AddLoan.class);//添加借款
                        break;
                    case "0006":
                        StatService.onEvent(mActivity, "点击添加列表欠款资产", "[开始添加欠款]", 1);
                        in = new Intent(mActivity, AddArrears.class);//添加欠款
                        break;
                    case "0013":
                        StatService.onEvent(mActivity, "点击添加列表报销资产", "[开始添加报销]", 1);
                        in = new Intent(mActivity, AddReimbursement.class);//添加报销
                        break;
                    case "0007":
                        StatService.onEvent(mActivity, "点击添加列表贷款资产", "[开始添加贷款]", 1);
                        in = new Intent(mActivity, AddCredit.class);//添加贷款
                        break;
                }
                in.putExtra("from", "addasset");//来自首次添加页面，便于后面做事件埋点区分
                mActivity.startActivity(in);
                mActivity.overridePendingTransition(R.anim.left_in, 0);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        StatService.onPageStart(mActivity, "添加资产-应收应付tab");
    }

    @Override
    public void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity, "添加资产-应收应付tab");
    }

}
