package com.beidou.ybz.accountbook.ui;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.entity.SercetKeyOverdueModel;
import com.beidou.ybz.accountbook.mvp.main.TextWatchView;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.ToastUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.ClearEditText;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.ImeUtil;
import com.beidou.ybz.accountbook.widget.NewWatcher;
import com.google.gson.Gson;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func4;
import rx.functions.Func5;

/**
 * Author: xu.yang on 2017/12/1
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module: 添加海外资产
 */
public class AddOverseasAssets extends MvpActivity<CommonPresenter> implements CommonView<SercetKeyOverdueModel>{
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ivShowMore)
    ImageView ivShowMore;
    @Bind(R.id.llShowMore)
    LinearLayout llShowMore;
    @Bind(R.id.llMore)
    LinearLayout llMore;
    @Bind(R.id.cetName)
    ClearEditText cetName;
    @Bind(R.id.tvCurType)
    TextView tvCurType;
    @Bind(R.id.cetAmount)
    ClearEditText cetAmount;
    @Bind(R.id.tvPingfang)
    TextView tvPingfang;
    @Bind(R.id.tvFt)
    TextView tvFt;
    @Bind(R.id.cetArea)
    ClearEditText cetArea;
    @Bind(R.id.cetAddress)
    ClearEditText cetAddress;
    @Bind(R.id.cetMemo)
    ClearEditText cetMemo;
    @Bind(R.id.btnSave)
    Button btnSave;
    @Bind(R.id.tvUnit)
    TextView tvUnit;
    private boolean isMoreShow;
    private int height;
    private ValueAnimator valueAnimatorShow, valueAnimatorHide;
    private String areaUnit;//面积单位
    private String amountUnit;//价格单位
    private String encMsg, signMsg;
    private String id, name, memo, acreage, amount, address, unit, curType, from;
    private boolean isEdit;//true-编辑 false-新增
    private final int requestCodeCoin = 22;
    private boolean isFromList;
    private boolean isFromAddasset;//来自首次添加页面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addoverseasassets);

        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatService.onEvent(mActivity,"点击房产资产编辑页返回按钮","放弃房产资产编辑",1);
                ActivityUtils.finishActivity(mActivity);
                ImeUtil.hideSoftKeyboard(cetName);
            }
        });
        handleIntent(getIntent());
        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent in) {
        //计算llMore的高度
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        llMore.measure(w, h);
        height = llMore.getMeasuredHeight();
        Log.e("height", "initView: " + height);

        initAnimator();

        if (in != null) {
            id = in.getStringExtra("id");
            name = in.getStringExtra("name");
            memo = in.getStringExtra("memo");
            acreage = in.getStringExtra("acreage");
            amount = in.getStringExtra("amount");
            address = in.getStringExtra("address");
            unit = in.getStringExtra("unit");
            curType = in.getStringExtra("curType");
            from = in.getStringExtra("from");
        }

        if (memo != null && !TextUtils.isEmpty(memo)
                || acreage != null && !TextUtils.isEmpty(acreage)
                || address != null && !TextUtils.isEmpty(address)) {
            cetName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    valueAnimatorShow.start();
                    isMoreShow = true;
                    ivShowMore.animate().rotation(180f).setDuration(400).start();
                }
            }, 300);
        }

        if (curType == null || curType.equals("CNY")) {
            tvUnit.setVisibility(View.VISIBLE);
        }else{
            tvUnit.setVisibility(View.GONE);
        }

        if (name != null && !TextUtils.isEmpty(name)) {
            isEdit = true;
        } else {
            isEdit = false;
        }
        LogUtils.logd("isEdit:" + isEdit);
        if (isEdit) {
            tvTitle.setText("编辑房产");
        } else {
            tvTitle.setText("添加房产");
        }

        if (from != null && from.equals("list")) {
            isFromList = true;
        } else {
            isFromList = false;
        }
        if(from != null && from.equals("addasset")){
            isFromAddasset = true;
        }else{
            isFromAddasset = false;
        }
    }

    void initView() {
        areaUnit = "㎡";
        amountUnit = "CNY";

        bindViewByRxBinding();
//        cetName.addTextChangedListener(new NewWatcher(this));
//        cetAmount.addTextChangedListener(new NewWatcher(this));

        try {
            cetName.setText(name);
            cetName.setSelection(name.length());
            cetAmount.setText(amount);
            cetArea.setText(acreage);
            cetAddress.setText(address);
            tvCurType.setText(curType);
            if (!TextUtils.isEmpty(cetAmount.getText().toString())) {
                cetAmount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            }

            if (unit != null && unit.equals("ft²")) {
                tvFt.setBackgroundResource(R.drawable.rmb);
                tvFt.setTextColor(getResources().getColor(R.color.colorWhite));
                tvPingfang.setTextColor(getResources().getColor(R.color.button_text_unable));
                tvPingfang.setBackgroundResource(R.drawable.rmb_empty);
                areaUnit = "ft²";
            } else {
                areaUnit = "㎡";
                tvPingfang.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFt.setTextColor(getResources().getColor(R.color.button_text_unable));
                tvPingfang.setBackgroundResource(R.drawable.rmb);
                tvFt.setBackgroundResource(R.drawable.rmb_empty);
            }
            cetMemo.setText(memo);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Utils.textChangedListener(cetAmount);

    }

    /**
     * 用combineLatest处理表单验证
     */
    private void bindViewByRxBinding(){
        Observable<CharSequence> Observable1 = RxTextView.textChanges(cetName);
        Observable<CharSequence> Observable2 = RxTextView.textChanges(cetAmount);
        Observable<CharSequence> Observable3 = RxTextView.textChanges(cetArea);
        Observable<CharSequence> Observable4 = RxTextView.textChanges(cetAddress);
        Observable<CharSequence> Observable5 = RxTextView.textChanges(cetMemo);

        Observable.combineLatest(Observable1,Observable2,Observable3,Observable4,Observable5,
                new Func5<CharSequence, CharSequence,CharSequence,CharSequence,CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence string1, CharSequence string2, CharSequence string3, CharSequence string4, CharSequence string5) {
                        return !Utils.isTextEmpty(string1.toString()) || !Utils.isTextEmpty(string2.toString())
                                || !Utils.isTextEmpty(string3.toString()) || !Utils.isTextEmpty(string4.toString())
                        ||!Utils.isTextEmpty(string5.toString());
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

    /**
     * 布局展开/隐藏动画
     * 属性动画-动态改变布局高度
     */
    void initAnimator() {
        final ValueAnimator.AnimatorUpdateListener ani = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int h = (Integer) animation.getAnimatedValue();
                //动态更新view的高度
                llMore.getLayoutParams().height = h;
                llMore.requestLayout();
            }
        };

        valueAnimatorHide = ValueAnimator.ofInt(height, 0);
        valueAnimatorShow = ValueAnimator.ofInt(0, height);

        valueAnimatorHide.addUpdateListener(ani);
        valueAnimatorShow.addUpdateListener(ani);

        valueAnimatorShow.setDuration(400);
        valueAnimatorHide.setDuration(400);

    }

    @Override
    public void getDataSuccess(SercetKeyOverdueModel model) {
        if (isEdit) {
            toastShow("编辑成功", R.drawable.gou_toast);
            cetName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActivityUtils.startActivity(mActivity,OverseasActivity.class);//从详情页跳转过来编辑成功后，回到列表页
                    ActivityUtils.finishActivity(mActivity);
                }
            }, ToastUtils.toastTime);

        } else {
            toastShow("添加成功", R.drawable.gou_toast);
            cetName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isFromList) {
                        ActivityUtils.finishActivity(mActivity);//从列表过来，保存成功后直接返回到列表
                    } else {
                        Intent in = new Intent(mActivity, OverseasActivity.class);
                        in.putExtra("from", "add");
                        startActivity(in);
                        overridePendingTransition(R.anim.left_in, 0);
                    }
                }
            },ToastUtils.toastTime);

        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this, this);
    }

    void sava() {
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());
        requestModel.setId(id);
        requestModel.setHomeName(cetName.getText().toString());
        requestModel.setAmount(cetAmount.getText().toString());
        requestModel.setCurType(tvCurType.getText().toString());
        requestModel.setUnit(areaUnit);
        requestModel.setArea(cetArea.getText().toString());
        requestModel.setAddress(cetAddress.getText().toString());
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
            mvpPresenter.updateoverseas(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        } else {
            mvpPresenter.addoverseas(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        }
    }

    @OnClick({R.id.llShowMore, R.id.btnSave, R.id.tvCurType, R.id.tvPingfang, R.id.tvFt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llShowMore:
                StatService.onEvent(mActivity,"添加房产资产时点击更多","添加更多房产信息",1);
                ImeUtil.hideSoftKeyboard(cetName);
                if (!isMoreShow) {
                    valueAnimatorShow.start();
                    isMoreShow = true;
                    ivShowMore.animate().rotation(180f).setDuration(400).start();
                } else {
                    valueAnimatorHide.start();
                    isMoreShow = false;
                    ivShowMore.animate().rotation(0f).setDuration(400).start();
                }
                break;
            case R.id.btnSave:
                if(isEdit){
                    StatService.onEvent(mActivity,"点击房产资产编辑页确认按钮","编辑房产资产",1);
                }else if(isFromAddasset){
                    StatService.onEvent(mActivity,"点击房产资产添加页确认按钮","首次新增房产",1);
                }else{
                    StatService.onEvent(mActivity,"房产列表进入添加页并点击确认按钮","确认新增房产",1);
                }
                if (valid()) {
                    sava();
                }
                break;
            case R.id.tvCurType:
                StatService.onEvent(mActivity, "添加房产时点击货币按钮", "添加外币房产", 1);
//                ActivityUtils.startActivityRightIn(mActivity,SupportCurrencyActivity.class);
                startActForResult(requestCodeCoin);
                break;
            case R.id.tvPingfang:
                areaUnit = "㎡";
                tvPingfang.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFt.setTextColor(getResources().getColor(R.color.button_text_unable));
                tvPingfang.setBackgroundResource(R.drawable.rmb);
                tvFt.setBackgroundResource(R.drawable.rmb_empty);
                break;
            case R.id.tvFt:
                tvFt.setBackgroundResource(R.drawable.rmb);
                tvFt.setTextColor(getResources().getColor(R.color.colorWhite));
                tvPingfang.setTextColor(getResources().getColor(R.color.button_text_unable));
                tvPingfang.setBackgroundResource(R.drawable.rmb_empty);
                areaUnit = "ft²";
                break;
        }
    }

    /**
     * startActForResult
     *
     * @param requestCode
     */
    void startActForResult(int requestCode) {
        Intent in = new Intent(this, SupportCurrencyActivity.class);
//        in.putExtra(tag, value);
//        in.putExtra("tag", tag);
        startActivityForResult(in, requestCode);
        overridePendingTransition(R.anim.left_in, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String info;
        if (resultCode == RESULT_OK) {
            info = data.getStringExtra("info");
            if (requestCode == requestCodeCoin) {
                LogUtils.logd("添加的"+info);
                tvCurType.setText(info);
                if (info != null && info.equals("CNY")) {
                        tvUnit.setVisibility(View.VISIBLE);
                }else{
                    tvUnit.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 提交前判断输入是否为空
     *
     * @return
     */
    boolean valid() {
        if (TextUtils.isEmpty(cetName.getText().toString())) {
            toastShow("请输入房产名称");
            return false;
        } else if(TextUtils.isEmpty(cetAmount.getText().toString())){
            toastShow("请输入预估价格");
            return false;
        }else {
            return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isEdit) {
            StatService.onPageStart(mActivity, "编辑房产页面");
        }else{
            StatService.onPageStart(mActivity, "添加房产页面");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ImeUtil.hideSoftKeyboard(cetName);

        if(isEdit) {
            StatService.onPageEnd(mActivity, "编辑房产页面");
        }else{
            StatService.onPageEnd(mActivity, "添加房产页面");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            StatService.onEvent(mActivity,"点击房产资产编辑页返回按钮","放弃房产资产编辑",1);
            ActivityUtils.finishActivity(mActivity);
            ImeUtil.hideSoftKeyboard(cetName);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
