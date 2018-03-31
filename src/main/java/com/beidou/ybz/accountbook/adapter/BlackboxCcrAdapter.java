package com.beidou.ybz.accountbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.GetHjzModel;

import java.util.List;

/**
 * Author: ${Supreme} on 2017/12/29
 * QQ:977594142
 * E-mail:977594142@qq.com
 * module:
 */

public class BlackboxCcrAdapter extends RecyclerView.Adapter<BlackboxCcrAdapter.MyViewHolder>{

    private List<GetHjzModel.BodyBean.ProListBean> proListBeans;
    private Context context;

    public BlackboxCcrAdapter(Context context,List<GetHjzModel.BodyBean.ProListBean> proListBeans) {
        this.context = context;
        this.proListBeans = proListBeans;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(proListBeans.get(position).getMobile());
    }

    @Override
    public int getItemCount() {
        return proListBeans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.phone);
        }
    }
}
