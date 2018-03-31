package com.beidou.ybz.accountbook.adapter;

import android.view.View;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.ArrearsModel;
import com.beidou.ybz.accountbook.mvp.entity.PrepaidListModel;
import com.beidou.ybz.accountbook.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 预付卡adapter
 */
public class AdapterPrepaid extends BaseQuickAdapter<PrepaidListModel.BodyBean.ProListBean,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
    public AdapterPrepaid(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PrepaidListModel.BodyBean.ProListBean item) {
        try {
            helper.setText(R.id.tvName,item.getComment());
            helper.setText(R.id.tvRmbAmount, Utils.addCommaContainsPoint(item.getAmount()));


        } catch (Exception e) {
            e.printStackTrace();
        }
//        helper.setText(R.id.tvAmount,item.getAmount());

    }
}
