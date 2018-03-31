package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.SercetKeyOverdueModel;
import com.beidou.ybz.accountbook.mvp.main.TextWatchView;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.ImeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.ToastUtils;
import com.beidou.ybz.accountbook.widget.ClearEditText;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.IndexModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.NewWatcher;
import com.google.gson.Gson;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func3;
import rx.functions.Func6;

/**
 * Author: xu.yang on 2017/12/5
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module: 添加报销
 */
public class AddReimbursement extends MvpActivity<CommonPresenter> implements CommonView<SercetKeyOverdueModel> {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btnSave)
    Button btnSave;
    @Bind(R.id.cetMoney)
    ClearEditText cetMoney;
    @Bind(R.id.cetName)
    ClearEditText cetName;
    @Bind(R.id.cetMemo)
    ClearEditText cetMemo;
    private String encMsg, signMsg,memo,name,amount,id,from;
    private boolean isEdit;//true-编辑 false-新增
    private boolean isFromList;
    private boolean isFromAddasset;//来自首次添加页面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreimbursement);

        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatService.onEvent(mActivity,"点击报销资产编辑页返回按钮","放弃报销资产编辑",1);
                ActivityUtils.finishActivity(mActivity);
                ImeUtil.hideSoftKeyboard(cetName);
            }
        });
        initView();
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent in){
        //id, name ,memo, comment ,amount ,from,times,expireDate
        if (in != null) {
            id = in.getStringExtra("id");
            memo = in.getStringExtra("memo");
            amount = in.getStringExtra("amount");
            name = in.getStringExtra("name");
            from = in.getStringExtra("from");
        }

        if(name != null && !TextUtils.isEmpty(name)){
            isEdit = true;
        }else{
            isEdit = false;
        }
        LogUtils.logd("isEdit:"+isEdit);
        if(isEdit){
            tvTitle.setText("编辑报销");
        }else{
            tvTitle.setText("添加报销");
        }

        if(from != null && from.equals("list")){
            isFromList = true;
        }else{
            isFromList = false;
        }
        if(from != null && from.equals("addasset")){
            isFromAddasset = true;
        }else{
            isFromAddasset = false;
        }

        if(name != null && name.length() > 0 ){
            cetName.setText(name);
            cetName.setSelection(name.length());

        }

        cetMoney.setText(amount);
        if (!TextUtils.isEmpty(cetMoney.getText().toString())) {
            cetMoney.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        }

        cetMemo.setText(memo);
    }

    void initView() {
        bindViewByRxBinding();
        /**
         * 添加监听，在hint时和text时切换字体大小
         */
        Utils.textChangedListener(cetMoney);
    }

    /**
     * 用combineLatest处理表单验证
     */
    private void bindViewByRxBinding() {
        Observable<CharSequence> Observable1 = RxTextView.textChanges(cetName);
        Observable<CharSequence> Observable2 = RxTextView.textChanges(cetMoney);
        Observable<CharSequence> Observable3 = RxTextView.textChanges(cetMemo);

        Observable.combineLatest(Observable1, Observable2, Observable3,
                new Func3<CharSequence, CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence string1, CharSequence string2, CharSequence string3) {
                        return !Utils.isTextEmpty(string1.toString()) || !Utils.isTextEmpty(string2.toString())
                                || !Utils.isTextEmpty(string3.toString());
                    }
                }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean verify) {
                if (verify) {
                    btnSave.setEnabled(true);
                    btnSave.setBackgroundResource(R.drawable.bg1);
                } else {
                    btnSave.setEnabled(false);
                    btnSave.setBackgroundResource(R.drawable.bg_unenabled);
                }
            }
        });
    }


    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this,this);
    }

    @OnClick({R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                if(isEdit){
                    StatService.onEvent(mActivity,"点击报销资产编辑页确认按钮","编辑报销资产",1);
                }else if(isFromAddasset){
                    StatService.onEvent(mActivity,"点击报销资产添加页确认按钮","首次新增报销",1);
                }else{
                    StatService.onEvent(mActivity,"报销列表进入添加页并点击确认按钮","确认新增报销",1);
                }
                if (valid()) {
                    sava();
                }
                break;
        }
    }

    /**
     * 提交前判断输入是否为空
     *
     * @return
     */
    boolean valid() {
        if (TextUtils.isEmpty(cetName.getText().toString()) ) {
            toastShow("请输入报销人姓名");
            return false;
        } else if(TextUtils.isEmpty(cetMoney.getText().toString())){
            toastShow("请输入报销金额");
            return false;
        }else {
            return true;
        }
    }

    void sava() {
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());
        requestModel.setId(id);
        requestModel.setArrearsName(cetName.getText().toString());
        requestModel.setAmount(cetMoney.getText().toString());
        requestModel.setMemo(cetMemo.getText().toString());

        loginRequestModel.setBody(requestModel);
        loginRequestModel.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(loginRequestModel);
        LogUtils.logd(json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isEdit) {
            mvpPresenter.updateexpenseaccount(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        } else {
            mvpPresenter.addExpenseaccount(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        }
    }

    @Override
    public void getDataSuccess(SercetKeyOverdueModel model) {
        if(isEdit) {
            toastShow("编辑成功", R.drawable.gou_toast);
            cetName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActivityUtils.startActivity(mActivity,ReimbursementActivity.class);//从详情页跳转过来编辑成功后，回到列表页
                    ActivityUtils.finishActivity(mActivity);
                }
            }, ToastUtils.toastTime);
        }else{
            toastShow("添加成功", R.drawable.gou_toast);
            cetName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(isFromList){
                        ActivityUtils.finishActivity(mActivity);
                    }else {
                        Intent in = new Intent(mActivity, ReimbursementActivity.class);
                        in.putExtra("from","add");
                        startActivity(in);
                        overridePendingTransition(R.anim.left_in, 0);
                    }
                }
            },ToastUtils.toastTime);

        }
    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isEdit) {
            StatService.onPageStart(mActivity, "编辑报销页面");
        }else{
            StatService.onPageStart(mActivity, "添加报销页面");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ImeUtil.hideSoftKeyboard(cetName);

        if(isEdit) {
            StatService.onPageEnd(mActivity, "编辑报销页面");
        }else{
            StatService.onPageEnd(mActivity, "添加报销页面");
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            StatService.onEvent(mActivity,"点击报销资产编辑页返回按钮","放弃报销资产编辑",1);
            ActivityUtils.finishActivity(mActivity);
            ImeUtil.hideSoftKeyboard(cetName);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
