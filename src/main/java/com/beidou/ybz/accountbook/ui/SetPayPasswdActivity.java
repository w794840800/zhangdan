package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.IndexModel;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.AlertDialogUtils;
import com.beidou.ybz.accountbook.util.DialogClickListener;
import com.beidou.ybz.accountbook.util.ImeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.widget.PinEntryEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author: xu.yang on 2018/3/5
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:设置支付密码页面(第一步)
 */
public class SetPayPasswdActivity extends MvpActivity<CommonPresenter> implements CommonView<IndexModel> {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.petPasswd)
    PinEntryEditText petPasswd;
    @Bind(R.id.tvDescribe)
    TextView tvDescribe;
    @Bind(R.id.btnSave)
    Button btnSave;
    @Bind(R.id.tvForgetPasswd)
    TextView tvForgetPasswd;
    AlertDialogUtils alertDialogUtils;
    AlertDialogUtils alertDialogUtils2;
    private String from;
    private boolean isFromH5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpaypasswd);
        ButterKnife.bind(this);

        tvTitle.setText("设置支付密码");
        tvDescribe.setText("请输入新的支付密码");
        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityUtils.finishActivity(mActivity);
//                ImeUtil.hideSoftKeyboard(petPasswd);
                alertDialogUtils2.setMessage("确定要放弃设置支付密码？");
                alertDialogUtils2.show();
            }
        });

        handlerIntent(getIntent());

        tvForgetPasswd.setVisibility(View.GONE);
        alertDialogUtils = new AlertDialogUtils(mActivity, "");
        alertDialogUtils2 = new AlertDialogUtils(mActivity);
        alertDialogUtils.setOnDialogClickListener(new DialogClickListener() {
            @Override
            public void clickYes(int which) {
                petPasswd.setText(null);
            }

            @Override
            public void clickNo() {
            }
        });

        alertDialogUtils2.setOnDialogClickListener(new DialogClickListener() {
            @Override
            public void clickYes(int which) {
                petPasswd.setText(null);
                ActivityUtils.finishActivity(mActivity);
            }

            @Override
            public void clickNo() {
            }
        });

        petPasswd.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImeUtil.showSoftKeyboard(petPasswd);
            }
        }, 300);

        petPasswd.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                String passwd = str.toString();
                if (valid(passwd)) {
                    ActivityUtils.startActivityRightInWithFrom(SetPayPasswdActivity.this, SetPayPasswdConfirmActivity.class,from);
                    petPasswd.setText(null);
                    spUtils.setPayPasswd(str.toString());
                }

//                ImeUtil.hideSoftKeyboard(petPasswd);
//
            }
        });

        btnSave.setVisibility(View.GONE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handlerIntent(intent);
    }

    void handlerIntent(Intent intent) {
        if (intent != null) {
            from = intent.getStringExtra("from");
        }
        if(from != null && from.equals("forgetPayPasswdFromWeb")){//from:H5中的忘记支付密码
            isFromH5 = true;
        }else {
            isFromH5 = false;
        }
    }



    /**
     * 6位密码判断
     */
    boolean valid(String input) {
        char[] ch = input.toCharArray();
        int[] in = new int[6];
        int count = 0;
        for (int i = 0; i < ch.length; i++) {
            in[i] = Integer.parseInt(String.valueOf(ch[i]));
            count += in[i];
            if (i > 0 && in[i] == in[i - 1]) {
//                toastShow("重复数字");
                alertDialogUtils.setMessage("支付密码设置过于简单，请重新设置！");
                alertDialogUtils.show();
                return false;
            }
        }
        LogUtils.logd("总和：" + count);
        if (count / 6 == in[2] || count / 6 == in[3]) {//连续数字
//            toastShow("连续数字");
            alertDialogUtils.setMessage("支付密码设置过于简单，请重新设置！");
            alertDialogUtils.show();
            return false;
        }

        return true;


    }


    @Override
    public void getDataSuccess(IndexModel model) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ImeUtil.hideSoftKeyboard(petPasswd);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            alertDialogUtils2.setMessage("确定要放弃设置支付密码？");
            alertDialogUtils2.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
