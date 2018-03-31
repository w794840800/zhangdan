package com.beidou.ybz.accountbook.adapter;

import android.view.View;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.ArrearsModel;
import com.beidou.ybz.accountbook.mvp.entity.LoanlistModel;
import com.beidou.ybz.accountbook.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 借款adapter
 */
public class AdapterLoan extends BaseQuickAdapter<LoanlistModel.BodyBean.ProListBean,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
        String loanStatus  ;
    public AdapterLoan(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LoanlistModel.BodyBean.ProListBean item) {
        loanStatus  = item.getLoanStatus();
        try {//tvHasReturn
            helper.setText(R.id.tvName,item.getLoanName());
            helper.setText(R.id.tvAmount, Utils.addCommaContainsPoint(item.getAmount()));
            helper.setText(R.id.tvHasReturn,"已收回");
            if(loanStatus != null && loanStatus.equals("1")){//已收回
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
