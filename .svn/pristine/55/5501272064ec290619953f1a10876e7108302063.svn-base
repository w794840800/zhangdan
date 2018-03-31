package com.beidou.ybz.accountbook.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.ImeUtil;

import butterknife.Bind;

/**
 * Author: Bob on 2017/11/3 9:31
 * QQ:754444814
 * E-mail:754444814@qq.com
 * purpose:修改密码
 */
public class AlterPasswordActivity extends MvpActivity<CommonPresenter> {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterpassword);

        tvTitle.setText("修改密码");
        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImeUtil.hideSoftKeyboard(v);
                ActivityUtils.finishActivity(mActivity);
            }
        });
    }

    @Override
    protected CommonPresenter createPresenter() {
        return null;
    }

}
