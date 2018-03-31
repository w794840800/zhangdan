package com.beidou.ybz.accountbook.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: xu.yang on 2017/12/10
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:添加资产类别（四大类）model
 */
public class AssetClassModel implements Parcelable {


    /**
     * body : {"shcwList":[{"amount":0,"nameValue":"0015","nameView":"互联网账号估值","syrs":"200"},{"amount":0,"nameValue":"0016","nameView":"游戏","syrs":"200"},{"amount":0,"nameValue":"0014","nameView":"域名估值","syrs":"200"},{"amount":0,"nameValue":"9999","nameView":"其它","syrs":"200"}],"tzlcList":[{"amount":0,"nameValue":"0001","nameView":"股票","syrs":"200"},{"amount":0,"nameValue":"0003","nameView":"理财","syrs":"200"},{"amount":0,"nameValue":"0002","nameView":"基金","syrs":"200"},{"amount":0,"nameValue":"0012","nameView":"海外房产","syrs":"200"},{"amount":0,"nameValue":"0009","nameView":"固定收益","syrs":"200"},{"amount":0,"nameValue":"0010","nameView":"私募","syrs":"200"},{"amount":0,"nameValue":"0008","nameView":"股权","syrs":"200"},{"amount":0,"nameValue":"0011","nameView":"保险","syrs":"200"}],"zjzhList":[{"amount":0,"nameValue":"0004","nameView":"银行卡","syrs":"200"},{"amount":0,"nameValue":"0005","nameView":"预付卡","syrs":"200"}],"zwwlList":[{"amount":0,"nameValue":"0017","nameView":"借款","syrs":"200"},{"amount":0,"nameValue":"0007","nameView":"贷款","syrs":"200"},{"amount":0,"nameValue":"0013","nameView":"报销单","syrs":"200"},{"amount":0,"nameValue":"0006","nameView":"欠款","syrs":"200"}]}
     * header : {"code":"0000","desc":"成功","responseTime":"2017-12-16 10:45:10"}
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
        private List<ShcwListBean> shcwList;
        private List<TzlcListBean> tzlcList;
        private List<ZjzhListBean> zjzhList;
        private List<ZwwlListBean> zwwlList;

        public List<ShcwListBean> getShcwList() {
            return shcwList;
        }

        public void setShcwList(List<ShcwListBean> shcwList) {
            this.shcwList = shcwList;
        }

        public List<TzlcListBean> getTzlcList() {
            return tzlcList;
        }

        public void setTzlcList(List<TzlcListBean> tzlcList) {
            this.tzlcList = tzlcList;
        }

        public List<ZjzhListBean> getZjzhList() {
            return zjzhList;
        }

        public void setZjzhList(List<ZjzhListBean> zjzhList) {
            this.zjzhList = zjzhList;
        }

        public List<ZwwlListBean> getZwwlList() {
            return zwwlList;
        }

        public void setZwwlList(List<ZwwlListBean> zwwlList) {
            this.zwwlList = zwwlList;
        }

        public static class ShcwListBean implements Parcelable {
            /**
             * amount : 0
             * nameValue : 0015
             * nameView : 互联网账号估值
             * syrs : 200
             */

