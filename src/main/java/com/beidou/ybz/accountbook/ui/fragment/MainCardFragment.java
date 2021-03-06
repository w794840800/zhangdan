package com.beidou.ybz.accountbook.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.HuizhangBigClassModel;
import com.beidou.ybz.accountbook.util.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页产品滑动列表
 */
public class MainCardFragment extends BaseFragment {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvCount)
    TextView tvCount;
    @Bind(R.id.tvTotalCount)
    TextView tvTotalCount;
    private HuizhangBigClassModel.BodyBean.BadgeTypeListBean mDatas;
    private String id, title;

    @Override
    public int getLayoutRes() {
        return R.layout.huizhang_bigclass_item;
    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();//从activity传过来的Bundle

        if (bundle != null) {
            try {
                mDatas = bundle.getParcelable("data");
                if (mDatas != null) {
                    LogUtils.loge("mDatas != null");
                    String name = mDatas.getBadgeTypeName();
                    String type = mDatas.getBadgeType();
                    String num = mDatas.getGetBadgeNum();
                    String badgeSum = mDatas.getBadgeSum();
                    tvTitle.setText(name);
                    tvCount.setText(num);
                    tvTotalCount.setText("总共徽章"+badgeSum+"枚");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
