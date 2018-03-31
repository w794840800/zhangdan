package com.beidou.ybz.accountbook.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: ${Supreme} on 2017/12/28
 * QQ:977594142
 * E-mail:977594142@qq.com
 * module:
 */

public class GetHjzModel implements Parcelable {

    /**
     * body : {"infoDto":{"cfDate":"2018-03-10","conditionif":"1","createTime":{"date":8,"day":4,"hours":9,"minutes":46,"month":1,"seconds":11,"time":1518054371000,"timezoneOffset":-480,"year":118},"id":924,"ifnotice":"","message":"赶紧看","mobile":"15555928936","mobileName":"","notice":"2","noticenumber":0,"sfkq":"","userNo":"10049975"},"proList":[{"cfDate":"2018-03-10","conditionif":"1","createTime":{"date":8,"day":4,"hours":9,"minutes":46,"month":1,"seconds":11,"time":1518054371000,"timezoneOffset":-480,"year":118},"id":924,"ifnotice":"","message":"赶紧看","mobile":"15555928936","mobileName":"","notice":"2","noticenumber":0,"sfkq":"","userNo":"10049975"}],"userDayAssetJzc":2.0003513508E8,"userDayAssetZqk":12,"userDayAssetZzc":2.0003514708E8}
     * header : {"code":"0000","desc":"成功","responseTime":"2018-02-08 09:35:05"}
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
        /**
         * infoDto : {"cfDate":"2018-03-10","conditionif":"1","createTime":{"date":8,"day":4,"hours":9,"minutes":46,"month":1,"seconds":11,"time":1518054371000,"timezoneOffset":-480,"year":118},"id":924,"ifnotice":"","message":"赶紧看","mobile":"15555928936","mobileName":"","notice":"2","noticenumber":0,"sfkq":"","userNo":"10049975"}
         * proList : [{"cfDate":"2018-03-10","conditionif":"1","createTime":{"date":8,"day":4,"hours":9,"minutes":46,"month":1,"seconds":11,"time":1518054371000,"timezoneOffset":-480,"year":118},"id":924,"ifnotice":"","message":"赶紧看","mobile":"15555928936","mobileName":"","notice":"2","noticenumber":0,"sfkq":"","userNo":"10049975"}]
         * userDayAssetJzc : 2.0003513508E8
         * userDayAssetZqk : 12
         * userDayAssetZzc : 2.0003514708E8
         */

        private InfoDtoBean infoDto;
        private String userDayAssetJzc;
        private String userDayAssetZqk;
        private String userDayAssetZzc;
        private List<ProListBean> proList;

        public InfoDtoBean getInfoDto() {
            return infoDto;
        }

        public void setInfoDto(InfoDtoBean infoDto) {
            this.infoDto = infoDto;
        }

        public String getUserDayAssetJzc() {
            return userDayAssetJzc;
        }

        public void setUserDayAssetJzc(String userDayAssetJzc) {
            this.userDayAssetJzc = userDayAssetJzc;
        }

        public String getUserDayAssetZqk() {
            return userDayAssetZqk;
        }

        public void setUserDayAssetZqk(String userDayAssetZqk) {
            this.userDayAssetZqk = userDayAssetZqk;
        }

        public String getUserDayAssetZzc() {
            return userDayAssetZzc;
        }

        public void setUserDayAssetZzc(String userDayAssetZzc) {
            this.userDayAssetZzc = userDayAssetZzc;
        }

        public List<ProListBean> getProList() {
            return proList;
        }

        public void setProList(List<ProListBean> proList) {
            this.proList = proList;
        }

        public static class InfoDtoBean implements Parcelable {
            /**
             * cfDate : 2018-03-10
             * conditionif : 1
             * createTime : {"date":8,"day":4,"hours":9,"minutes":46,"month":1,"seconds":11,"time":1518054371000,"timezoneOffset":-480,"year":118}
             * id : 924
             * ifnotice :
             * message : 赶紧看
             * mobile : 15555928936
             * mobileName :
             * notice : 2
             * noticenumber : 0
             * sfkq :
             * userNo : 10049975
             */

            private String cfDate;
            private String conditionif;
            private CreateTimeBean createTime;
            private String id;
            private String ifnotice;
            private String message;
            private String mobile;
            private String mobileName;
            private String notice;
            private String noticenumber;
            private String sfkq;
            private String userNo;

            public String getCfDate() {
                return cfDate;
            }

            public void setCfDate(String cfDate) {
                this.cfDate = cfDate;
            }

            public String getConditionif() {
                return conditionif;
            }

            public void setConditionif(String conditionif) {
                this.conditionif = conditionif;
            }

            public CreateTimeBean getCreateTime() {
                return createTime;
            }

