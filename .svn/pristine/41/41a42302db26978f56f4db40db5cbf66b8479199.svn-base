package com.beidou.ybz.accountbook.util;

import android.os.CountDownTimer;

import com.beidou.ybz.accountbook.retrofit.TimeCountInterface;


/**
 * Created by bob on 2017/9/1.
 */
public  class TimeCount extends CountDownTimer {
    private TimeCountInterface mTimeCountInterface;
    public TimeCount(TimeCountInterface timeCountInterface, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        mTimeCountInterface = timeCountInterface;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTimeCountInterface.onTick(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        mTimeCountInterface.onFinish();
    }
}
