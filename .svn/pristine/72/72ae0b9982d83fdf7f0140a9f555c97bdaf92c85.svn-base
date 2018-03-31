package com.beidou.ybz.accountbook.ui;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.beidou.ybz.accountbook.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShixiangTestActivity extends BaseActivity {

    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.rl1)
    RelativeLayout rl1;
    @Bind(R.id.rl2)
    LinearLayout rl2;
    private int height;
    private ValueAnimator valueAnimatorShow, valueAnimatorHide;
    private boolean isMoreShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shixiang_test);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //计算llMore的高度
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        rl2.measure(w, h);
        height = rl2.getMeasuredHeight();
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
                rl2.getLayoutParams().height = h;
                rl2.requestLayout();
            }
        };

        valueAnimatorHide = ValueAnimator.ofInt(height, 0);
        valueAnimatorShow = ValueAnimator.ofInt(0, height);

        valueAnimatorHide.addUpdateListener(ani);
        valueAnimatorShow.addUpdateListener(ani);

        valueAnimatorShow.setDuration(400);
        valueAnimatorHide.setDuration(400);

    }


    @OnClick(R.id.btn1)
    public void onViewClicked() {
        if (!isMoreShow) {
            valueAnimatorShow.start();
            isMoreShow = true;
        } else {
            valueAnimatorHide.start();
            isMoreShow = false;
        }
    }
}
