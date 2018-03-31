package com.beidou.ybz.accountbook.widget;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Author: xu.yang on 2018/1/8
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:
 */
public class NonPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position)
    {
        page.setScaleX(0.999f);//hack
    }

    public static final ViewPager.PageTransformer INSTANCE = new NonPageTransformer();
}
