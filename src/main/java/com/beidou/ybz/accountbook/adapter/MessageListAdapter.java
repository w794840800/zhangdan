package com.beidou.ybz.accountbook.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.MessageListModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author: ${Supreme} on 2018/1/2
 * QQ:977594142
 * E-mail:977594142@qq.com
 * module:
 */

public class MessageListAdapter extends BaseQuickAdapter<MessageListModel.BodyBean.RowsBean, BaseViewHolder> {

    public MessageListAdapter(int layoutResId, @Nullable List<MessageListModel.BodyBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final MessageListModel.BodyBean.RowsBean rowsBean) {
        if (rowsBean != null){
            if (rowsBean.getTitle().length()>10){
                ((TextView) baseViewHolder.getView(R.id.title)).setText(rowsBean.getTitle().substring(0,10)+"...");
            }else {
                ((TextView) baseViewHolder.getView(R.id.title)).setText(rowsBean.getTitle());
            }
            ((TextView) baseViewHolder.getView(R.id.content)).setText(rowsBean.getContent());
            ((TextView) baseViewHolder.getView(R.id.date)).setText(rowsBean.getReleaseTimeStr());
            String type = String.valueOf(rowsBean.getMsgType());
            switch (type){
                //（1系统消息；2活动消息）
                case "1":
                    ((TextView) baseViewHolder.getView(R.id.text)).setText("系统公告");
                    break;
                case "2":
                    ((TextView) baseViewHolder.getView(R.id.text)).setText("活动通知");
                    break;
            }
        }
    }
}
