package com.beidou.ybz.accountbook.mvp.view;

import com.beidou.ybz.accountbook.mvp.main.BaseView;

/**
 * Author: ${Supreme} on 2017/12/13
 * QQ:977594142
 * E-mail:977594142@qq.com
 * module:
 */

public interface GetPiccodeView <T> extends BaseView {

    void getPiccodeSuccess(T model);
    void getPiccodeFail(String msg);

}
