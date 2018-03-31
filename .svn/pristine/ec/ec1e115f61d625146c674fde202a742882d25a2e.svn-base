package com.beidou.ybz.accountbook.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.adapter.AdapterLicai;
import com.beidou.ybz.accountbook.mvp.entity.AddAssetsModel;
import com.beidou.ybz.accountbook.mvp.entity.AssetClassModel;
import com.beidou.ybz.accountbook.mvp.entity.IndexModel;
import com.beidou.ybz.accountbook.mvp.other.MvpFragment;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.ui.AddBankCard;
import com.beidou.ybz.accountbook.ui.AddPrepaidCard;
import com.beidou.ybz.accountbook.ui.BaseApplication;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.widget.EmptyView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Author: Bob on 2017/12/4
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:添加资产 -> 资金账户fragment
 */
public class ZijinFragment extends BaseFragment {
    @Bind(R.id.bob_empty_view)
    EmptyView mEmptyView;
    @Bind(R.id.rvLicai)
    RecyclerView rvLicai;
    private AdapterLicai mAdapter;
    private List<AddAssetsModel> addAssetsModelList;
    private ArrayList<AssetClassModel.BodyBean.ZjzhListBean> zjzhListBeanList;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_licai;
    }

    @Override
    public void initView() {

        Bundle bundle = getArguments();//从activity传过来的Bundle
        if (bundle != null) {
            zjzhListBeanList = bundle.getParcelableArrayList("zjzhListBeanList");
            LogUtils.logd("zjzhListBeanList:"+(zjzhListBeanList == null));
        }
        mEmptyView.bindView(rvLicai); // 设置bindView
        mEmptyView.buttonClick(this, "request"); // 当button点击时调用哪个方法
        addAssetsModelList = new ArrayList<>();

        if(zjzhListBeanList != null && zjzhListBeanList.size() > 0) {
            mEmptyView.success();
            for (int i = 0; i < zjzhListBeanList.size(); i++) {
                addAssetsModelList.add(new AddAssetsModel(zjzhListBeanList.get(i).getAmount(),
                        zjzhListBeanList.get(i).getNameView(),
                        zjzhListBeanList.get(i).getSyrs(),
                        BaseApplication.getInstance().getClassifiBackground(zjzhListBeanList.get(i).getNameValue())
                        ));
            }
        }else{
            mEmptyView.setEmptyText("该资产分类已全部添加～");
            mEmptyView.assetsAddAll();
        }

//        addAssetsModelList.add(new AddAssetsModel("银行卡",R.drawable.bankcard));
//        addAssetsModelList.add(new AddAssetsModel("预付卡",R.drawable.prepaidcard));
        rvLicai.setLayoutManager(new GridLayoutManager(mActivity,2));
        mAdapter = new AdapterLicai(R.layout.addzichan_licai_item, addAssetsModelList);
        rvLicai.setAdapter(mAdapter);

       mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               Intent in = null;
               switch (zjzhListBeanList.get(position).getNameValue()){
                    case "0004":
                        StatService.onEvent(mActivity, "点击添加列表银行卡资产", "[开始添加银行卡]",1);
                        in = new Intent(mActivity, AddBankCard.class);//添加银行卡
                        break;
                    case "0005":
                        StatService.onEvent(mActivity, "点击添加列表预付卡资产", "[开始添加预付卡]",1);
                        in = new Intent(mActivity, AddPrepaidCard.class);//添加预付卡
                        break;
                }
                in.putExtra("from","addasset");//来自首次添加页面，便于后面做事件埋点区分
                mActivity.startActivity(in);
                mActivity.overridePendingTransition(R.anim.left_in, 0);
           }
       });


    }


    @Override
    public void onResume() {
        super.onResume();
        StatService.onPageStart(mActivity, "添加资产-资金账户tab");
    }

    @Override
    public void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity, "添加资产-资金账户tab");
    }

}
