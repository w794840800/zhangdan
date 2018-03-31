package com.beidou.ybz.accountbook.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.adapter.AdapterHuizhangSecondClass;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.HuizhangSecondClassModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.other.MvpFragment;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.mvp.view.OtherView;
import com.beidou.ybz.accountbook.ui.HuiZhangDetailActivity;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author: xu.yang on 2018/1/25
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:
 */
public class BadgeSecondClassFragment extends MvpFragment<CommonPresenter> implements OtherView<HuizhangSecondClassModel> {
    ArrayList<HuizhangSecondClassModel.BodyBean.ShowListBean> showListBeanList;
    @Bind(R.id.rvSecondClass)
    RecyclerView rvSecondClass;
    AdapterHuizhangSecondClass adapterHuizhangSecondClass;
    private String badgeType;
    private String encMsg, signMsg;
    HuizhangSecondClassModel.BodyBean.ShowListBean showListBean;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_badgesecond;
    }

    @Override
    public void initView() {
        rvSecondClass.setLayoutManager(new GridLayoutManager(mActivity, 3));
        adapterHuizhangSecondClass = new AdapterHuizhangSecondClass(R.layout.huizhang_secondclass_item, null);
        rvSecondClass.setAdapter(adapterHuizhangSecondClass);
        handlerIntent();
        getSecondClass(badgeType);

        adapterHuizhangSecondClass.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showListBean = showListBeanList.get(position);
                Intent in = new Intent(mActivity, HuiZhangDetailActivity.class);
                in.putExtra("showListBean", showListBean);
                startActivity(in);
                getActivity().overridePendingTransition(R.anim.alpha_in, 0);
            }
        });
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        handlerIntent();
//        getSecondClass(badgeType);
    }

    void handlerIntent(){
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if (bundle != null) {
            badgeType = bundle.getString("badgeType");
//            showListBeanList = bundle.getParcelableArrayList("data");
//            adapterHuizhangSecondClass.setNewData(showListBeanList);
        }
        LogUtils.logd("badgeType:"+badgeType);
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(getActivity(),this);
    }

    //获取徽章二级分类
    void getSecondClass(String badgeType) {
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());
        requestModel.setBadgeType(badgeType);
        loginRequestModel.setBody(requestModel);
        loginRequestModel.setHeader(headerBean);
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(loginRequestModel);
        LogUtils.logd(json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.badgedetaillist(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }

    @Override
    public void onSuccess(HuizhangSecondClassModel model) {
        showListBeanList = (ArrayList<HuizhangSecondClassModel.BodyBean.ShowListBean>) model.getBody().getShowList();
        adapterHuizhangSecondClass.setNewData(showListBeanList);
    }

    @Override
    public void onFail(String msg) {

    }

}
