package com.beidou.ybz.accountbook.widget;


//import android.app.AlertDialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.beidou.ybz.accountbook.R;


/**
 * 加载中Dialog
 * @author xu.yang
 * @E-mail 754444814@qq.com
 * @Createtime 2014-5-10 上午9:14:34
 *
 */
public class LoadingDialog extends AlertDialog {

    public LoadingDialog(Context context) {
        super(context, R.style.FullScreenDialog);
    }
    public LoadingDialog(Context context, int theme, String message) {
        super(context.getApplicationContext(), theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(false);
        this.setContentView(R.layout.view_tips_loading);
    }



}
