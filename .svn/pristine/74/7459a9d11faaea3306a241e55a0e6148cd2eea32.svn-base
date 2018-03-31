package com.beidou.ybz.accountbook.util;

import android.support.design.widget.AppBarLayout;

/**监听CollapsingToolbarLayout的展开与折叠
 * Created by bob on 2016/9/2.
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0619/4362.html
 */
public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mCurrentState = State.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
//        LogUtils.loge("距离："+i+"");

        onOffset(appBarLayout,Math.abs(i));

        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE);
            }
            mCurrentState = State.IDLE;
        }
    }

//    public abstract void onOffsetChanged(AppBarLayout appBarLayout, int i);
    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
    public abstract void onOffset(AppBarLayout appBarLayout, int i);
}
