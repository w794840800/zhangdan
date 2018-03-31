package com.beidou.ybz.accountbook.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.beidou.ybz.accountbook.R;


/**
 * Author: xu.yang on 2017/12/6
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:AlertDialog的简单封装
 * http://blog.csdn.net/emperor_rock/article/details/51204426
 */
public class AlertDialogUtils {
    private Context mContext;
    private int layoutId;
    private DialogClickListener dialogClickLintener = null;
    private View view;
    //用于判断显示模式
    public static final int
            SHOW_MOD_YES = 0x00000001,
            SHOW_MOD_NO = 0x00000002;
    private int showMode = SHOW_MOD_YES + SHOW_MOD_NO;
    AlertDialog.Builder build;
    AlertDialog alertDialog;
    private int mPosition;

    public AlertDialogUtils(Context context, int layoutId) {
        this.mContext = context;
        this.layoutId = layoutId;
//        build = new AlertDialog.Builder(context);
        build.setTitle("提示");
        LayoutInflater inflate = LayoutInflater.from(context);
        view = inflate.inflate(layoutId, null);
        build.setView(view);
        build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (dialogClickLintener != null) {
                    dialogClickLintener.clickYes(mPosition);
                }
            }
        });
        build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (dialogClickLintener != null) {
                    dialogClickLintener.clickNo();
                }
            }
        });
//        build.create().show();
    }


    public AlertDialogUtils(Context context) {
        alertDialog = new AlertDialog.Builder(context)
                .setTitle("提示")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (dialogClickLintener != null) {
                            dialogClickLintener.clickNo();
                        }
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (dialogClickLintener != null) {
                            dialogClickLintener.clickYes(mPosition);
                        }
                    }
                })
                .setCancelable(false)
                .create();

        alertDialog.getWindow().setWindowAnimations(R.style.dialogAnimation);
    }

    public AlertDialogUtils(Context context,String data) {
        alertDialog = new AlertDialog.Builder(context)
                .setTitle("提示")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (dialogClickLintener != null) {
                            dialogClickLintener.clickYes(mPosition);
                        }
                    }
                })
                .setCancelable(false)
                .create();

        alertDialog.getWindow().setWindowAnimations(R.style.dialogAnimation);
    }

    public AlertDialogUtils(Context context,boolean data) {
        alertDialog = new AlertDialog.Builder(context)
                .setTitle("提示")
                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (dialogClickLintener != null) {
                            dialogClickLintener.clickYes(mPosition);
                        }
                    }
                })
                .setCancelable(false)
                .create();

        alertDialog.getWindow().setWindowAnimations(R.style.dialogAnimation);
    }

    public void setMessage(String msg) {
        if (alertDialog != null) {
            alertDialog.setMessage(msg);
        }
    }

    public void show() {
        if (alertDialog != null) {
            alertDialog.show();
        }
    }

    public void setPosition(int position) {
        mPosition = position;
    }


    public void setOnDialogClickListener(DialogClickListener dialogClickLintener) {
        this.dialogClickLintener = dialogClickLintener;
    }
}
