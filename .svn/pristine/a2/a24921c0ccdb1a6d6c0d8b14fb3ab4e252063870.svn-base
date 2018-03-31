package com.beidou.ybz.accountbook.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.SharePreferenceUtil;
import com.beidou.ybz.accountbook.util.ToastUtils;
import com.beidou.ybz.accountbook.widget.LoadingDialog;
import com.tapadoo.alerter.Alerter;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by WuXiaolong on 2015/9/23.
 * github:https://github.com/WuXiaolong/
 * 微信公众号：吴小龙同学
 * 个人博客：http://wuxiaolong.me/
 */
public class BaseActivity extends SwipeBackActivity {
    public Activity mActivity;
    private CompositeSubscription mCompositeSubscription;
    private List<Call> calls;
    //    public Alerter mAlerter;
    public SharePreferenceUtil spUtils;
    private AlertDialog alertDialog;
    public static boolean isActive = true;
    private LoadingDialog loadingDialog;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {//SOFT_INPUT_STATE_HIDDEN
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);//软键盘不自动弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置始终为竖屏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivity = this;
        super.setContentView(layoutResID);
//        ActivityUtils.setStatusBar(BaseActivity.this);

        ButterKnife.bind(this);
        loadingDialog = new LoadingDialog(mActivity);
        spUtils = new SharePreferenceUtil(mActivity, "xinliangbao");
    }

    @Override
    public void setContentView(View view) {
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置始终为竖屏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(view);
//        ActivityUtils.setStatusBar(BaseActivity.this);
        ButterKnife.bind(this);
        mActivity = this;
        spUtils = new SharePreferenceUtil(mActivity, "xinliangbao");
        loadingDialog = new LoadingDialog(mActivity);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
//        ActivityUtils.setStatusBar(BaseActivity.this);
        ButterKnife.bind(this);
        mActivity = this;
        spUtils = new SharePreferenceUtil(mActivity, "xinliangbao");
        loadingDialog = new LoadingDialog(mActivity);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (!isAppOnFreground()) {
            Log.d("应用是否在后台", "在后台");
            isActive = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        /**
         * umeng统计语句
         */
        MobclickAgent.onResume(this);

        if (!isActive) {
            //从后台唤醒
            Log.d("从后台唤醒", "从后台唤醒");
            isActive = true;
            if (spUtils == null) {
                spUtils = new SharePreferenceUtil(this, "xinliangbao");
            }
           /* if (spUtils.getIsGesture()) {
                Intent n = new Intent(this, GestureValidActivity.class);
                n.putExtra("flag", "homekey");
                startActivity(n);
                overridePendingTransition(R.anim.left_in, 0);
            }*/
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        callCancel();
        onUnsubscribe();
        super.onDestroy();
    }

    public void addCalls(Call call) {
        if (calls == null) {
            calls = new ArrayList<>();
        }
        calls.add(call);
    }

    private void callCancel() {
        if (calls != null && calls.size() > 0) {
            for (Call call : calls) {
                if (!call.isCanceled())
                    call.cancel();
            }
            calls.clear();
        }
    }


    /**
     * 是否在后台
     *
     * @return
     */
    public boolean isAppOnFreground() {
        ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        String curPackageName = getApplicationContext().getPackageName();
        List<ActivityManager.RunningAppProcessInfo> app = am.getRunningAppProcesses();
        if (app == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo a : app) {
            if (a.processName.equals(curPackageName) &&
                    a.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
        /*ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        if(!TextUtils.isEmpty(curPackageName)&&curPackageName.equals(getPackageName())){
            return true;
        }
        return false;*/
    }


    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
            mCompositeSubscription.unsubscribe();
    }

    public void toastShow(int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        ToastUtils.toast(mActivity, resId);
    }

    public void toastShow(String resId, int id) {
        ToastUtils.toast(mActivity, id, resId);
    }

    public void AlerterSuccess(String text) {
        Alerter.create(this)
                .setIcon(R.drawable.gou_toast)
                .setBackgroundColor(R.color.success)
                .setText(text)
                .show();
    }

    public void AlerterFailed(String text) {
        Alerter.create(this)
                .setIcon(R.drawable.alerter_ic_notifications)
                .setBackgroundColor(R.color.colorAccent)
                .setText(text)
                .show();
    }

    public ProgressDialog progressDialog;

    public ProgressDialog showProgressDialog() {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage("加载中...");
        progressDialog.show();
        return progressDialog;
    }

    public ProgressDialog showProgressDialog(CharSequence message) {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    public void showLoadingDialog() {
        if (loadingDialog != null && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    public void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            ActivityUtils.finishActivity(mActivity);
        }
        return super.onKeyDown(keyCode, event);
    }
}
