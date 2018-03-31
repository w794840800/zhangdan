package com.beidou.ybz.accountbook.adapter;

import android.view.View;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.OverseasListModel;
import com.beidou.ybz.accountbook.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 海外房产列表adapter
 */
public class AdapterOverseasList extends BaseQuickAdapter<OverseasListModel.BodyBean.OverseasListBean,BaseViewHolder> {
    private String curType;
    public AdapterOverseasList(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OverseasListModel.BodyBean.OverseasListBean item) {

        try {
            curType = item.getCurType();
            if(curType != null && curType.equals("CNY")){
                helper.getView(R.id.tvAmount).setVisibility(View.GONE);
            }else{
                helper.getView(R.id.tvAmount).setVisibility(View.VISIBLE);
            }
            ((TextView)helper.getView(R.id.tvName)).setTextColor(mContext.getResources().getColor(R.color.background));
            helper.setText(R.id.tvName,item.getHomeName());
            helper.setText(R.id.tvAmount,"≈"+item.getCurType()+"\t"+ Utils.addCommaContainsPoint(item.getAmount()));
            helper.setText(R.id.tvRmbAmount,Utils.addCommaContainsPoint(item.getAmountRmb()));
        } catch (Exception e) {
            e.printStackTrace();
        }


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
