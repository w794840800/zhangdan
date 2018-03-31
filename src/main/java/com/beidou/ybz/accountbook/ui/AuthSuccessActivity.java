package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.util.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author: xu.yang on 2018/3/8
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:实名认证结果
 */
public class AuthSuccessActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tvRealname)
    TextView tvRealname;
    @Bind(R.id.tvIdNum)
    TextView tvIdNum;
    private String realName,idNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authsuccess);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        tvTitle.setText("实名认证");
        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.finishActivity(mActivity);
            }
        });


        Intent in = getIntent();
        if(in != null){
            realName = in.getStringExtra("realName");
            idNo = in.getStringExtra("idNo");
        }

        try {
            if(realName != null && realName.length() > 1){
                realName = "*"+realName.substring(1);
            }
            if(idNo != null && idNo.length() > 0){
                idNo = idNo.substring(0,1)+"****************"+idNo.substring(idNo.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvRealname.setText(realName);
        tvIdNum.setText(idNo);
    }
}
