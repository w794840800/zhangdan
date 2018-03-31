package com.beidou.ybz.accountbook.mvp.entity;

/**
 * Author: xu.yang on 2017/12/10
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:通讯录列表Model
 */
public class ContactsModel {

    /**
     * name : 鲍安
     * phone : 05566735293
     */

    private String name;
    private String phone;
    private String initials;

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
