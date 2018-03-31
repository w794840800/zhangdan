package com.beidou.ybz.accountbook.ui;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.util.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: xu.yang on 2017/12/21
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:黑匣子-未设置界面
 */
public class BlackboxUnsettingActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.set_btn)
    TextView setBtn;
    @Bind(R.id.linear)
    LinearLayout linear;
    @Bind(R.id.rel)
    RelativeLayout rel;
    @Bind(R.id.ivBankcard)
    ImageView ivBankcard;
    @Bind(R.id.llIm)
    LinearLayout llIm;
    private int height;
    private ValueAnimator valueAnimatorShow, valueAnimatorHide;
    private boolean isMoreShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blackbox_unsetting);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.finishActivity(mActivity);
            }
        });

        setShowAnimation(rel, 2000);
        setAnimation(linear, 1500);

        //计算llMore的高度
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        llIm.measure(w, h);
        height = llIm.getMeasuredHeight();
        Log.e("height", "initView: " + height);

        initAnimator();
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
                llIm.getLayoutParams().height = h;
                llIm.requestLayout();
            }
        };

        valueAnimatorHide = ValueAnimator.ofInt(height, 0);
        valueAnimatorShow = ValueAnimator.ofInt(0, height);

        valueAnimatorHide.addUpdateListener(ani);
        valueAnimatorShow.addUpdateListener(ani);

        valueAnimatorShow.setDuration(400);
        valueAnimatorHide.setDuration(400);

    }

    /**
     * 平移动画
     *
     * @param view
     * @param duration
     */
    private void setAnimation(View view, int duration) {
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        animation.setDuration(duration);
        view.startAnimation(animation);
    }


    private AlphaAnimation mShowAnimation = null;

    /**
     * 渐渐显示动画
     *
     * @param view
     * @param duration
     */
    private void setShowAnimation(View view, int duration) {

        if (null == view || duration < 0) {
            return;
        }
        if (null != mShowAnimation) {
            mShowAnimation.cancel();
        }
        mShowAnimation = new AlphaAnimation(0.0f, 1.0f);
        mShowAnimation.setDuration(duration);
        mShowAnimation.setFillAfter(true);
        view.startAnimation(mShowAnimation);
    }


    @OnClick(R.id.linear)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear:
//                if (!isMoreShow) {
//                    isMoreShow = true;
//                    valueAnimatorShow.start();
//                } else {
//                    valueAnimatorHide.start();
//                    isMoreShow = false;
//                }
                ActivityUtils.startActivityRightIn(BlackboxUnsettingActivity.this, MyBlackboxActivity.class);
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        StatService.onPageStart(mActivity, "黑匣子引导页面");
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity, "黑匣子引导页面");
    }
}
