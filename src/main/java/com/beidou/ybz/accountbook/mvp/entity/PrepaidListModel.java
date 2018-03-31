package com.beidou.ybz.accountbook.mvp.entity;

import java.util.List;

/**
 * Author: xu.yang on 2017/12/29
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:预付卡列表model
 */
public class PrepaidListModel {

    /**
     * body : {"infoDto":null,"proList":[{"amount":1255,"assetId":"0005","comment":"预付卡2","createTime":{"date":29,"day":5,"hours":11,"minutes":59,"month":11,"seconds":45,"time":1514519985000,"timezoneOffset":-480,"year":117},"delFlag":"1","expireDate":"","id":8,"memo":"ill路他林俊杰林俊杰啦咯啦咯啦咯啦咯啦","times":"","userNo":"10049975"},{"amount":1200,"assetId":"0005","comment":"预付卡","createTime":{"date":29,"day":5,"hours":11,"minutes":49,"month":11,"seconds":5,"time":1514519345000,"timezoneOffset":-480,"year":117},"delFlag":"1","expireDate":"","id":7,"memo":"预付卡备注","times":"","userNo":"10049975"}],"zsz":2455}
     * header : {"code":"0000","desc":"成功","responseTime":"2017-12-29 12:00:14"}
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
        /**
         * infoDto : null
         * proList : [{"amount":1255,"assetId":"0005","comment":"预付卡2","createTime":{"date":29,"day":5,"hours":11,"minutes":59,"month":11,"seconds":45,"time":1514519985000,"timezoneOffset":-480,"year":117},"delFlag":"1","expireDate":"","id":8,"memo":"ill路他林俊杰林俊杰啦咯啦咯啦咯啦咯啦","times":"","userNo":"10049975"},{"amount":1200,"assetId":"0005","comment":"预付卡","createTime":{"date":29,"day":5,"hours":11,"minutes":49,"month":11,"seconds":5,"time":1514519345000,"timezoneOffset":-480,"year":117},"delFlag":"1","expireDate":"","id":7,"memo":"预付卡备注","times":"","userNo":"10049975"}]
         * zsz : 2455
         */

        private Object infoDto;
        private String zsz;
        private List<ProListBean> proList;

        public Object getInfoDto() {
            return infoDto;
        }

        public void setInfoDto(Object infoDto) {
            this.infoDto = infoDto;
        }

        public String getZsz() {
            return zsz;
        }

        public void setZsz(String zsz) {
            this.zsz = zsz;
        }

        public List<ProListBean> getProList() {
            return proList;
        }

        public void setProList(List<ProListBean> proList) {
            this.proList = proList;
        }

        public static class ProListBean {
            /**
             * amount : 1255
             * assetId : 0005
             * comment : 预付卡2
             * createTime : {"date":29,"day":5,"hours":11,"minutes":59,"month":11,"seconds":45,"time":1514519985000,"timezoneOffset":-480,"year":117}
             * delFlag : 1
             * expireDate :
             * id : 8
             * memo : ill路他林俊杰林俊杰啦咯啦咯啦咯啦咯啦
             * times :
             * userNo : 10049975
             */

            private String amount;
            private String assetId;
            private String comment;
            private CreateTimeBean createTime;
            private String delFlag;
            private String expireDate;
            private String id;
            private String memo;
            private String times;
            private String userNo;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getAssetId() {
                return assetId;
            }

            public void setAssetId(String assetId) {
                this.assetId = assetId;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
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

            public String getExpireDate() {
                return expireDate;
            }

            public void setExpireDate(String expireDate) {
                this.expireDate = expireDate;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }

            public String getUserNo() {
                return userNo;
            }

            public void setUserNo(String userNo) {
                this.userNo = userNo;
            }

            public static class CreateTimeBean {
                /**
                 * date : 29
                 * day : 5
                 * hours : 11
                 * minutes : 59
                 * month : 11
                 * seconds : 45
                 * time : 1514519985000
                 * timezoneOffset : -480
                 * year : 117
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
         * responseTime : 2017-12-29 12:00:14
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
