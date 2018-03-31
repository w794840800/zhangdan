package com.beidou.ybz.accountbook.widget;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Author: xu.yang on 2018/1/8
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:
 */
public abstract class BasePageTransformer implements ViewPager.PageTransformer {
    protected ViewPager.PageTransformer mPageTransformer = NonPageTransformer.INSTANCE;
    public static final float DEFAULT_CENTER = 0.5f;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void transformPage(View view, float position)
    {
        if (mPageTransformer != null)
        {
            mPageTransformer.transformPage(view, position);
        }

        pageTransform(view, position);
    }

    protected abstract void pageTransform(View view, float position);
}
