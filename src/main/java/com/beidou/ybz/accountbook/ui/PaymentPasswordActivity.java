package com.beidou.ybz.accountbook.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.ImeUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Author: xu.yang on 2018/3/2
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:支付密码已设置页面
 */
public class PaymentPasswordActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_paymentpassword);
        ButterKnife.bind(this);
        tvTitle.setText("支付密码");
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

    @OnClick({R.id.rlUpdatePayPassword, R.id.rlForgetPayPassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlUpdatePayPassword:
                ActivityUtils.startActivityRightIn(mActivity,VertifyPayPasswdActivity.class);
                break;
            case R.id.rlForgetPayPassword:
                ActivityUtils.startActivityRightInWithFrom(mActivity,VerifyOldPhoneActivity.class,"forgetPayPasswd");
                break;
        }
    }
}
