package com.beidou.ybz.accountbook.adapter;

import android.view.View;
import android.widget.ImageView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.SupportBankModel;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 银行卡列表adapter
 */
public class AdapterSupportBankOther extends BaseQuickAdapter<SupportBankModel.BodyBean.BankListBean,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
        String bankType  ;
    private int mPosition = -1;
    public AdapterSupportBankOther(int layoutResId, List data) {
        super(layoutResId, data);
    }
    public void setCurrentPosition(int position){
        mPosition = position;
        notifyDataSetChanged();
    }
    @Override
    protected void convert(BaseViewHolder helper, SupportBankModel.BodyBean.BankListBean item) {
        helper.setText(R.id.tvBankName,item.getBankName());

        Glide.with(mContext).load(ApiStores.supportbankPrefix+item.getBankIcon())/*.crossFade()*/.into((ImageView) helper.getView(R.id.ivBankIcon));

        if(mPosition == helper.getLayoutPosition()){
            helper.getView(R.id.ivSelected).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.ivSelected).setVisibility(View.INVISIBLE);
        }

//        helper.setText(R.id.tvAmount,item.get());

//        String type = item.getBankType();
//        if(type != null && type.equals("2")){
//            bankType = "信用卡";
//            helper.getView(R.id.tvAmount).setVisibility(View.VISIBLE);
//        }else{
//            bankType = "借记卡";
//            helper.getView(R.id.tvAmount).setVisibility(View.INVISIBLE);
//        }

//        helper.setText(R.id.tvCreditCard,bankType);
//        helper.setText(R.id.tvAmount,item.getAmount());

    }
}
