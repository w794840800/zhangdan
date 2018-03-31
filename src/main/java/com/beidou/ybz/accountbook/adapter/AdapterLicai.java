package com.beidou.ybz.accountbook.adapter;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddAssetsModel;
import com.beidou.ybz.accountbook.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by xu.yang on 2017/9/7.
 * 添加资产-列表 adapter
 */
public class AdapterLicai extends BaseQuickAdapter<AddAssetsModel, BaseViewHolder> {
    public AdapterLicai(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final AddAssetsModel item) {
        helper.setText(R.id.tv_title,item.getNameView());//tvTianjia
//        helper.setText(R.id.tvAmount,item.getAmount());
        try {
            helper.setText(R.id.tvTianjia, Utils.numberFormat(item.getSyrs())+"人已添加");
        } catch (Exception e) {
            e.printStackTrace();
        }
        helper.getView(R.id.llAll).setBackgroundResource(item.getImageResource());
    }
}
