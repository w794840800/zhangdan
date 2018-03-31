package com.beidou.ybz.accountbook.adapter;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.PrepaidListModel;
import com.beidou.ybz.accountbook.mvp.entity.ReimburseListModel;
import com.beidou.ybz.accountbook.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 报销单adapter
 */
public class AdapterReimburse extends BaseQuickAdapter<ReimburseListModel.BodyBean.ProListBean,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
    public AdapterReimburse(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReimburseListModel.BodyBean.ProListBean item) {
        try {
            helper.setText(R.id.tvName,item.getArrearsName());
            helper.setText(R.id.tvRmbAmount, Utils.addCommaContainsPoint(item.getAmount()));


        } catch (Exception e) {
            e.printStackTrace();
        }
//        helper.setText(R.id.tvAmount,item.getAmount());

    }
}
