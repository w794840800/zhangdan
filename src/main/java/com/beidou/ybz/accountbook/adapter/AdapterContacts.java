package com.beidou.ybz.accountbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.ContactsModel;
import com.beidou.ybz.accountbook.widget.StickyRecyclerHeader.StickyRecyclerHeadersAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 通讯录列表adapter
 */
public class AdapterContacts extends BaseQuickAdapter<ContactsModel, BaseViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    public AdapterContacts(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactsModel item) {
        try {
            String title = null;
            if (item.getName().length()>10){
                title = item.getName().substring(0,10)+"...";
            }else {
                title = item.getName();
            }
            helper.setText(R.id.title, title).addOnClickListener(R.id.tvInviate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getInitials().charAt(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_header, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            TextView textView = (TextView) holder.itemView;
            String showValue = String.valueOf(getItem(position).getInitials().charAt(0));
            textView.setText(showValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
