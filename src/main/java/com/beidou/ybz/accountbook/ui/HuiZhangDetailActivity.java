package com.beidou.ybz.accountbook.ui;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.HuizhangSecondClassModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class HuiZhangDetailActivity extends BaseActivity {
    @Bind(R.id.ivBack)
    ImageView ivBack;
    HuizhangSecondClassModel.BodyBean.ShowListBean showListBean;
    @Bind(R.id.ivIcon)
    ImageView ivIcon;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.tvContion)
    TextView tvContion;
    @Bind(R.id.btnShare)
    Button btnShare;
    private String badgeDetailName,content,getWay,picShowUrl,getStatus,badgeDetailId;
    private String encMsg, signMsg,from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huizhangdetail);

        ButterKnife.bind(this);
        ActivityUtils.setStatusBar(mActivity);

        initView();
        DrawerAnimation();
    }

    /** 属性动画集
     * 控件展示动画
     */
    void DrawerAnimation() {
        ObjectAnimator animatoriv1 = ObjectAnimator.ofFloat(ivIcon, "scaleX", 0f, 1.2f, 0.9f);
        ObjectAnimator animatoriv2 = ObjectAnimator.ofFloat(ivIcon, "scaleY", 0f, 1.2f, 0.9f);
        ObjectAnimator animatoriv3 = ObjectAnimator.ofFloat(ivIcon, "alpha", 0f, 1f);

        ObjectAnimator animtv1 = ObjectAnimator.ofFloat(tvName, "translationY", 80f, 0f);
        ObjectAnimator animtv11= ObjectAnimator.ofFloat(tvName, "alpha", 0f, 1f);

        ObjectAnimator animtv2 = ObjectAnimator.ofFloat(tvContent, "translationX", 150f, -20f, 0f);
        ObjectAnimator animtv22 = ObjectAnimator.ofFloat(tvContent, "alpha", 0f, 1f);

        ObjectAnimator animtv3 = ObjectAnimator.ofFloat(tvContion, "translationX", 100f, -10f, 0f);
        ObjectAnimator animtv33 = ObjectAnimator.ofFloat(tvContion, "alpha", 0f, 1f);

        ObjectAnimator animbtn1 = ObjectAnimator.ofFloat(btnShare, "translationY", 200f, 0f);
        ObjectAnimator animbtn11 = ObjectAnimator.ofFloat(btnShare, "alpha", 0f, 1f);


        animtv1.setInterpolator(new OvershootInterpolator());
        AnimatorSet animSet = new AnimatorSet();

        animSet.play(animatoriv1).with(animatoriv2).with(animatoriv3);//ivIcon先行动画

        animSet.play(animtv1).with(animtv11).after(animatoriv1);
        animSet.play(animtv2).with(animtv22).after(animtv1);
        animSet.play(animtv3).with(animtv33).after(animtv2);
        animSet.play(animbtn1).with(animbtn11).after(animtv3);
        animSet.setDuration(360);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.setStartDelay(700);
        animSet.start();

    }


    void initView() {
        Intent in = getIntent();
        showListBean = in.getParcelableExtra("showListBean");
        //badgeDetailName,content,getWay,picShowUrl,getStatus;
        badgeDetailName = showListBean.getBadgeDetailName();
        content = showListBean.getContent();
        getWay = showListBean.getGetWay();
        picShowUrl = showListBean.getPicShowUrl();
        getStatus = showListBean.getGetStatus();
        badgeDetailId = showListBean.getBadgeDetailId();

        if(getStatus != null && getStatus.equals("1")){
            btnShare.setVisibility(View.VISIBLE);
        }else{
            btnShare.setVisibility(View.GONE);
        }

        Glide.with(mActivity)
                .load(picShowUrl)
                .placeholder(R.drawable.huizhang)
                .dontAnimate()
                .into(ivIcon);

        tvName.setText(badgeDetailName);
        tvContent.setText(content);
        tvContion.setText(getWay);
    }

    @OnClick({R.id.ivBack, R.id.btnShare})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                overridePendingTransition(0, R.anim.alpha_out);
                break;
            case R.id.btnShare:
                RxPermissions.getInstance(HuiZhangDetailActivity.this)
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)//这里填写所需要的权限
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {
                                   requestUrl(badgeDetailId);
                                } else {
                                }
                            }
                        });

//                if (isgo){
//                    requestUrl(badgeDetailId);
//                }else {
//                    permissions();
//                }
                break;
        }
    }

    private boolean isgo = false;
    private void permissions() {
        RxPermissions.getInstance(HuiZhangDetailActivity.this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)//这里填写所需要的权限
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
//                                    toastShow("成功");
                            isgo = true;
                        } else {
                            isgo = false;
                        }
                    }
                });
    }

    void requestUrl(String id) {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();//10051547
        requestModel.setUserNo(spUtils.getUserId());//spUtils.getUserId()
        requestModel.setBadgeDetailId(id);

        requestBody.setBody(requestModel);
        requestBody.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(requestBody);
        LogUtils.loge("请求参数：" + json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = ApiStores.CAISHANG_URL+"badge/share.htm"+"?encMsg="+encMsg+"&signMsg="+signMsg+"&msgType=2"+"&secretKeyId="+spUtils.getSecretKeyId();
        LogUtils.loge("请求url：" + url);
        // requestUrl(id,stockCode,stockName,positionNumber,brokerName,memo);from
//        Intent in = new Intent(this,CourseShareActivity.class);
        Intent in = new Intent(this,HuizhangShareActivity.class);
        in.putExtra("url",url);
        startActivity(in);
        overridePendingTransition(R.anim.left_in, 0);
    }


    @Override
    protected void onResume() {
        super.onResume();
        StatService.onPageStart(mActivity,"徽章详情页面");
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity,"徽章详情页面");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
            overridePendingTransition(0, R.anim.alpha_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
