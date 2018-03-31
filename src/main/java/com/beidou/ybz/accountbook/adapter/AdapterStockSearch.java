package com.beidou.ybz.accountbook.adapter;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.StockSearchModel;
import com.beidou.ybz.accountbook.util.StringFormatUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 银行卡列表adapter
 */
public class AdapterStockSearch extends BaseQuickAdapter<StockSearchModel.ResultBean, BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
    String bankType, mKey;

    public AdapterStockSearch(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public void setKey(String key) {
        mKey = key;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, StockSearchModel.ResultBean item) {
        helper.setText(R.id.tvName, item.getCode());
        helper.setText(R.id.tvRmbAmount, item.getName());

        StringFormatUtil spanCode = new StringFormatUtil(mContext, item.getCode(),
                mKey, R.color.colorGold).fillColor();
        if (spanCode != null) {
            helper.setText(R.id.tvName, spanCode.getResult());
        } else {
            helper.setText(R.id.tvName, item.getCode());
        }

        StringFormatUtil spanName = new StringFormatUtil(mContext, item.getName(),
                mKey, R.color.colorGold).fillColor();
        if (spanName != null) {
            helper.setText(R.id.tvRmbAmount, spanName.getResult());
        } else {
            helper.setText(R.id.tvRmbAmount, item.getName());
        }


    }
}
