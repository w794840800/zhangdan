package com.beidou.ybz.accountbook.adapter;


import android.widget.ImageView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.ui.BaseApplication;
import com.beidou.ybz.accountbook.mvp.entity.AccountIndexModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 首页-资产列表adapter 用户添加数据后
 */
public class AdapterZichan extends BaseQuickAdapter<AccountIndexModel.BodyBean.FieldViewAmountListBean,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
    public AdapterZichan(int layoutResId, List data) {
        super(layoutResId, data);
    }
    private BaseViewHolder viewholder;

    @Override
    protected void convert(BaseViewHolder helper, AccountIndexModel.BodyBean.FieldViewAmountListBean item) {
        try {
            helper.setText(R.id.tv_title,item.getNameView());
            helper.setText(R.id.tvAmount, item.getAmount());//
            ((ImageView)helper.getView(R.id.iv)).setImageResource(BaseApplication.getClassifiIcon(item.getNameValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
