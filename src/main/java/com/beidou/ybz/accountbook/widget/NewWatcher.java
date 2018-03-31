package com.beidou.ybz.accountbook.widget;

import android.text.Editable;
import android.text.TextWatcher;

import com.beidou.ybz.accountbook.mvp.main.TextWatchView;
import com.beidou.ybz.accountbook.util.LogUtils;

/**
 * Author: xu.yang on 2017/12/19
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:edittext交互要求：如果都没填写置灰不可点击，只要填写一个，按钮高亮可点击
 */
public class NewWatcher implements TextWatcher  {
    private boolean check = false, check_ = true;
    private int checkCount = 0;
    private TextWatchView textWatchView;

    public NewWatcher(TextWatchView textWatchView) {
        this.textWatchView = textWatchView;
        EdtCheckEntity.checkNum = 0;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0) {
            check = true;
        } else if (s.length() == 0) {
            check_ = true;                            //开门
            check = false;
        }
        if (check) {
            if (check_) {
                EdtCheckEntity.checkNum++;
                check_ = false;
                LogUtils.logd("EdtCheckEntity.checkNum1111:" + EdtCheckEntity.checkNum);
                if (EdtCheckEntity.checkNum >= 0) {
                    textWatchView.enable();

                }
            }
        } else {
            EdtCheckEntity.checkNum--;
            LogUtils.logd("EdtCheckEntity.checkNum22222:" + EdtCheckEntity.checkNum);
            if (EdtCheckEntity.checkNum < 0) {
                textWatchView.unable();
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
