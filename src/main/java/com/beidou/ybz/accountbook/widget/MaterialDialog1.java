package com.beidou.ybz.accountbook.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;


//import android.support.v7.app.AlertDialog;


/**
 * Created by drakeet on 9/28/14.(新版本更新去掉pandding弹出框)
 */
public class MaterialDialog1 {

    private final static int BUTTON_BOTTOM = 9;
    private final static int BUTTON_TOP = 9;

    private boolean mCancel;
    private Context mContext;
    private AlertDialog mAlertDialog;
    private MaterialDialog1.Builder mBuilder;
    private View mView;
    private int mTitleResId;
    private CharSequence mTitle;
    private CharSequence mExtraMessage;
    private int mMessageResId;
    private CharSequence mMessage;
    private Button mPositiveButton;
    private LinearLayout.LayoutParams mLayoutParams;
    private ImageView mNegativeButton,ivHuizhangClose;
    private boolean mHasShow = false;
    private int mBackgroundResId = -1;
    private Drawable mBackgroundDrawable;
    private View mMessageContentView;
    private int mMessageContentViewResId;
    private DialogInterface.OnDismissListener mOnDismissListener;
    private int pId = -1, nId = -1,hId = -1;
    private String pText, nText,hText;
    View.OnClickListener pListener, nListener;


    public MaterialDialog1(Context context) {
        this.mContext = context;
    }


    public void show() {
        if (!mHasShow) {
            mBuilder = new Builder();
        } else {
            mAlertDialog.show();
        }
        mHasShow = true;
    }


    public MaterialDialog1 setView(View view) {
        mView = view;
        if (mBuilder != null) {
            mBuilder.setView(view);
        }
        return this;
    }


    public MaterialDialog1 setContentView(View view) {
        mMessageContentView = view;
        mMessageContentViewResId = 0;
        if (mBuilder != null) {
            mBuilder.setContentView(mMessageContentView);
        }
        return this;
    }


    /**
     * Set a custom view resource to be the contents of the dialog.
     *
     * @param layoutResId resource ID to be inflated
     */
    public MaterialDialog1 setContentView(int layoutResId) {
        mMessageContentViewResId = layoutResId;
        mMessageContentView = null;
        if (mBuilder != null) {
            mBuilder.setContentView(layoutResId);
        }
        return this;
    }


    public MaterialDialog1 setBackground(Drawable drawable) {
        mBackgroundDrawable = drawable;
        if (mBuilder != null) {
            mBuilder.setBackground(mBackgroundDrawable);
        }
        return this;
    }


    public MaterialDialog1 setBackgroundResource(int resId) {
        mBackgroundResId = resId;
        if (mBuilder != null) {
            mBuilder.setBackgroundResource(mBackgroundResId);
        }
        return this;
    }


    public void dismiss() {
        mAlertDialog.dismiss();
    }