            public void setCreateTime(CreateTimeBean createTime) {
                this.createTime = createTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIfnotice() {
                return ifnotice;
            }

            public void setIfnotice(String ifnotice) {
                this.ifnotice = ifnotice;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getMobileName() {
                return mobileName;
            }

            public void setMobileName(String mobileName) {
                this.mobileName = mobileName;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public String getNoticenumber() {
                return noticenumber;
            }

            public void setNoticenumber(String noticenumber) {
                this.noticenumber = noticenumber;
            }

            public String getSfkq() {
                return sfkq;
            }

            public void setSfkq(String sfkq) {
                this.sfkq = sfkq;
            }

            public String getUserNo() {
                return userNo;
            }

            public void setUserNo(String userNo) {
                this.userNo = userNo;
            }

            public static class CreateTimeBean implements Parcelable {
                /**
                 * date : 8
                 * day : 4
                 * hours : 9
                 * minutes : 46
                 * month : 1
                 * seconds : 11
                 * time : 1518054371000
                 * timezoneOffset : -480
                 * year : 118
                 */

                private int date;
                private int day;
                private int hours;
                private int minutes;
                private int month;
                private int seconds;
                private long time;
                private int timezoneOffset;
                private int year;

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.date);
                    dest.writeInt(this.day);
                    dest.writeInt(this.hours);
                    dest.writeInt(this.minutes);
                    dest.writeInt(this.month);
                    dest.writeInt(this.seconds);
                    dest.writeLong(this.time);
                    dest.writeInt(this.timezoneOffset);
                    dest.writeInt(this.year);
                }

                public CreateTimeBean() {
                }

                protected CreateTimeBean(Parcel in) {
                    this.date = in.readInt();
                    this.day = in.readInt();
                    this.hours = in.readInt();
                    this.minutes = in.readInt();
                    this.month = in.readInt();
                    this.seconds = in.readInt();
                    this.time = in.readLong();
                    this.timezoneOffset = in.readInt();
                    this.year = in.readInt();
                }

                public static final Creator<CreateTimeBean> CREATOR = new Creator<CreateTimeBean>() {
                    @Override
                    public CreateTimeBean createFromParcel(Parcel source) {
                        return new CreateTimeBean(source);
                    }

                    @Override
                    public CreateTimeBean[] newArray(int size) {
                        return new CreateTimeBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.cfDate);
                dest.writeString(this.conditionif);
                dest.writeParcelable(this.createTime, flags);
                dest.writeString(this.id);
                dest.writeString(this.ifnotice);
                dest.writeString(this.message);
                dest.writeString(this.mobile);
                dest.writeString(this.mobileName);
                dest.writeString(this.notice);
                dest.writeString(this.noticenumber);
                dest.writeString(this.sfkq);
                dest.writeString(this.userNo);
            }

            public InfoDtoBean() {
            }

            protected InfoDtoBean(Parcel in) {
                this.cfDate = in.readString();
                this.conditionif = in.readString();
                this.createTime = in.readParcelable(CreateTimeBean.class.getClassLoader());
                this.id = in.readString();
                this.ifnotice = in.readString();
                this.message = in.readString();
                this.mobile = in.readString();
                this.mobileName = in.readString();
                this.notice = in.readString();
                this.noticenumber = in.readString();
                this.sfkq = in.readString();
                this.userNo = in.readString();
            }

            public static final Creator<InfoDtoBean> CREATOR = new Creator<InfoDtoBean>() {
                @Override
                public InfoDtoBean createFromParcel(Parcel source) {
                    return new InfoDtoBean(source);
                }

                @Override
                public InfoDtoBean[] newArray(int size) {
                    return new InfoDtoBean[size];
                }
            };
        }

        public static class ProListBean implements Parcelable {
            /**
             * cfDate : 2018-03-10
             * conditionif : 1
             * createTime : {"date":8,"day":4,"hours":9,"minutes":46,"month":1,"seconds":11,"time":1518054371000,"timezoneOffset":-480,"year":118}
             * id : 924
             * ifnotice :
             * message : 赶紧看
             * mobile : 15555928936
             * mobileName :
             * notice : 2
             * noticenumber : 0
             * sfkq :
             * userNo : 10049975
             */

            private String cfDate;
            private String conditionif;
            private CreateTimeBeanX createTime;
            private String id;
            private String ifnotice;
            private String message;
            private String mobile;
            private String mobileName;
            private String notice;
            private String noticenumber;
            private String sfkq;
            private String userNo;

            public String getCfDate() {
                return cfDate;
            }

            public void setCfDate(String cfDate) {
                this.cfDate = cfDate;
            }

            public String getConditionif() {
                return conditionif;
            }

            public void setConditionif(String conditionif) {
                this.conditionif = conditionif;
            }

            public CreateTimeBeanX getCreateTime() {
                return createTime;
            }

            public void setCreateTime(CreateTimeBeanX createTime) {
                this.createTime = createTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIfnotice() {
                return ifnotice;
            }

            public void setIfnotice(String ifnotice) {
                this.ifnotice = ifnotice;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getMobileName() {
                return mobileName;
            }

            public void setMobileName(String mobileName) {
                this.mobileName = mobileName;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public String getNoticenumber() {
                return noticenumber;
            }

            public void setNoticenumber(String noticenumber) {
                this.noticenumber = noticenumber;
            }

            public String getSfkq() {
                return sfkq;
            }

            public void setSfkq(String sfkq) {
                this.sfkq = sfkq;
            }

            public String getUserNo() {
                return userNo;
            }

            public void setUserNo(String userNo) {
                this.userNo = userNo;
            }

            public static class CreateTimeBeanX implements Parcelable {
                /**
                 * date : 8
                 * day : 4
                 * hours : 9
                 * minutes : 46
                 * month : 1
                 * seconds : 11
                 * time : 1518054371000
                 * timezoneOffset : -480
                 * year : 118
                 */

                private int date;
                private int day;
                private int hours;
                private int minutes;
                private int month;
                private int seconds;
                private long time;
                private int timezoneOffset;
                private int year;

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.date);
                    dest.writeInt(this.day);
                    dest.writeInt(this.hours);
                    dest.writeInt(this.minutes);
                    dest.writeInt(this.month);
                    dest.writeInt(this.seconds);
                    dest.writeLong(this.time);
                    dest.writeInt(this.timezoneOffset);
                    dest.writeInt(this.year);
                }

                public CreateTimeBeanX() {
                }

                protected CreateTimeBeanX(Parcel in) {
                    this.date = in.readInt();
                    this.day = in.readInt();
                    this.hours = in.readInt();
                    this.minutes = in.readInt();
                    this.month = in.readInt();
                    this.seconds = in.readInt();
                    this.time = in.readLong();
                    this.timezoneOffset = in.readInt();
                    this.year = in.readInt();
                }

                public static final Creator<CreateTimeBeanX> CREATOR = new Creator<CreateTimeBeanX>() {
                    @Override
                    public CreateTimeBeanX createFromParcel(Parcel source) {
                        return new CreateTimeBeanX(source);
                    }

                    @Override
                    public CreateTimeBeanX[] newArray(int size) {
                        return new CreateTimeBeanX[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.cfDate);
                dest.writeString(this.conditionif);
                dest.writeParcelable(this.createTime, flags);
                dest.writeString(this.id);
                dest.writeString(this.ifnotice);
                dest.writeString(this.message);
                dest.writeString(this.mobile);
                dest.writeString(this.mobileName);
                dest.writeString(this.notice);
                dest.writeString(this.noticenumber);
                dest.writeString(this.sfkq);
                dest.writeString(this.userNo);
            }

            public ProListBean() {
            }

            protected ProListBean(Parcel in) {
                this.cfDate = in.readString();
                this.conditionif = in.readString();
                this.createTime = in.readParcelable(CreateTimeBeanX.class.getClassLoader());
                this.id = in.readString();
                this.ifnotice = in.readString();
                this.message = in.readString();
                this.mobile = in.readString();
                this.mobileName = in.readString();
                this.notice = in.readString();
                this.noticenumber = in.readString();
                this.sfkq = in.readString();
                this.userNo = in.readString();
            }

            public static final Creator<ProListBean> CREATOR = new Creator<ProListBean>() {
                @Override
                public ProListBean createFromParcel(Parcel source) {
                    return new ProListBean(source);
                }

                @Override
                public ProListBean[] newArray(int size) {
                    return new ProListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.infoDto, flags);
            dest.writeString(this.userDayAssetJzc);
            dest.writeString(this.userDayAssetZqk);
            dest.writeString(this.userDayAssetZzc);
            dest.writeList(this.proList);
        }

        public BodyBean() {
        }

        protected BodyBean(Parcel in) {
            this.infoDto = in.readParcelable(InfoDtoBean.class.getClassLoader());
            this.userDayAssetJzc = in.readString();
            this.userDayAssetZqk = in.readString();
            this.userDayAssetZzc = in.readString();
            this.proList = new ArrayList<ProListBean>();
            in.readList(this.proList, ProListBean.class.getClassLoader());
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
         * responseTime : 2018-02-08 09:35:05
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

    public GetHjzModel() {
    }

    protected GetHjzModel(Parcel in) {
        this.body = in.readParcelable(BodyBean.class.getClassLoader());
        this.header = in.readParcelable(HeaderBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<GetHjzModel> CREATOR = new Parcelable.Creator<GetHjzModel>() {
        @Override
        public GetHjzModel createFromParcel(Parcel source) {
            return new GetHjzModel(source);
        }

        @Override
        public GetHjzModel[] newArray(int size) {
            return new GetHjzModel[size];
        }
    };
}
