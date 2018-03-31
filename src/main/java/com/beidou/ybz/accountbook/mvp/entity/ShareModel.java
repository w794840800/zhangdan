package com.beidou.ybz.accountbook.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: xu.yang on 2018/1/15
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module: 分享内容Model
 */
public class ShareModel implements Parcelable {
    private String title;//标题
    private String content;//内容简介
    private String htmlurl;//跳转外链
    private String imgurl;//小图标url

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtmlurl() {
        return htmlurl;
    }

    public void setHtmlurl(String htmlurl) {
        this.htmlurl = htmlurl;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.htmlurl);
        dest.writeString(this.imgurl);
    }

    public ShareModel() {
    }

    protected ShareModel(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        this.htmlurl = in.readString();
        this.imgurl = in.readString();
    }

    public static final Parcelable.Creator<ShareModel> CREATOR = new Parcelable.Creator<ShareModel>() {
        @Override
        public ShareModel createFromParcel(Parcel source) {
            return new ShareModel(source);
        }

        @Override
        public ShareModel[] newArray(int size) {
            return new ShareModel[size];
        }
    };
}
