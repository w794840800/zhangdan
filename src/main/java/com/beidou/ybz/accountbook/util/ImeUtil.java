package com.beidou.ybz.accountbook.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**非著名程序员提供的软键盘解决方案
 * Created by bob on 2017/3/31.
 */
public class ImeUtil {
    /**
     * 显示和隐藏软键盘 View ： EditText、TextView isShow : true = show , false = hide
     *
     * @param context
     * @param view
     * @param isShow
     */
    public static void popSoftKeyboard(Context context, View view,
                                       boolean isShow) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            view.requestFocus();
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        } else {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 弹起软键盘
     */
    public static void showSoftInput(View view,Activity activity){
        InputMethodManager inputMethodManager=(InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        inputMethodManager.showSoftInput(view,InputMethodManager.SHOW_FORCED);
    }

    /**
     * 显示软键盘
     * 当页面没有创建成功时调用无效果，可以通过View.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                            ImeUtil.showSoftKeyboard(etUser);
                                                            }
                                                            },500);
     延时即可
     * @param view
     */
    public static void showSoftKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public static void hideSoftKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
