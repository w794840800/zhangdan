package com.beidou.ybz.accountbook.mvp.entity;

/**
 * Author: xu.yang on 2017/12/9
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:加密接口服务器响应model
 */
public class EncryptedResponseModel {

    /**
     * encMsg : jo5+iQiC8rp5I73Dlq+kuAISufnBpcSkpcub9I4145wmmjM2BxqOw/yKMcR4xjKgpgcxRtkKNJle
     1pGh/cr1ExYoa/XBCeVp1pnqlIkpICoNYjcYjw4BHgysm0RyH8drpvJiPw+X2ESDUo7bvh1nqQ==
     * signMsg : 5c3043f3012a4296ff158eb15ddf248b
     * msgType : 2
     */

    private String encMsg;
    private String signMsg;
    private String msgType;

    public String getEncMsg() {
        return encMsg;
    }

    public void setEncMsg(String encMsg) {
        this.encMsg = encMsg;
    }

    public String getSignMsg() {
        return signMsg;
    }

    public void setSignMsg(String signMsg) {
        this.signMsg = signMsg;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
