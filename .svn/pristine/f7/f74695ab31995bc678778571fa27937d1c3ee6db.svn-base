package com.beidou.ybz.accountbook.adapter;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.CreditModel;
import com.beidou.ybz.accountbook.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 贷款列表adapter
 */
public class AdapterCredit extends BaseQuickAdapter<CreditModel.BodyBean.ProListBean,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean

    public AdapterCredit(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CreditModel.BodyBean.ProListBean item) {
        try {
            helper.setText(R.id.tvName,item.getArrearsName());
            helper.setText(R.id.tvRmbAmount, Utils.addCommaContainsPoint(item.getAmount()));
        } catch (Exception e) {
            e.printStackTrace();
        }


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
