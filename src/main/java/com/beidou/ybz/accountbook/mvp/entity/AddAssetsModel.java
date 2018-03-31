package com.beidou.ybz.accountbook.mvp.entity;

/**
 * Author: xu.yang on 2017/12/5
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:添加资产列表model
 */
public class AddAssetsModel {

    /**
     * title : 基金
     * imageResource : ss
     */
//    "amount": 0,  "nameValue": "0015","nameView": "互联网账号估值","syrs": "200"
    private String title;
    private String amount;
    private String nameValue;
    private String nameView;
    private String syrs;
    private int imageResource;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNameValue() {
        return nameValue;
    }

    public void setNameValue(String nameValue) {
        this.nameValue = nameValue;
    }

    public String getNameView() {
        return nameView;
    }

    public void setNameView(String nameView) {
        this.nameView = nameView;
    }

    public String getSyrs() {
        return syrs;
    }

    public void setSyrs(String syrs) {
        this.syrs = syrs;
    }

    public AddAssetsModel(String title, int imageResource){
        this.title = title;
        this.imageResource = imageResource;
    }

    public AddAssetsModel(String amount, String nameView, String syrs, int imageResource) {
        this.amount = amount;
        this.nameView = nameView;
        this.syrs = syrs;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