    public boolean isShow(){
        return mAlertDialog.isShowing();
    }

    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    private static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }


    public MaterialDialog1 setTitle(int resId) {
        mTitleResId = resId;
        if (mBuilder != null) {
            mBuilder.setTitle(resId);
        }
        return this;
    }


    public MaterialDialog1 setTitle(CharSequence title) {
        mTitle = title;
        if (mBuilder != null) {
            mBuilder.setTitle(title);
        }
        return this;
    }


    public MaterialDialog1 setExtraMessage(CharSequence extraMessage) {
        mExtraMessage = extraMessage;
        if (mBuilder != null) {
            mBuilder.setExtra(extraMessage);
        }
        return this;
    }


    public MaterialDialog1 setMessage(int resId) {
        mMessageResId = resId;
        if (mBuilder != null) {
            mBuilder.setMessage(resId);
        }
        return this;
    }


    public MaterialDialog1 setMessage(CharSequence message) {
        mMessage = message;
        if (mBuilder != null) {
            mBuilder.setMessage(message);
        }
        return this;
    }


    public MaterialDialog1 setPositiveButton(int resId, final View.OnClickListener listener) {
        this.pId = resId;
        this.pListener = listener;
        return this;
    }


    public Button getPositiveButton() {
        return mPositiveButton;
    }

    public void setPositiveButtonEnable(boolean enable){
        if(mPositiveButton != null) {
            if (enable) {
                mPositiveButton.setBackgroundResource(R.drawable.bg1);
                mPositiveButton.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            } else {
                mPositiveButton.setBackgroundResource(R.drawable.bg_unenabled);
                mPositiveButton.setTextColor(mContext.getResources().getColor(R.color.stock_gray));
            }
            mPositiveButton.setEnabled(enable);
        }
    }


    public ImageView getNegativeButton() {
        return mNegativeButton;
    }


    public MaterialDialog1 setPositiveButton(String text, final View.OnClickListener listener) {
        this.pText = text;
        this.pListener = listener;
        return this;
    }


    public MaterialDialog1 setNegativeButton(int resId, final View.OnClickListener listener) {
        this.nId = resId;
        this.nListener = listener;
        return this;
    }


    public MaterialDialog1 setNegativeButton(String text, final View.OnClickListener listener) {
        this.nText = text;
        this.nListener = listener;
        return this;
    }

    public MaterialDialog1 setHuizhangCloseButton(String text, final View.OnClickListener listener) {
        this.hText = text;
        this.nListener = listener;
        return this;
    }


    /**
     * Sets whether this dialog is canceled when touched outside the window's
     * bounds OR pressed the back key. If setting to true, the dialog is
     * set to be cancelable if not
     * already set.
     *
     * @param cancel Whether the dialog should be canceled when touched outside
     * the window OR pressed the back key.
     */
    public MaterialDialog1 setCanceledOnTouchOutside(boolean cancel) {
        this.mCancel = cancel;
        if (mBuilder != null) {
            mBuilder.setCanceledOnTouchOutside(mCancel);
        }
        return this;
    }


    public MaterialDialog1 setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
        return this;
    }


    private class Builder {

        private TextView mTitleView;
        private RelativeLayout rlTitle;
        private View viewLine;
        private TextView mExtramessageView;
        private ViewGroup mMessageContentRoot;
        private TextView mMessageView;
        private Window mAlertDialogWindow;
        private LinearLayout mButtonLayout;


        private Builder() {
            mAlertDialog = new AlertDialog.Builder(mContext).create();
            mAlertDialog.show();

            mAlertDialog.getWindow()
                        .clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//            mAlertDialog.getWindow()
//                        .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_STATE);
            mAlertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            mAlertDialogWindow = mAlertDialog.getWindow();
            mAlertDialogWindow.setBackgroundDrawable(
                    new ColorDrawable(Color.TRANSPARENT));
            mAlertDialogWindow.setWindowAnimations(R.style.dialogAnimation);
            View contentView = LayoutInflater.from(mContext)
                                             .inflate(R.layout.layout_material_dialog1, null);
            contentView.setFocusable(true);
            contentView.setFocusableInTouchMode(true);

            mAlertDialogWindow.setBackgroundDrawableResource(R.drawable.material_dialog_window);

            mAlertDialogWindow.setContentView(contentView);

            mTitleView = (TextView) mAlertDialogWindow.findViewById(R.id.title);
            rlTitle = (RelativeLayout) mAlertDialogWindow.findViewById(R.id.rlTitle);
            viewLine = mAlertDialogWindow.findViewById(R.id.viewLine);
            mExtramessageView = (TextView) mAlertDialogWindow.findViewById(R.id.extramessage);
            mMessageView = (TextView) mAlertDialogWindow.findViewById(R.id.message);
            mButtonLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.buttonLayout);
            mPositiveButton = (Button) mButtonLayout.findViewById(R.id.btn_p);
            mNegativeButton = (ImageView) mAlertDialogWindow.findViewById(R.id.ivClose);
            ivHuizhangClose = (ImageView) mAlertDialogWindow.findViewById(R.id.ivHuizhangClose);
            mMessageContentRoot = (ViewGroup) mAlertDialogWindow.findViewById(
                    R.id.message_content_root);
            if (mView != null) {
                LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(
                        R.id.contentView);
                linearLayout.removeAllViews();
                linearLayout.addView(mView);
            }
            if (mTitleResId != 0) {
                setTitle(mTitleResId);
            }
            if (mTitle != null) {
                setTitle(mTitle);
            }
            if (mTitle == null && mTitleResId == 0) {
                mTitleView.setVisibility(View.GONE);
                rlTitle.setVisibility(View.GONE);
                viewLine.setVisibility(View.GONE);
            }
            if (mExtraMessage != null) {
                setExtra(mExtraMessage);
            }

            mPositiveButton.setBackgroundResource(R.drawable.bg_unenabled);
            mPositiveButton.setTextColor(mContext.getResources().getColor(R.color.stock_gray));


            if (mExtraMessage == null ) {
                mExtramessageView.setVisibility(View.GONE);
            }
            if (mMessageResId != 0) {
                setMessage(mMessageResId);
            }
            if (mMessage != null) {
                setMessage(mMessage);
            }
            if (pId != -1) {
                mPositiveButton.setVisibility(View.VISIBLE);
                mPositiveButton.setText(pId);
                mPositiveButton.setOnClickListener(pListener);
                if (isLollipop()) {
                    mPositiveButton.setElevation(0);
                }
            }
            if (nId != -1) {
                mNegativeButton.setVisibility(View.VISIBLE);
//                mNegativeButton.setText(nId);
                mNegativeButton.setOnClickListener(nListener);
                if (isLollipop()) {
                    mNegativeButton.setElevation(0);
                }
            }

            if (hId != -1) {
                ivHuizhangClose.setVisibility(View.VISIBLE);
//                mNegativeButton.setText(nId);
                ivHuizhangClose.setOnClickListener(nListener);
                if (isLollipop()) {
                    ivHuizhangClose.setElevation(0);
                }
            }

            if (!isNullOrEmpty(hText)) {
                ivHuizhangClose.setVisibility(View.VISIBLE);
                ivHuizhangClose.setOnClickListener(nListener);
                if (isLollipop()) {
                    ivHuizhangClose.setElevation(0);
                }
            }

            if (!isNullOrEmpty(pText)) {
                mPositiveButton.setVisibility(View.VISIBLE);
                mPositiveButton.setText(pText);
                mPositiveButton.setOnClickListener(pListener);
                if (isLollipop()) {
                    mPositiveButton.setElevation(0);
                }
            }

            if (!isNullOrEmpty(nText)) {
                mNegativeButton.setVisibility(View.VISIBLE);
//                mNegativeButton.setText(nText);
                mNegativeButton.setOnClickListener(nListener);
                if (isLollipop()) {
                    mNegativeButton.setElevation(0);
                }
            }
            if (isNullOrEmpty(pText) && pId == -1) {
                mPositiveButton.setVisibility(View.GONE);
            }

            if (isNullOrEmpty(hText) && hId == -1) {
                ivHuizhangClose.setVisibility(View.GONE);
            }

            if (isNullOrEmpty(nText) && nId == -1 && mNegativeButton != null) {
                mNegativeButton.setVisibility(View.GONE);
            }
            if (mBackgroundResId != -1) {
                LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(
                        R.id.material_background);
                linearLayout.setBackgroundResource(mBackgroundResId);
            }
            if (mBackgroundDrawable != null) {
                LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(
                        R.id.material_background);
                linearLayout.setBackground(mBackgroundDrawable);
            }

            if (mMessageContentView != null) {
                this.setContentView(mMessageContentView);
            } else if (mMessageContentViewResId != 0) {
                this.setContentView(mMessageContentViewResId);
            }
            mAlertDialog.setCanceledOnTouchOutside(mCancel);
            mAlertDialog.setCancelable(mCancel);
            if (mOnDismissListener != null) {
                mAlertDialog.setOnDismissListener(mOnDismissListener);
            }
        }


        public void setTitle(int resId) {
            mTitleView.setText(resId);
        }


        public void setTitle(CharSequence title) {
            mTitleView.setText(title);
        }

        public void setExtra(CharSequence extra) {
            mExtramessageView.setText(extra);
        }


        public void setMessage(int resId) {
            if (mMessageView != null) {
                mMessageView.setText(resId);
            }
        }


        public void setMessage(CharSequence message) {
            if (mMessageView != null) {
                mMessageView.setText(message);
            }
        }


        /**
         * set positive button
         *
         * @param text the name of button
         */
        public void setPositiveButton(String text, final View.OnClickListener listener) {
            Button button = new Button(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.material_card);
            button.setTextColor(Color.argb(255, 35, 159, 242));
            button.setText(text);
            button.setGravity(Gravity.CENTER);
            button.setTextSize(14);
            button.setPadding(dip2px(12), 0, dip2px(32), dip2px(BUTTON_BOTTOM));
            button.setOnClickListener(listener);
            mButtonLayout.addView(button);
        }


        /**
         * set negative button
         *
         * @param text the name of button
         */
        public void setNegativeButton(String text, final View.OnClickListener listener) {
            Button button = new Button(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.material_card);
            button.setText(text);
            button.setTextColor(Color.argb(222, 0, 0, 0));
            button.setTextSize(14);
            button.setGravity(Gravity.CENTER);
            button.setPadding(0, 0, 0, dip2px(8));
            button.setOnClickListener(listener);
            if (mButtonLayout.getChildCount() > 0) {
                params.setMargins(20, 0, 10, dip2px(BUTTON_BOTTOM));
                button.setLayoutParams(params);
                mButtonLayout.addView(button, 1);
            } else {
                button.setLayoutParams(params);
                mButtonLayout.addView(button);
            }
        }


        public void setView(View view) {
            LinearLayout l = (LinearLayout) mAlertDialogWindow.findViewById(R.id.contentView);
            l.removeAllViews();
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);

            view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override public void onFocusChange(View v, boolean hasFocus) {
                    mAlertDialogWindow.setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    // show imm
                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                            InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
            });

            l.addView(view);

            if (view instanceof ViewGroup) {

                ViewGroup viewGroup = (ViewGroup) view;

                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    if (viewGroup.getChildAt(i) instanceof EditText) {
                        EditText editText = (EditText) viewGroup.getChildAt(i);
                        editText.setFocusable(true);
                        editText.requestFocus();
                        editText.setFocusableInTouchMode(true);
                    }
                }
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    if (viewGroup.getChildAt(i) instanceof AutoCompleteTextView) {
                        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) viewGroup
                                .getChildAt(i);
                        autoCompleteTextView.setFocusable(true);
                        autoCompleteTextView.requestFocus();
                        autoCompleteTextView.setFocusableInTouchMode(true);
                    }
                }
            }
        }


        public void setContentView(View contentView) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            contentView.setLayoutParams(layoutParams);
            if (contentView instanceof ListView) {
                setListViewHeightBasedOnChildren((ListView) contentView);
            }
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(
                    R.id.message_content_view);
            if (linearLayout != null) {
                linearLayout.removeAllViews();
                linearLayout.addView(contentView);
            }
            for (int i = 0; i < (linearLayout != null ? linearLayout.getChildCount() : 0); i++) {
                if (linearLayout.getChildAt(i) instanceof AutoCompleteTextView) {
                    AutoCompleteTextView autoCompleteTextView
                            = (AutoCompleteTextView) linearLayout.getChildAt(i);
                    autoCompleteTextView.setFocusable(true);
                    autoCompleteTextView.requestFocus();
                    autoCompleteTextView.setFocusableInTouchMode(true);
                }
            }
        }


        /**
         * Set a custom view resource to be the contents of the dialog. The
         * resource will be inflated into a ScrollView.
         *
         * @param layoutResId resource ID to be inflated
         */
        public void setContentView(int layoutResId) {
            mMessageContentRoot.removeAllViews();
            // Not setting this to the other content view because user has defined their own
            // layout params, and we don't want to overwrite those.
            LayoutInflater.from(mMessageContentRoot.getContext())
                          .inflate(layoutResId, mMessageContentRoot);
        }


        public void setBackground(Drawable drawable) {
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(
                    R.id.material_background);
            linearLayout.setBackground(drawable);
        }


        public void setBackgroundResource(int resId) {
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(
                    R.id.material_background);
            linearLayout.setBackgroundResource(resId);
        }


        public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            mAlertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            mAlertDialog.setCancelable(canceledOnTouchOutside);
        }
    }


    private boolean isNullOrEmpty(String nText) {
        return nText == null || nText.isEmpty();
    }


    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}

