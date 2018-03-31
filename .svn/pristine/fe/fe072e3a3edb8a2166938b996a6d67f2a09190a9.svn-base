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
import rx.functions.Func6;

/**
 * Author: xu.yang on 2017/12/4
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module: 添加固定收益
 */
public class AddFixedIncome extends MvpActivity<CommonPresenter> implements CommonView<SercetKeyOverdueModel> {
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
    @Bind(R.id.btnSave)
    Button btnSave;
    @Bind(R.id.cetName)
    ClearEditText cetName;
    @Bind(R.id.cetMoney)
    ClearEditText cetMoney;
    @Bind(R.id.cetBrokerName)
    ClearEditText cetBrokerName;
    @Bind(R.id.cetYearIncome)
    ClearEditText cetYearIncome;
    @Bind(R.id.cetPeriodsNumber)
    ClearEditText cetPeriodsNumber;
    @Bind(R.id.cetMemo)
    ClearEditText cetMemo;
    private boolean isMoreShow;
    private int height;
    private ValueAnimator valueAnimatorShow, valueAnimatorHide;
    private String encMsg, signMsg, name, memo, brokerName, yearIncome, investmentTerm;
    private String amount, flag, id, from;
    private boolean isEdit;
    private boolean isFromList;
    private boolean isFromAddasset;//来自首次添加页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfixedincome);
        ButterKnife.bind(this);

        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatService.onEvent(mActivity, "点击固定收益资产编辑页返回按钮", "放弃固定收益资产编辑", 1);
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
            amount = in.getStringExtra("amount");
//            brokerName,yearIncome,investmentTerm
            brokerName = in.getStringExtra("brokerName");
            yearIncome = in.getStringExtra("yearIncome");
            investmentTerm = in.getStringExtra("investmentTerm");

            from = in.getStringExtra("from");
        }

        if (memo != null && !TextUtils.isEmpty(memo)
                || brokerName != null && !TextUtils.isEmpty(brokerName)
                || yearIncome != null && !TextUtils.isEmpty(yearIncome)
                || investmentTerm != null && !TextUtils.isEmpty(investmentTerm)) {
            cetName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    valueAnimatorShow.start();
                    isMoreShow = true;
                    ivShowMore.animate().rotation(180f).setDuration(400).start();
                }
            }, 300);
        }

        if (name != null && !TextUtils.isEmpty(name)) {
            isEdit = true;
        } else {
            isEdit = false;
        }
        LogUtils.logd("isEdit:" + isEdit);
        if (isEdit) {
            tvTitle.setText("编辑固定收益");
        } else {
            tvTitle.setText("添加固定收益");
        }

        if (from != null && from.equals("list")) {
            isFromList = true;
        } else {
            isFromList = false;
        }
        if (from != null && from.equals("addasset")) {
            isFromAddasset = true;
        } else {
            isFromAddasset = false;
        }

        try {
            cetName.setText(name);
            cetName.setSelection(name.length());
            cetMoney.setText(amount);
            cetMemo.setText(memo);
            cetBrokerName.setText(brokerName);
            cetYearIncome.setText(yearIncome);
            cetPeriodsNumber.setText(investmentTerm);
            if (!TextUtils.isEmpty(cetMoney.getText().toString())) {
                cetMoney.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void initView() {
        bindViewByRxBinding();
        //计算llMore的高度


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
        Observable<CharSequence> Observable3 = RxTextView.textChanges(cetBrokerName);
        Observable<CharSequence> Observable4 = RxTextView.textChanges(cetPeriodsNumber);
        Observable<CharSequence> Observable5 = RxTextView.textChanges(cetYearIncome);
        Observable<CharSequence> Observable6 = RxTextView.textChanges(cetMemo);

        Observable.combineLatest(Observable1, Observable2, Observable3, Observable4, Observable5, Observable6,
                new Func6<CharSequence, CharSequence, CharSequence, CharSequence, CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence string1, CharSequence string2, CharSequence string3, CharSequence string4, CharSequence string5, CharSequence string6) {
                        return !Utils.isTextEmpty(string1.toString()) || !Utils.isTextEmpty(string2.toString())
                                || !Utils.isTextEmpty(string3.toString()) || !Utils.isTextEmpty(string4.toString())
                                || !Utils.isTextEmpty(string5.toString()) || !Utils.isTextEmpty(string6.toString());
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
                    ActivityUtils.startActivity(mActivity, FixedIncomeActivity.class);//从详情页跳转过来编辑成功后，回到列表页
                    ActivityUtils.finishActivity(mActivity);
                }
            }, ToastUtils.toastTime);

        } else {
            toastShow("添加成功", R.drawable.gou_toast);
            cetName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isFromList) {
                        ActivityUtils.finishActivity(mActivity);
                    } else {
                        Intent in = new Intent(mActivity, FixedIncomeActivity.class);
                        in.putExtra("from", "add");
                        startActivity(in);
                        overridePendingTransition(R.anim.left_in, 0);
                    }
                }
            }, ToastUtils.toastTime);

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

    @OnClick({R.id.llShowMore, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llShowMore:
                StatService.onEvent(mActivity, "添加固定收益资产时点击更多", "添加更多固定收益信息", 1);
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
                if (isEdit) {
                    StatService.onEvent(mActivity, "点击固定收益资产编辑页确认按钮", "编辑固定收益资产", 1);
                } else if (isFromAddasset) {
                    StatService.onEvent(mActivity, "点击固定收益资产添加页确认按钮", "首次新增固定收益", 1);
                } else {
                    StatService.onEvent(mActivity, "固定收益列表进入添加页并点击确认按钮", "确认新增固定收益", 1);
                }
                if (valid()) {
                    save();
                }
                break;
        }
    }

    void save() {
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());
        requestModel.setId(id);
        requestModel.setProductName(cetName.getText().toString());
        requestModel.setAmount(cetMoney.getText().toString());
        requestModel.setBrokerName(cetBrokerName.getText().toString());
        requestModel.setYearIncome(cetYearIncome.getText().toString());
        requestModel.setAmount(cetMoney.getText().toString());
        requestModel.setInvestmentTerm(cetPeriodsNumber.getText().toString());
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
            mvpPresenter.updatetrust(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        } else {
            mvpPresenter.addtrust(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        }
    }

    /**
     * 提交前判断输入是否为空
     *
     * @return
     */
    boolean valid() {
        if (TextUtils.isEmpty(cetName.getText().toString())) {
            toastShow("请输入产品名称");
            return false;
        } else if (TextUtils.isEmpty(cetMoney.getText().toString())) {
            toastShow("请输入投资金额");
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isEdit) {
            StatService.onPageStart(mActivity, "编辑固定收益页面");
        } else {
            StatService.onPageStart(mActivity, "添加固定收益页面");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ImeUtil.hideSoftKeyboard(cetName);

        if (isEdit) {
            StatService.onPageEnd(mActivity, "编辑固定收益页面");
        } else {
            StatService.onPageEnd(mActivity, "添加固定收益页面");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            StatService.onEvent(mActivity, "点击固定收益资产编辑页返回按钮", "放弃固定收益资产编辑", 1);
            ActivityUtils.finishActivity(mActivity);
            ImeUtil.hideSoftKeyboard(cetName);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
