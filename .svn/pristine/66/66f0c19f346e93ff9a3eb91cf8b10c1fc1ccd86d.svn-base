package com.beidou.ybz.accountbook.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: ${Supreme} on 2017/12/12
 * QQ:977594142
 * E-mail:977594142@qq.com
 * module:
 */

public class HuizhangSecondClassModel implements Parcelable {

    /**
     * body : {"showList":[{"activityUrl":"","badgeDetailId":3,"badgeDetailName":"糊涂虫","badgeId":1,"content":"精于计算你就不会被别人牵头转","getStatus":1,"getWay":"学习1次财商课程/音频可获得","picShowUrl":"http://static.360caifu.test/ybzybz/badge/ae3eaf479a2640ecabca65d84d9ca76f.png"},{"activityUrl":"","badgeDetailId":4,"badgeDetailName":"门外汉","badgeId":1,"content":"风险来自于你不知道自己在做什么","getStatus":1,"getWay":"学习10次财商课程/音频可获得","picShowUrl":"http://static.360caifu.test/ybzybz/badge/8a67cf6d5c9e4dd9b9b97208434104a2.png"},{"activityUrl":"","badgeDetailId":5,"badgeDetailName":"潜力股","badgeId":1,"content":"多少人终其一生，都没能把他们潜能的0.1%用完","getStatus":1,"getWay":"学习30次财商课程/音频可获得","picShowUrl":"http://static.360caifu.test/ybzybz/badge/8a67cf6d5c9e4dd9b9b97208434104a2.png"},{"activityUrl":"","badgeDetailId":6,"badgeDetailName":"千里马","badgeId":1,"content":"投资需要耐心，一个亿的\u201c小目标\u201d也需要日积月累","getStatus":1,"getWay":"学习200次财商课程/音频可获得","picShowUrl":"http://static.360caifu.test/ybzybz/badge/8a67cf6d5c9e4dd9b9b97208434104a2.png"},{"activityUrl":"","badgeDetailId":7,"badgeDetailName":"三连击","badgeId":2,"content":"我们之间的故事，有本账就够了","getStatus":1,"getWay":"连续3天登录APP","picShowUrl":"http://static.360caifu.test/ybz"},{"activityUrl":"","badgeDetailId":8,"badgeDetailName":"打卡大咖","badgeId":2,"content":"荣耀是因为对爱的坚持","getStatus":1,"getWay":"连续5天登录APP","picShowUrl":"http://static.360caifu.test/ybzybz/badge/b1b4de7cbeb24be5bdf1d5718e83bc0b.png"},{"activityUrl":"","badgeDetailId":9,"badgeDetailName":"毅力帝","badgeId":2,"content":"锲而不舍，金石可镂","getStatus":1,"getWay":"连续7天登录APP","picShowUrl":"http://static.360caifu.test/ybzybz/badge/c11292b352b14fb5b7ad9316043a1456.png"},{"activityUrl":"","badgeDetailId":10,"badgeDetailName":"每天都有\"有本账\"","badgeId":2,"content":"有你的每天，都是快乐的","getStatus":1,"getWay":"连续60天登录APP","picShowUrl":"http://static.360caifu.test/ybzybz/badge/59f56413812e41719bfb8f2f010a2383.png"}]}
     * header : {"code":"0000","desc":"成功","responseTime":"2018-01-05 19:15:06"}
     */

