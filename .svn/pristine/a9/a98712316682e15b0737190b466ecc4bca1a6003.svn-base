package com.beidou.ybz.accountbook.adapter;

import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.StockRecordModel;
import com.beidou.ybz.accountbook.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 股票操作记录列表adapter
 */
public class AdapterStockOperationRecord extends BaseQuickAdapter<StockRecordModel.BodyBean.RecordListBean,BaseViewHolder> {
    private String positionNumber,positionNumberCopy;
    public AdapterStockOperationRecord(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StockRecordModel.BodyBean.RecordListBean item) {
        positionNumber  = item.getPositionNumber();
        helper.setText(R.id.tvName, TimeUtils.getStrTime3(item.getCreateTime().getTime()));

        ((TextView)helper.getView(R.id.tvName)).setTextColor(mContext.getResources().getColor(R.color.stock_gray));


        if(positionNumber != null && positionNumber.startsWith("-")){
            positionNumberCopy = positionNumber;
            ((TextView)helper.getView(R.id.tvRmbAmount)).setTextColor(mContext.getResources().getColor(R.color.stock_green));
        }else if(positionNumber != null && positionNumber.startsWith("+")){
            positionNumberCopy = positionNumber;
            ((TextView)helper.getView(R.id.tvRmbAmount)).setTextColor(mContext.getResources().getColor(R.color.stock_red));
        }else{
            positionNumberCopy = positionNumber;
            ((TextView)helper.getView(R.id.tvRmbAmount)).setTextColor(mContext.getResources().getColor(R.color.stock_red));
        }

        helper.setText(R.id.tvRmbAmount,positionNumberCopy+"股");


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
