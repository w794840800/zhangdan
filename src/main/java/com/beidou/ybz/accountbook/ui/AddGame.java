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
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.IndexModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.entity.SercetKeyOverdueModel;
import com.beidou.ybz.accountbook.mvp.main.TextWatchView;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.ImeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.ToastUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.ClearEditText;
import com.beidou.ybz.accountbook.widget.NewWatcher;
import com.google.gson.Gson;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func3;
import rx.functions.Func4;

/**
 * Author: xu.yang on 2017/12/5
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module: 添加游戏
 */
public class AddGame extends MvpActivity<CommonPresenter> implements CommonView<SercetKeyOverdueModel> {
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
    private String encMsg, signMsg;
    private String id, name ,memo ,amount ,from;
    private boolean isEdit;//true-编辑 false-新增
    private boolean isFromList;
    private boolean isFromAddasset;//来自首次添加页面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgame);
        ButterKnife.bind(this);

        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatService.onEvent(mActivity,"点击游戏资产编辑页返回按钮","放弃游戏资产编辑",1);
                ActivityUtils.finishActivity(mActivity);
                ImeUtil.hideSoftKeyboard(cetName);
            }
        });
        initView();
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
            name = in.getStringExtra("name");
            amount = in.getStringExtra("amount");
            from = in.getStringExtra("from");
        }

        if(name != null && !TextUtils.isEmpty(name)){
            isEdit = true;
        }else{
            isEdit = false;
        }
        LogUtils.logd("isEdit:"+isEdit);
        if(isEdit){
            tvTitle.setText("编辑游戏");
        }else{
            tvTitle.setText("添加游戏");
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

        cetName.setText(name);
        cetMoney.setText(amount);
        if (!TextUtils.isEmpty(cetMoney.getText().toString())) {
            cetMoney.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        }

        if(name != null && name.length() > 0 ){
            cetName.setSelection(name.length());
        }
        cetMemo.setText(memo);
    }

    void initView() {
        bindViewByRxBinding();

        handleIntent(getIntent());

        /**
         * 添加监听，在hint时和text时切换字体大小
         */
        Utils.textChangedListener(cetMoney);
    }

    /**
     *用combineLatest处理表单验证
     */
    private void bindViewByRxBinding(){
        Observable<CharSequence> ObservableName = RxTextView.textChanges(cetName);
        Observable<CharSequence> ObservableMoney = RxTextView.textChanges(cetMoney);
        Observable<CharSequence> ObservableMemo = RxTextView.textChanges(cetMemo);

        Observable.combineLatest(ObservableName,ObservableMoney,ObservableMemo,
                new Func3<CharSequence, CharSequence,CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence name, CharSequence money, CharSequence memo) {
                        return !Utils.isTextEmpty(name.toString()) || !Utils.isTextEmpty(money.toString())  || !Utils.isTextEmpty(memo.toString());
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

    void save() {
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());
        requestModel.setId(id);
        requestModel.setGameName(cetName.getText().toString());
        requestModel.setAmount(cetMoney.getText().toString());
        requestModel.setMemo(cetMemo.getText().toString());

        loginRequestModel.setBody(requestModel);
        loginRequestModel.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(loginRequestModel);
        LogUtils.loge("请求参数：" + json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isEdit) {
            mvpPresenter.updategame(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        } else {
            mvpPresenter.addgame(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        }
    }

    @Override
    public void getDataSuccess(SercetKeyOverdueModel model) {
        if(isEdit) {
            toastShow("编辑成功", R.drawable.gou_toast);
            cetName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActivityUtils.startActivity(mActivity,GameActivity.class);//从详情页跳转过来编辑成功后，回到列表页
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
                        Intent in = new Intent(mActivity, GameActivity.class);
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
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this,this);
    }

    @OnClick({R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                if(isEdit){
                    StatService.onEvent(mActivity,"点击游戏资产编辑页确认按钮","编辑游戏资产",1);
                }else if(isFromAddasset){
                    StatService.onEvent(mActivity,"点击游戏资产添加页确认按钮","首次新增游戏",1);
                }else{
                    StatService.onEvent(mActivity,"游戏列表进入添加页并点击确认按钮","确认新增游戏",1);
                }
                if (valid()) {
                    save();
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
            toastShow("请输入游戏名称");
            return false;
        } else if(TextUtils.isEmpty(cetMoney.getText().toString())){
            toastShow("请输入游戏价值");
            return false;
        }else {
            return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isEdit) {
            StatService.onPageStart(mActivity, "编辑游戏页面");
        }else{
            StatService.onPageStart(mActivity, "添加游戏页面");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ImeUtil.hideSoftKeyboard(cetName);

        if(isEdit) {
            StatService.onPageEnd(mActivity, "编辑游戏页面");
        }else{
            StatService.onPageEnd(mActivity, "添加游戏页面");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            StatService.onEvent(mActivity,"点击游戏资产编辑页返回按钮","放弃游戏资产编辑",1);
            ActivityUtils.finishActivity(mActivity);
            ImeUtil.hideSoftKeyboard(cetName);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