    private BodyBean body;
    private HeaderBean header;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public static class BodyBean implements Parcelable {
        private List<ShowListBean> showList;

        public List<ShowListBean> getShowList() {
            return showList;
        }

        public void setShowList(List<ShowListBean> showList) {
            this.showList = showList;
        }

        public static class ShowListBean implements Parcelable {
            /**
             * activityUrl :
             * badgeDetailId : 3
             * badgeDetailName : 糊涂虫
             * badgeId : 1
             * content : 精于计算你就不会被别人牵头转
             * getStatus : 1
             * getWay : 学习1次财商课程/音频可获得
             * picShowUrl : http://static.360caifu.test/ybzybz/badge/ae3eaf479a2640ecabca65d84d9ca76f.png
             */

            private String activityUrl;
            private String badgeDetailId;
            private String badgeDetailName;
            private String badgeId;
            private String content;
            private String getStatus;
            private String getWay;
            private String picShowUrl;

            public String getActivityUrl() {
                return activityUrl;
            }

            public void setActivityUrl(String activityUrl) {
                this.activityUrl = activityUrl;
            }

            public String getBadgeDetailId() {
                return badgeDetailId;
            }

            public void setBadgeDetailId(String badgeDetailId) {
                this.badgeDetailId = badgeDetailId;
            }

            public String getBadgeDetailName() {
                return badgeDetailName;
            }

            public void setBadgeDetailName(String badgeDetailName) {
                this.badgeDetailName = badgeDetailName;
            }

            public String getBadgeId() {
                return badgeId;
            }

            public void setBadgeId(String badgeId) {
                this.badgeId = badgeId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getGetStatus() {
                return getStatus;
            }

            public void setGetStatus(String getStatus) {
                this.getStatus = getStatus;
            }

            public String getGetWay() {
                return getWay;
            }

            public void setGetWay(String getWay) {
                this.getWay = getWay;
            }

            public String getPicShowUrl() {
                return picShowUrl;
            }

            public void setPicShowUrl(String picShowUrl) {
                this.picShowUrl = picShowUrl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.activityUrl);
                dest.writeString(this.badgeDetailId);
                dest.writeString(this.badgeDetailName);
                dest.writeString(this.badgeId);
                dest.writeString(this.content);
                dest.writeString(this.getStatus);
                dest.writeString(this.getWay);
                dest.writeString(this.picShowUrl);
            }

            public ShowListBean() {
            }

            protected ShowListBean(Parcel in) {
                this.activityUrl = in.readString();
                this.badgeDetailId = in.readString();
                this.badgeDetailName = in.readString();
                this.badgeId = in.readString();
                this.content = in.readString();
                this.getStatus = in.readString();
                this.getWay = in.readString();
                this.picShowUrl = in.readString();
            }

            public static final Creator<ShowListBean> CREATOR = new Creator<ShowListBean>() {
                @Override
                public ShowListBean createFromParcel(Parcel source) {
                    return new ShowListBean(source);
                }

                @Override
                public ShowListBean[] newArray(int size) {
                    return new ShowListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.showList);
        }

        public BodyBean() {
        }

        protected BodyBean(Parcel in) {
            this.showList = new ArrayList<ShowListBean>();
            in.readList(this.showList, ShowListBean.class.getClassLoader());
        }

        public static final Creator<BodyBean> CREATOR = new Creator<BodyBean>() {
            @Override
            public BodyBean createFromParcel(Parcel source) {
                return new BodyBean(source);
            }

            @Override
            public BodyBean[] newArray(int size) {
                return new BodyBean[size];
            }
        };
    }

    public static class HeaderBean implements Parcelable {
        /**
         * code : 0000
         * desc : 成功
         * responseTime : 2018-01-05 19:15:06
         */

        private String code;
        private String desc;
        private String responseTime;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getResponseTime() {
            return responseTime;
        }

        public void setResponseTime(String responseTime) {
            this.responseTime = responseTime;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.code);
            dest.writeString(this.desc);
            dest.writeString(this.responseTime);
        }

        public HeaderBean() {
        }

        protected HeaderBean(Parcel in) {
            this.code = in.readString();
            this.desc = in.readString();
            this.responseTime = in.readString();
        }

        public static final Creator<HeaderBean> CREATOR = new Creator<HeaderBean>() {
            @Override
            public HeaderBean createFromParcel(Parcel source) {
                return new HeaderBean(source);
            }

            @Override
            public HeaderBean[] newArray(int size) {
                return new HeaderBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.body, flags);
        dest.writeParcelable(this.header, flags);
    }

    public HuizhangSecondClassModel() {
    }

    protected HuizhangSecondClassModel(Parcel in) {
        this.body = in.readParcelable(BodyBean.class.getClassLoader());
        this.header = in.readParcelable(HeaderBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<HuizhangSecondClassModel> CREATOR = new Parcelable.Creator<HuizhangSecondClassModel>() {
        @Override
        public HuizhangSecondClassModel createFromParcel(Parcel source) {
            return new HuizhangSecondClassModel(source);
        }

        @Override
        public HuizhangSecondClassModel[] newArray(int size) {
            return new HuizhangSecondClassModel[size];
        }
    };
}
