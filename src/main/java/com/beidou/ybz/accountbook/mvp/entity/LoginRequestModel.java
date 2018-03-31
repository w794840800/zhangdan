package com.beidou.ybz.accountbook.mvp.entity;

/**
 * Author: xu.yang on 2017/12/10
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:登录接口请求model
 */
public class LoginRequestModel {

    /**
     * mobile : 0901
     * loginMode : 密钥为空或已过期，请重新申请密钥
     * messageCode : 2017-12-1016: 37: 53
     * Pwd : qqqq
     */

    private String mobile;
    private String loginMode;
    private String messageCode;
    private String pwd;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginMode() {
        return loginMode;
    }

    public void setLoginMode(String loginMode) {
        this.loginMode = loginMode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
