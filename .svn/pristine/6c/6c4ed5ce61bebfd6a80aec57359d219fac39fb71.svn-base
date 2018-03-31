package com.beidou.ybz.accountbook.adapter;

import android.widget.ImageView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.CurrentFinancialModel;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 活期理财分类adapter
 */
public class AdapterCurrentClass extends BaseQuickAdapter<CurrentFinancialModel,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
    private int mPosition;
    public AdapterCurrentClass(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public void setCurrentPosition(int position){
        this.mPosition = position;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, CurrentFinancialModel item) {
        try {
            helper.setText(R.id.tvName,item.getName());
            Glide.with(mContext).load(item.getResourceId()).into((ImageView) helper.getView(R.id.iv));
//            ((ImageView) helper.getView(R.id.iv)).setImageResource(item.getResourceId());


            if(mPosition == helper.getLayoutPosition()){
                helper.getView(R.id.llZhifubao).setBackgroundResource(R.drawable.bgcurrent_en);
            }else{
                helper.getView(R.id.llZhifubao).setBackgroundResource(R.drawable.bgcurrent_un);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