            private String amount;
            private String nameValue;
            private String nameView;
            private String syrs;

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.amount);
                dest.writeString(this.nameValue);
                dest.writeString(this.nameView);
                dest.writeString(this.syrs);
            }

            public ShcwListBean() {
            }

            protected ShcwListBean(Parcel in) {
                this.amount = in.readString();
                this.nameValue = in.readString();
                this.nameView = in.readString();
                this.syrs = in.readString();
            }

            public static final Creator<ShcwListBean> CREATOR = new Creator<ShcwListBean>() {
                @Override
                public ShcwListBean createFromParcel(Parcel source) {
                    return new ShcwListBean(source);
                }

                @Override
                public ShcwListBean[] newArray(int size) {
                    return new ShcwListBean[size];
                }
            };
        }

        public static class TzlcListBean implements Parcelable {
            /**
             * amount : 0
             * nameValue : 0001
             * nameView : 股票
             * syrs : 200
             */

            private String amount;
            private String nameValue;
            private String nameView;
            private String syrs;

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.amount);
                dest.writeString(this.nameValue);
                dest.writeString(this.nameView);
                dest.writeString(this.syrs);
            }

            public TzlcListBean() {
            }

            protected TzlcListBean(Parcel in) {
                this.amount = in.readString();
                this.nameValue = in.readString();
                this.nameView = in.readString();
                this.syrs = in.readString();
            }

            public static final Creator<TzlcListBean> CREATOR = new Creator<TzlcListBean>() {
                @Override
                public TzlcListBean createFromParcel(Parcel source) {
                    return new TzlcListBean(source);
                }

                @Override
                public TzlcListBean[] newArray(int size) {
                    return new TzlcListBean[size];
                }
            };
        }

        public static class ZjzhListBean implements Parcelable {
            /**
             * amount : 0
             * nameValue : 0004
             * nameView : 银行卡
             * syrs : 200
             */

            private String amount;
            private String nameValue;
            private String nameView;
            private String syrs;

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.amount);
                dest.writeString(this.nameValue);
                dest.writeString(this.nameView);
                dest.writeString(this.syrs);
            }

            public ZjzhListBean() {
            }

            protected ZjzhListBean(Parcel in) {
                this.amount = in.readString();
                this.nameValue = in.readString();
                this.nameView = in.readString();
                this.syrs = in.readString();
            }

            public static final Creator<ZjzhListBean> CREATOR = new Creator<ZjzhListBean>() {
                @Override
                public ZjzhListBean createFromParcel(Parcel source) {
                    return new ZjzhListBean(source);
                }

                @Override
                public ZjzhListBean[] newArray(int size) {
                    return new ZjzhListBean[size];
                }
            };
        }

        public static class ZwwlListBean implements Parcelable {
            /**
             * amount : 0
             * nameValue : 0017
             * nameView : 借款
             * syrs : 200
             */

            private String amount;
            private String nameValue;
            private String nameView;
            private String syrs;

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.amount);
                dest.writeString(this.nameValue);
                dest.writeString(this.nameView);
                dest.writeString(this.syrs);
            }

            public ZwwlListBean() {
            }

            protected ZwwlListBean(Parcel in) {
                this.amount = in.readString();
                this.nameValue = in.readString();
                this.nameView = in.readString();
                this.syrs = in.readString();
            }

            public static final Creator<ZwwlListBean> CREATOR = new Creator<ZwwlListBean>() {
                @Override
                public ZwwlListBean createFromParcel(Parcel source) {
                    return new ZwwlListBean(source);
                }

                @Override
                public ZwwlListBean[] newArray(int size) {
                    return new ZwwlListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.shcwList);
            dest.writeList(this.tzlcList);
            dest.writeList(this.zjzhList);
            dest.writeList(this.zwwlList);
        }

        public BodyBean() {
        }

        protected BodyBean(Parcel in) {
            this.shcwList = new ArrayList<ShcwListBean>();
            in.readList(this.shcwList, ShcwListBean.class.getClassLoader());
            this.tzlcList = new ArrayList<TzlcListBean>();
            in.readList(this.tzlcList, TzlcListBean.class.getClassLoader());
            this.zjzhList = new ArrayList<ZjzhListBean>();
            in.readList(this.zjzhList, ZjzhListBean.class.getClassLoader());
            this.zwwlList = new ArrayList<ZwwlListBean>();
            in.readList(this.zwwlList, ZwwlListBean.class.getClassLoader());
        }

        public static final Parcelable.Creator<BodyBean> CREATOR = new Parcelable.Creator<BodyBean>() {
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
         * responseTime : 2017-12-16 10:45:10
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

        public static final Parcelable.Creator<HeaderBean> CREATOR = new Parcelable.Creator<HeaderBean>() {
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

    public AssetClassModel() {
    }

    protected AssetClassModel(Parcel in) {
        this.body = in.readParcelable(BodyBean.class.getClassLoader());
        this.header = in.readParcelable(HeaderBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<AssetClassModel> CREATOR = new Parcelable.Creator<AssetClassModel>() {
        @Override
        public AssetClassModel createFromParcel(Parcel source) {
            return new AssetClassModel(source);
        }

        @Override
        public AssetClassModel[] newArray(int size) {
            return new AssetClassModel[size];
        }
    };
}
