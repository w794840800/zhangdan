package com.beidou.ybz.accountbook.adapter;

import android.text.TextUtils;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.StockListModel;
import com.beidou.ybz.accountbook.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 首页-资产列表adapter
 */
public class AdapterStock extends BaseQuickAdapter<StockListModel.BodyBean.PositionListBean,BaseViewHolder> {//AccountIndexModel.BodyBean.FieldViewAmountListBean
    String dqsz,zde,zdf;
    String zrsy,zdf2;//取小数点前两位后结果
    String updateDate;
    public AdapterStock(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StockListModel.BodyBean.PositionListBean item) {


        try {
            dqsz = item.getDqsz();
            zde = item.getZde();
            zdf = item.getZdf();//String.valueOf(Double.parseDouble(item.getZdf()) * 100);

            updateDate = item.getJyrq();

            zrsy = Utils.addCommaContainsPoint(zde);
            zdf2 = Utils.addCommaContainsPoint(zdf);


            if(updateDate == null || TextUtils.isEmpty(updateDate)){
                updateDate = "--";
            }else{
                if(updateDate.length() == 8){//20171229
                    updateDate = updateDate.substring(4,6)+"-"+updateDate.substring(6);
                }else{
                    updateDate = "--";
                }
            }

            helper.setText(R.id.tvUpdateDate,"("+updateDate+")");//净值日期

            helper.setText(R.id.tv_title,item.getStockName());
            helper.setText(R.id.tv_stockCode,item.getStockCode());
            helper.setText(R.id.tvDqsz, Utils.addCommaContainsPoint(dqsz));//当前市值
//            helper.setText(R.id.tvZde,Utils.addCommaContainsPoint(zde));
//            helper.setText(R.id.tvZdf,Utils.addCommaContainsPoint(zdf)+"%");

            if(zde != null && zde.startsWith("-")){
                helper.setText(R.id.tvZde,"-"+Utils.addCommaContainsPoint(zde.substring(1)));
                ((TextView)helper.getView(R.id.tvZde)).setTextColor(mContext.getResources().getColor(R.color.stock_green));
            }else if(zrsy != null && Double.parseDouble(zrsy) == 0){
                helper.setText(R.id.tvZde,Utils.addCommaContainsPoint(zde));
                ((TextView)helper.getView(R.id.tvZde)).setTextColor(mContext.getResources().getColor(R.color.stock_gray));
            }else{
                helper.setText(R.id.tvZde,Utils.addCommaContainsPoint(zde));
                ((TextView)helper.getView(R.id.tvZde)).setTextColor(mContext.getResources().getColor(R.color.stock_red));
            }


            if(zdf != null && zdf.startsWith("-")){
                helper.setText(R.id.tvZdf,"-"+Utils.addCommaContainsPoint(zdf.substring(1))+"%");
                ((TextView)helper.getView(R.id.tvZdf)).setTextColor(mContext.getResources().getColor(R.color.stock_green));
            }else if(zdf2 != null && Double.parseDouble(zdf2) == 0){
                helper.setText(R.id.tvZdf,Utils.addCommaContainsPoint(zdf)+"%");
                ((TextView)helper.getView(R.id.tvZdf)).setTextColor(mContext.getResources().getColor(R.color.stock_gray));
            }else{
                helper.setText(R.id.tvZdf,Utils.addCommaContainsPoint(zdf)+"%");
                ((TextView)helper.getView(R.id.tvZdf)).setTextColor(mContext.getResources().getColor(R.color.stock_red));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



//        helper.setText(R.id.tvAmount,item.getAmount());


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
