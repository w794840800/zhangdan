package com.beidou.ybz.accountbook.mvp.entity;

import java.util.List;

/**
 * Author: xu.yang on 2018/1/3
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:预付卡操作记录model
 */
public class PrepaidRecordListModel {


    /**
     * body : {"proList":[{"amount":"12.00","createTime":{"date":3,"day":3,"hours":14,"minutes":49,"month":0,"seconds":34,"time":1514962174000,"timezoneOffset":-480,"year":118},"delFlag":"1","holdAmount":"67.00","id":38,"positionId":"38","userNo":"10049975"},{"amount":"55.00","createTime":{"date":3,"day":3,"hours":14,"minutes":35,"month":0,"seconds":20,"time":1514961320000,"timezoneOffset":-480,"year":118},"delFlag":"1","holdAmount":"55","id":33,"positionId":"38","userNo":"10049975"}]}
     * header : {"code":"0000","desc":"成功","responseTime":"2018-01-03 14:49:34"}
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

    public static class BodyBean {
        private List<ProListBean> proList;

        public List<ProListBean> getProList() {
            return proList;
        }

        public void setProList(List<ProListBean> proList) {
            this.proList = proList;
        }

        public static class ProListBean {
            /**
             * amount : 12.00
             * createTime : {"date":3,"day":3,"hours":14,"minutes":49,"month":0,"seconds":34,"time":1514962174000,"timezoneOffset":-480,"year":118}
             * delFlag : 1
             * holdAmount : 67.00
             * id : 38
             * positionId : 38
             * userNo : 10049975
             */

            private String amount;
            private CreateTimeBean createTime;
            private String delFlag;
            private String holdAmount;
            private String id;
            private String positionId;
            private String userNo;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public CreateTimeBean getCreateTime() {
                return createTime;
            }

            public void setCreateTime(CreateTimeBean createTime) {
                this.createTime = createTime;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public String getHoldAmount() {
                return holdAmount;
            }

            public void setHoldAmount(String holdAmount) {
                this.holdAmount = holdAmount;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPositionId() {
                return positionId;
            }

            public void setPositionId(String positionId) {
                this.positionId = positionId;
            }

            public String getUserNo() {
                return userNo;
            }

            public void setUserNo(String userNo) {
                this.userNo = userNo;
            }

            public static class CreateTimeBean {
                /**
                 * date : 3
                 * day : 3
                 * hours : 14
                 * minutes : 49
                 * month : 0
                 * seconds : 34
                 * time : 1514962174000
                 * timezoneOffset : -480
                 * year : 118
                 */

                private String date;
                private String day;
                private String hours;
                private String minutes;
                private String month;
                private String seconds;
                private String time;
                private String timezoneOffset;
                private String year;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }

                public String getHours() {
                    return hours;
                }

                public void setHours(String hours) {
                    this.hours = hours;
                }

                public String getMinutes() {
                    return minutes;
                }

                public void setMinutes(String minutes) {
                    this.minutes = minutes;
                }

                public String getMonth() {
                    return month;
                }

                public void setMonth(String month) {
                    this.month = month;
                }

                public String getSeconds() {
                    return seconds;
                }

                public void setSeconds(String seconds) {
                    this.seconds = seconds;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(String timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public String getYear() {
                    return year;
                }

                public void setYear(String year) {
                    this.year = year;
                }
            }
        }
    }

    public static class HeaderBean {
        /**
         * code : 0000
         * desc : 成功
         * responseTime : 2018-01-03 14:49:34
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
    }
}
