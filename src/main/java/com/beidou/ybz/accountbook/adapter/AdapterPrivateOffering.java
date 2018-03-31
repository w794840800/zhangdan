package com.beidou.ybz.accountbook.adapter;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.CustomListModel;
import com.beidou.ybz.accountbook.mvp.entity.PrivateOfferingModel;
import com.beidou.ybz.accountbook.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 私募基金adapter
 */
public class AdapterPrivateOffering extends BaseQuickAdapter<PrivateOfferingModel.BodyBean.PositionListBean,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
    public AdapterPrivateOffering(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PrivateOfferingModel.BodyBean.PositionListBean item) {
        try {
            helper.setText(R.id.tvName,item.getFundName());
            helper.setText(R.id.tvRmbAmount, Utils.addCommaContainsPoint(item.getDqsz()));


        } catch (Exception e) {
            e.printStackTrace();
        }
//        helper.setText(R.id.tvAmount,item.getAmount());

    }
}
