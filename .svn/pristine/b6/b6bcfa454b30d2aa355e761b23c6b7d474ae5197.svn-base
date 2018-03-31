package com.beidou.ybz.accountbook.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.HuodongListModel;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author: ${Supreme} on 2018/1/2
 * QQ:977594142
 * E-mail:977594142@qq.com
 * module:
 */

public class HuodongListAdapter extends BaseQuickAdapter<HuodongListModel.BodyBean.RowsBean, BaseViewHolder>{

    public HuodongListAdapter(int layoutResId, @Nullable List<HuodongListModel.BodyBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HuodongListModel.BodyBean.RowsBean rowsBean) {
        Glide.with(mContext)
                .load(ApiStores.CMS_IMG_URL+rowsBean.getImage())
                .into(((ImageView) baseViewHolder.getView(R.id.img)));
    }

}
