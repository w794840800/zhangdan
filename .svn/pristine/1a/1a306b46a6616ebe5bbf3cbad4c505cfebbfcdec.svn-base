package com.beidou.ybz.accountbook.widget.sidebar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.EncryptedResponseModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.retrofit.AppClient;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.SharePreferenceUtil;
import com.beidou.ybz.accountbook.util.Utils;
import com.google.gson.Gson;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author http://blog.csdn.net/finddreams
 * @Description:用来处理集合中数据的显示与排序
 */
public class SortAdapter extends BaseAdapter implements SectionIndexer {
    private List<SortModel> list = null;
    private Context mContext;
    SharePreferenceUtil spUtils;

    public SortAdapter(Context mContext, List<SortModel> list) {
        spUtils = new SharePreferenceUtil(mContext, "xinliangbao");
        this.mContext = mContext;
        this.list = list;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<SortModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final SortModel mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.phone_constacts_item, null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
//            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            viewHolder.inviate = (LinearLayout) view.findViewById(R.id.tvInviate);
//			viewHolder.icon = (ImageTextView) view.findViewById(R.id.icon);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现

        viewHolder.tvTitle.setText(list.get(position).getName().split("&")[0]);
//		viewHolder.icon.setText(this.list.get(position).getName());
//		viewHolder.icon.setIconText(mContext,this.list.get(position).getName());
        viewHolder.inviate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.loge("-------哈哈-------" + list.get(position).getName());
//                Toast.makeText(mContext,list.get(position).getSortLetters(),Toast.LENGTH_SHORT).show();
                if (list.get(position).getName().contains("&")) {
                    if (Utils.isPhone(list.get(position).getName().split("&")[1].replaceAll(" ", ""))) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri
                                .parse("smsto:" + list.get(position).getName().split("&")[1]));
                        // 如果需要将内容传过去增加如下代码
                        intent.putExtra("sms_body", "圣诞节快乐");
                        mContext.startActivity(intent);
                        invited();
                    } else {
//                        Toast.makeText(mContext, "手机号无效", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "无手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    /**
     * 人脉徽章埋点
     */
    private String encMsg,signMsg;
    private void invited() {

    }


    final static class ViewHolder {
        TextView tvTitle;
        //		ImageTextView icon;
        LinearLayout inviate;
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}