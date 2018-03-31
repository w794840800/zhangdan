package com.beidou.ybz.accountbook.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: xu.yang on 2017/12/10
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:支持的货币列表model
 */
public class CurrencyModel implements Parcelable {

    /**
     * body : {"currencyList":[{"currencyName":"人民币","currencyNo":"CNY"},{"currencyName":"美元","currencyNo":"USD"},{"currencyName":"日元","currencyNo":"JPY"},{"currencyName":"欧元","currencyNo":"GBP"},{"currencyName":"港币","currencyNo":"HKD"}]}
     * header : {"code":"0000","desc":"成功","responseTime":"2017-12-16 17:11:10"}
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
        private List<CurrencyListBean> currencyList;

        public List<CurrencyListBean> getCurrencyList() {
            return currencyList;
        }

        public void setCurrencyList(List<CurrencyListBean> currencyList) {
            this.currencyList = currencyList;
        }

        public static class CurrencyListBean implements Parcelable {
            /**
             * currencyName : 人民币
             * currencyNo : CNY
             */

            private String currencyName;
            private String currencyNo;
            private String initials;//首字母

            public String getInitials() {
                return initials;
            }

            public void setInitials(String initials) {
                this.initials = initials;
            }



            public String getCurrencyName() {
                return currencyName;
            }

            public void setCurrencyName(String currencyName) {
                this.currencyName = currencyName;
            }

            public String getCurrencyNo() {
                return currencyNo;
            }

            public void setCurrencyNo(String currencyNo) {
                this.currencyNo = currencyNo;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.currencyName);
                dest.writeString(this.currencyNo);
                dest.writeString(this.initials);
            }

            public CurrencyListBean() {
            }

            protected CurrencyListBean(Parcel in) {
                this.currencyName = in.readString();
                this.currencyNo = in.readString();
                this.initials = in.readString();
            }

            public static final Creator<CurrencyListBean> CREATOR = new Creator<CurrencyListBean>() {
                @Override
                public CurrencyListBean createFromParcel(Parcel source) {
                    return new CurrencyListBean(source);
                }

                @Override
                public CurrencyListBean[] newArray(int size) {
                    return new CurrencyListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.currencyList);
        }

        public BodyBean() {
        }

        protected BodyBean(Parcel in) {
            this.currencyList = new ArrayList<CurrencyListBean>();
            in.readList(this.currencyList, CurrencyListBean.class.getClassLoader());
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
         * responseTime : 2017-12-16 17:11:10
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

    public CurrencyModel() {
    }

    protected CurrencyModel(Parcel in) {
        this.body = in.readParcelable(BodyBean.class.getClassLoader());
        this.header = in.readParcelable(HeaderBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<CurrencyModel> CREATOR = new Parcelable.Creator<CurrencyModel>() {
        @Override
        public CurrencyModel createFromParcel(Parcel source) {
            return new CurrencyModel(source);
        }

        @Override
        public CurrencyModel[] newArray(int size) {
            return new CurrencyModel[size];
        }
    };
}
