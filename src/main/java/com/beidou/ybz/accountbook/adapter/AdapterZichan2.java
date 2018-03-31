package com.beidou.ybz.accountbook.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AccountIndexModel;
import com.beidou.ybz.accountbook.ui.BaseApplication;
import com.beidou.ybz.accountbook.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 首页-资产列表adapter 用户未添加资产时 -示例资产- 页面
 */
public class AdapterZichan2 extends BaseQuickAdapter<AccountIndexModel.BodyBean.FieldViewdelListBean,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
    private String nameValue;
    private double nh7r;
    public AdapterZichan2(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccountIndexModel.BodyBean.FieldViewdelListBean item) {
        try {
            nameValue = item.getNameValue();
            helper.setText(R.id.tv_title,item.getNameView())
           .setText(R.id.tv_syrs,item.getSyrs()+"人已添加")
            .addOnClickListener(R.id.llCaufubao);//为财富宝这一行添加点击事件

            ((ImageView)helper.getView(R.id.iv)).setImageResource(BaseApplication.getClassifiIcon(item.getNameValue()));

            if(nameValue != null && nameValue.equals("0003")){//活期理财
                helper.getView(R.id.llCaufubao).setVisibility(View.VISIBLE);
                helper.getView(R.id.viewCaifubao).setVisibility(View.VISIBLE);
            }else{
                helper.getView(R.id.llCaufubao).setVisibility(View.GONE);
                helper.getView(R.id.viewCaifubao).setVisibility(View.GONE);
            }
            helper.setText(R.id.tvNh7r,Utils.addCommaContainsPoint(nh7r*100+"")+"%");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setNh7r(String nh7r){
        if(nh7r != null && Utils.isNumeric(nh7r)){
            this.nh7r = Double.parseDouble(nh7r);
        }
        notifyDataSetChanged();
    }
}
