package com.beidou.ybz.accountbook.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.other.MvpFragment;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.ui.AddGame;
import com.beidou.ybz.accountbook.ui.AddInternetAccount;
import com.beidou.ybz.accountbook.ui.BaseApplication;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.adapter.AdapterLicai;
import com.beidou.ybz.accountbook.mvp.entity.AddAssetsModel;
import com.beidou.ybz.accountbook.mvp.entity.AssetClassModel;
import com.beidou.ybz.accountbook.mvp.entity.IndexModel;
import com.beidou.ybz.accountbook.ui.AddCustom;
import com.beidou.ybz.accountbook.ui.AddDomin;
import com.beidou.ybz.accountbook.widget.EmptyView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Author: Bob on 2017/12/5
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:添加资产 -> 生活财务fragment
 */
public class FinanceFragment extends BaseFragment {//MvpFragment<CommonPresenter> implements CommonView<IndexModel> {
    @Bind(R.id.bob_empty_view)
    EmptyView mEmptyView;
    @Bind(R.id.rvLicai)
    RecyclerView rvLicai;
    private AdapterLicai mAdapter;
    private List<AddAssetsModel> addAssetsModelList;
    private ArrayList<AssetClassModel.BodyBean.ShcwListBean> shcwListBeanList;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_licai;
    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if (bundle != null) {
            shcwListBeanList = bundle.getParcelableArrayList("shcwListBeanList");
            LogUtils.logd("shcwListBeanList:" + (shcwListBeanList == null));
        }
        mEmptyView.bindView(rvLicai); // 设置bindView
        mEmptyView.buttonClick(this, "request"); // 当button点击时调用哪个方法
        addAssetsModelList = new ArrayList<>();
        LogUtils.logd("shcwListBeanList:" + shcwListBeanList.size());
        if (shcwListBeanList != null && shcwListBeanList.size() > 0) {
            mEmptyView.success();
            for (int i = 0; i < shcwListBeanList.size(); i++) {
                addAssetsModelList.add(new AddAssetsModel(shcwListBeanList.get(i).getAmount(),
                        shcwListBeanList.get(i).getNameView(),
                        shcwListBeanList.get(i).getSyrs(),
                        BaseApplication.getInstance().getClassifiBackground(shcwListBeanList.get(i).getNameValue())
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
                switch (shcwListBeanList.get(position).getNameValue()) {
                    case "0015":
                        StatService.onEvent(mActivity, "点击添加列表账号资产", "[开始添加账号]", 1);
                        in = new Intent(mActivity, AddInternetAccount.class);//添加互联网账号
                        break;
                    case "0016":
                        StatService.onEvent(mActivity, "点击添加列表游戏资产", "[开始添加游戏]", 1);
                        in = new Intent(mActivity, AddGame.class);//添加游戏
                        break;
                    case "0014":
                        StatService.onEvent(mActivity, "点击添加列表域名资产", "[开始添加域名]", 1);
                        in = new Intent(mActivity, AddDomin.class);//添加域名
                        break;
                    case "9999":
                        StatService.onEvent(mActivity, "点击添加列表其他资产", "[开始添加其他]", 1);
                        in = new Intent(mActivity, AddCustom.class);//添加自定义
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
        StatService.onPageStart(mActivity, "添加资产-生活财务tab");
    }

    @Override
    public void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity, "添加资产-生活财务tab");
    }
}
