package com.beidou.ybz.accountbook.adapter;

import android.widget.ImageView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.mvp.entity.AccountIndexModel;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 首页-热门推荐列表adapter
 */
public class AdapterRecommend extends BaseQuickAdapter<AccountIndexModel.BodyBean.ReProListBean,BaseViewHolder> {

    public AdapterRecommend(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccountIndexModel.BodyBean.ReProListBean item) {
        Glide.with(mContext).load(ApiStores.CMS_IMG_URL+item.getImageUrl())/*.crossFade()*/.into((ImageView) helper.getView(R.id.iv));

        LogUtils.logd("URl::"+ApiStores.CMS_IMG_URL+item.getProductName());

//        helper.setText(R.id.tv_title,item);
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
