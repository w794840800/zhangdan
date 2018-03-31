package com.beidou.ybz.accountbook.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.PayPasswdQueryModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.ImeUtil;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.PinEntryEditText;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: xu.yang on 2018/3/5
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:修改支付密码前验证当前支付密码
 */
public class VertifyPayPasswdActivity extends MvpActivity<CommonPresenter> implements CommonView<PayPasswdQueryModel> {
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
    private String encMsg, signMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpaypasswd);
        ButterKnife.bind(this);

        tvTitle.setText("身份验证");
        tvDescribe.setText("请输入当前的支付密码");
        tvForgetPasswd.setVisibility(View.VISIBLE);
        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.finishActivity(mActivity);
                ImeUtil.hideSoftKeyboard(petPasswd);
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
                checktxpwd();
            }
        });

        btnSave.setVisibility(View.GONE);
    }

    /**
     * 查询支付密码是否已设置
     */
    void checktxpwd() {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());
        requestModel.setTxPwd(petPasswd.getText().toString());


        requestBody.setBody(requestModel);
        requestBody.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(requestBody);

        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.checktxpwd(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }


    @Override
    public void getDataSuccess(PayPasswdQueryModel model) {
        String result = model.getBody().getResult();
        String errTimes = model.getBody().getErrTimes();
        if (result != null && result.equals("001")) {//验证通过
            ActivityUtils.startActivityRightIn(mActivity, SetPayPasswdActivity.class);
        } else if (result != null && result.equals("-100") && !TextUtils.isEmpty(errTimes) && Utils.isNumeric(errTimes)) {//表示密码错误少于五次
            if (Integer.parseInt(errTimes) == 5) {//表示密码错误少于五次
                toastShow("输入错误次数过多，请24小时后再修改支付密码");
                petPasswd.setText(null);
            } else {
                toastShow("匹配失败，还可以输入" + (5 - Integer.parseInt(errTimes)) + "次");
                petPasswd.setText(null);
            }
        } else {
            toastShow("输入错误次数过多，请24小时后再修改支付密码");
            petPasswd.setText(null);
        }

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

    @OnClick(R.id.tvForgetPasswd)
    public void onViewClicked() {
        ActivityUtils.startActivityRightInWithFrom(mActivity,VerifyOldPhoneActivity.class,"forgetPayPasswd");
    }
}
