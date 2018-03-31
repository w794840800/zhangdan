package com.beidou.ybz.accountbook.adapter;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.DominListModel;
import com.beidou.ybz.accountbook.mvp.entity.PrepaidListModel;
import com.beidou.ybz.accountbook.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 预付卡adapter
 */
public class AdapterDomin extends BaseQuickAdapter<DominListModel.BodyBean.ProListBean,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
    public AdapterDomin(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DominListModel.BodyBean.ProListBean item) {
        try {
            helper.setText(R.id.tvName,item.getHosturl());
            helper.setText(R.id.tvRmbAmount, Utils.addCommaContainsPoint(item.getAmount()));


        } catch (Exception e) {
            e.printStackTrace();
        }
//        helper.setText(R.id.tvAmount,item.getAmount());

    }
}
