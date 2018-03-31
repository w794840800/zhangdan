package com.beidou.ybz.accountbook.adapter;

import android.view.View;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.ArrearsModel;
import com.beidou.ybz.accountbook.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 欠款adapter
 */
public class AdapterArrears extends BaseQuickAdapter<ArrearsModel.BodyBean.ProListBean,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
        String arrearsStatus;
    public AdapterArrears(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArrearsModel.BodyBean.ProListBean item) {
        arrearsStatus  = item.getArrearsStatus();
        try {
            helper.setText(R.id.tvName,item.getArrearsName());
            helper.setText(R.id.tvAmount, Utils.addCommaContainsPoint(item.getAmount()));
            helper.setText(R.id.tvHasReturn,"已还清");
        if(arrearsStatus != null && arrearsStatus.equals("1")){//已还清
            helper.getView(R.id.tvHasReturn).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.tvHasReturn).setVisibility(View.GONE);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        helper.setText(R.id.tvAmount,item.getAmount());

    }
}
