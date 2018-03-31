package com.beidou.ybz.accountbook.adapter;

import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.OperationRecordModel;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 活期理财记录列表adapter
 */
public class AdapterOperationRecord extends BaseQuickAdapter<OperationRecordModel.BodyBean.TzlcListBean,BaseViewHolder> {
    private String amount;
    public AdapterOperationRecord(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OperationRecordModel.BodyBean.TzlcListBean item) {
        amount = item.getAmount();

        if(amount != null && amount.startsWith("-")){
            ((TextView)helper.getView(R.id.tvRmbAmount)).setTextColor(mContext.getResources().getColor(R.color.stock_green));
        }else{
            ((TextView)helper.getView(R.id.tvRmbAmount)).setTextColor(mContext.getResources().getColor(R.color.stock_red));
        }

        helper.setText(R.id.tvName, TimeUtils.getStrTime3(item.getCreateTime().getTime()));
        try {
            helper.setText(R.id.tvRmbAmount, Utils.addCommaContainsPoint(item.getAmount())+"元");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ((TextView)helper.getView(R.id.tvName)).setTextColor(mContext.getResources().getColor(R.color.stock_gray));
//        helper.setText(R.id.tvAmount,item.getAmount());


//        // 加载网络图片
//        Glide.with(mContext).load(item.getThumbnail())/*.crossFade()*/.into((ImageView) helper.getView(R.id.iv));
//        Glide.with(mContext).load(item.getInsuranceLogo())/*.crossFade()*/.into((ImageView) helper.getView(R.id.iv2));
//        final String id = item.getId();
//        helper.getView(R.id.ll).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in = new Intent(mContext, ProductDetailActivity.class);
//                in.putExtra("id",id);
//                mContext.startActivity(in);
//                ((Activity)mContext).overridePendingTransition(R.anim.left_in, 0);
//            }
//        });
    }
}
