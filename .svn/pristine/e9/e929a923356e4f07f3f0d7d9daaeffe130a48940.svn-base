package com.beidou.ybz.accountbook.adapter;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.GameListModel;
import com.beidou.ybz.accountbook.mvp.entity.PrepaidListModel;
import com.beidou.ybz.accountbook.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 游戏adapter
 */
public class AdapterGame extends BaseQuickAdapter<GameListModel.BodyBean.ProListBean,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
    public AdapterGame(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GameListModel.BodyBean.ProListBean item) {
        try {
            helper.setText(R.id.tvName,item.getGameName());
            helper.setText(R.id.tvRmbAmount, Utils.addCommaContainsPoint(item.getAmount()));


        } catch (Exception e) {
            e.printStackTrace();
        }
//        helper.setText(R.id.tvAmount,item.getAmount());

    }
}
