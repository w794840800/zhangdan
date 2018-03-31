package com.beidou.ybz.accountbook.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.BankcardListModel;
import com.beidou.ybz.accountbook.mvp.entity.CaifuCardModel;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 财富卡列表adapter
 */
public class AdapterCaifucard extends BaseQuickAdapter<CaifuCardModel.BodyBean.CardListBean,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
        String bankType,bankNo,amount,bankName,bankIcon,dailyLimit,singleLimit,defaultBankcardFlag;
    public AdapterCaifucard(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CaifuCardModel.BodyBean.CardListBean item) {
        try {
            bankName = item.getBankName();
            bankIcon = item.getBankIcon();
            bankNo = item.getAcctNo();
            bankType = item.getCardType();
            dailyLimit = item.getDailyLimit();
            singleLimit = item.getSingleLimit();
            defaultBankcardFlag = item.getDefaultBankcardFlag();



            helper.setText(R.id.tvBankCardNo,bankNo);
            if (bankNo != null && !TextUtils.isEmpty(bankNo)) {
                if (bankNo.length() <= 4) {
                    helper.setText(R.id.tvBankCardNo,bankNo);
                } else if(bankNo.length() > 4 && bankNo.length() < 16){
                    helper.setText(R.id.tvBankCardNo,ss(Utils.intervalString(bankNo).trim()));
                }else{//>=16位
                    helper.setText(R.id.tvBankCardNo,ss2(bankNo));
                }
            } else {
                helper.setText(R.id.tvBankCardNo,"您还没有填写银行卡号哦~");
            }

            helper.setText(R.id.tvSingleLimit,singleLimit+"万");
            helper.setText(R.id.tvDailyLimit,dailyLimit+"万");

            Glide.with(mContext).load(ApiStores.supportbankPrefix + bankIcon).into((ImageView) helper.getView(R.id.ivBankIcon));

            LogUtils.logd("银行卡："+ApiStores.supportbankPrefix+bankIcon);
            if(bankType != null && bankType.equals("2")){
                bankType = "信用卡";
            }else{
                bankType = "借记卡";
            }
            helper.setText(R.id.tvCardname,bankName+bankType);
            helper.setText(R.id.tvCardType,bankType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 16位以下
     * @param input
     * @return
     */
    String ss(String input){//1222 5555 5555
        int length = input.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < length; i++) {
            if(i%5 != 0){
                sb.append("*");
            }else{
                sb.append("  ");
            }
        }
        LogUtils.logd("sb.toString():"+sb.toString()+"\ninput:"+input.substring(length-1));
        return sb.toString()+input.substring(length-1);
    }

    /**
     * 16位以上
     * @param input
     * @return
     */
    String ss2(String input){
        int length = input.length();
        return "****  ****  **** "+input.substring(length-4);
    }
}
