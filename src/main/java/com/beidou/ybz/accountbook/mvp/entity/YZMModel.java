package com.beidou.ybz.accountbook.mvp.entity;

/**
 * Author: xu.yang on 2017/12/4
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:获取验证码
 */
public class YZMModel {
    /**
     * mobile :
     * validType : 1
     */
    private String mobile;
    private String validType;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getValidType() {
        return validType;
    }

    public void setValidType(String validType) {
        this.validType = validType;
    }
}
