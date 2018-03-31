package com.beidou.ybz.accountbook.widget;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.beidou.ybz.accountbook.util.LogUtils;

/**
 * Created by lenovo on 2016/11/14.
 */
public class ScaleTransformer implements ViewPager.PageTransformer{
    private static final float MIN_SCALE = 0.80f;
    private static final float MIN_ALPHA = 0.6f;
    @Override
    public void transformPage(View page, float position) {
        LogUtils.logd("position:::::::::"+position);
        if (position < -1 || position > 1) {
//            page.setAlpha(MIN_ALPHA);
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        } else if (position <= 1) { // [-1,1]
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            if (position < 0) {
                float scaleX = 1 + 0.2f * position;
                Log.d("google_lenve_fb", "transformPage: scaleX:" + scaleX);
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            } else {
                float scaleX = 1 - 0.2f * position;
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            }
//            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        }
    }
}
