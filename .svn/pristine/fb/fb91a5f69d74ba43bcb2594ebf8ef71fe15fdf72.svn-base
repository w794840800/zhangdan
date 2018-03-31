package com.beidou.ybz.accountbook.ui;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.EncryptedResponseModel;
import com.beidou.ybz.accountbook.mvp.entity.GetHjzModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.retrofit.AppClient;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.GsonTools;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: xu.yang on 2017/12/21
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:黑匣子引导界面
 */
public class BlackboxGuideActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.set_btn)
    TextView setBtn;
    @Bind(R.id.linear)
    LinearLayout linear;
    @Bind(R.id.rel)
    RelativeLayout rel;
    @Bind(R.id.ivText)
    ImageView ivText;
    @Bind(R.id.llText)
    LinearLayout llText;
    private int height, height2;
    private ValueAnimator valueAnimatorShow, valueAnimatorHide, valueAnimatorShow1, valueAnimatorHide1;
    private boolean isMoreShow, isMoreShow1;
    private String encMsg, signMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blackbox_guide);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.finishActivity(mActivity);
            }
        });


//        LogUtils.logd("C:\codes");

        //计算llMore的高度
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        linear.measure(w, h);
        height = linear.getMeasuredHeight();
        Log.e("height", "initView: " + height);

        llText.measure(w, h);
        height2 = llText.getMeasuredHeight();

        initAnimator();

        rel.animate().alpha(1.0f).setDuration(2000).start();
        rel.postDelayed(new Runnable() {
            @Override
            public void run() {
                valueAnimatorShow1.start();
            }
        }, 1600);
        rel.postDelayed(new Runnable() {
            @Override
            public void run() {
                valueAnimatorShow.start();
            }
        }, 2200);
    }

    /**
     * 布局展开/隐藏动画
     * 属性动画-动态改变布局高度
     */
    void initAnimator() {
        final ValueAnimator.AnimatorUpdateListener ani = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int h = (Integer) animation.getAnimatedValue();
                //动态更新view的高度
                linear.getLayoutParams().height = h;
                linear.requestLayout();
            }
        };

        valueAnimatorHide = ValueAnimator.ofInt(height, 0);
        valueAnimatorShow = ValueAnimator.ofInt(0, height);

        valueAnimatorHide.addUpdateListener(ani);
        valueAnimatorShow.addUpdateListener(ani);

        valueAnimatorShow.setDuration(700);
        valueAnimatorHide.setDuration(700);

        final ValueAnimator.AnimatorUpdateListener ani1 = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int h = (Integer) animation.getAnimatedValue();
                //动态更新view的高度
                llText.getLayoutParams().height = h;
                llText.requestLayout();
            }
        };

        valueAnimatorHide1 = ValueAnimator.ofInt(height2, 0);
        valueAnimatorShow1 = ValueAnimator.ofInt(0, height2);
        valueAnimatorHide1.addUpdateListener(ani1);
        valueAnimatorShow1.addUpdateListener(ani1);
        valueAnimatorShow1.setDuration(800);
        valueAnimatorHide1.setDuration(800);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onPageStart(mActivity, "黑匣子引导页面");
        gethjz();
    }

    /**
     * 查询黑匣子
     */
    private void gethjz() {
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());
        loginRequestModel.setBody(requestModel);
        loginRequestModel.setHeader(headerBean);
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(loginRequestModel);
        LogUtils.logd(json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        mvpPresenter.gethjz(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        showLoadingDialog();
        ApiStores apiStores = AppClient.retrofit(mActivity).create(ApiStores.class);
        Observable<EncryptedResponseModel> observable = apiStores.gethjz(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EncryptedResponseModel>() {
                    @Override
                    public void onCompleted() {dismissLoadingDialog();}
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(EncryptedResponseModel model) {
                        String msgType = model.getMsgType();
                        if (msgType != null && msgType.equals("2")) {//加密
                            String encMsg = model.getEncMsg();
                            LogUtils.loge(model.getEncMsg());
                            try {
                                String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                                LogUtils.loge("解密后：查询黑匣子" + platext);
                                GetHjzModel getHjzModel = GsonTools.getObject(platext, GetHjzModel.class);
                                if (getHjzModel.getHeader().getCode().equals("0000")) {
                                    if (getHjzModel.getBody().getInfoDto() == null) {//未设置
                                        spUtils.setOpenblackBox(false);
                                    } else {//已设置黑匣子
                                        spUtils.setOpenblackBox(true);
                                        ActivityUtils.startActivityRightIn(BlackboxGuideActivity.this, MyBlackboxDetailActivity.class);
                                    }
                                } else {
//                                    toastShow(getHjzModel.getHeader().getDesc());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                        }
                    }

                });
    }



    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity, "黑匣子引导页面");
    }

    @OnClick({R.id.linear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear:
//                StatService.onEvent(mActivity, "黑匣子介绍页面点击立即设置按钮", "[黑匣子介绍页面点击立即设置按钮]");
                if (spUtils.getIsLogin()) {
                    StatService.onEvent(mActivity, "黑匣子介绍页面点击立即设置按钮", "尝试开启黑匣子", 1);
                    ActivityUtils.startActivityRightIn(BlackboxGuideActivity.this, BlackBoxEditActivity.class);//MyBlackboxActivity  BlackBoxEditActivity
                }else {
                    ActivityUtils.startActivityRightInWithFrom(this, LoginActivity.class, "blackboxguide");//forgetGes
                }
                break;
        }
    }

}
