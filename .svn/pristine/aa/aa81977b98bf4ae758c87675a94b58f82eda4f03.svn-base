package com.beidou.ybz.accountbook.adapter;

import android.view.View;
import android.widget.ImageView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.BankcardListModel;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 银行卡列表adapter
 */
public class AdapterBankcard extends BaseQuickAdapter<BankcardListModel.BodyBean.ProListBean,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
        String bankType,bankNo,amount;
    public AdapterBankcard(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BankcardListModel.BodyBean.ProListBean item) {
        try {
            amount = item.getAmount();
            bankNo = item.getBankNo();
            if(bankNo == null || bankNo.length() == 0){
                ;
            }else if(bankNo != null && bankNo.length() <= 4){
                bankNo = "尾号（"+bankNo+"）";
            }else if(bankNo != null && bankNo.length() >= 5){
                bankNo = "尾号（"+bankNo.substring(bankNo.length()-4)+"）";
            }


            helper.setText(R.id.tvBankName,item.getBankName()+bankNo);

            if(Utils.isNumeric(amount) && Double.parseDouble(amount) > 0){
                helper.setText(R.id.tvAmount, Utils.addCommaContainsPoint(item.getAmount()));
            }else{
                helper.setText(R.id.tvAmount, "");
            }


            Glide.with(mContext).load(ApiStores.supportbankPrefix+item.getBanklogo()).into((ImageView) helper.getView(R.id.iv));


            LogUtils.logd("银行卡："+ApiStores.supportbankPrefix+item.getBanklogo());

            String type = item.getBankType();
            if(type != null && type.equals("2")){
                bankType = "信用卡";
                helper.getView(R.id.tvAmount).setVisibility(View.VISIBLE);
            }else{
                bankType = "借记卡";
                helper.getView(R.id.tvAmount).setVisibility(View.INVISIBLE);
            }

            helper.setText(R.id.tvCreditCard,bankType);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        helper.setText(R.id.tvAmount,item.getAmount());

    }
}
