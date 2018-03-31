package com.beidou.ybz.accountbook.mvp.entity;

import java.util.List;

/**
 * Author: xu.yang on 2017/12/10
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:自定义列表model
 */
public class CustomListModel {

    /**
     * body : {"infoDto":null,"proList":[{"amount":12,"assetId":"9999","buyTime":"2018年1月3日","comment":"分类内容","createTime":{"date":3,"day":3,"hours":15,"minutes":43,"month":0,"seconds":46,"time":1514965426000,"timezoneOffset":-480,"year":118},"delFlag":"1","id":15,"memo":"备注","name":"分类名称","userNo":"10049975"},{"amount":12,"assetId":"9999","buyTime":"2018年1月3日","comment":"回来了","createTime":{"date":3,"day":3,"hours":15,"minutes":35,"month":0,"seconds":57,"time":1514964957000,"timezoneOffset":-480,"year":118},"delFlag":"1","id":14,"memo":"啊啊啊啊","name":"自定义分类","userNo":"10049975"},{"amount":22,"assetId":"9999","buyTime":"","comment":"接机","createTime":{"date":3,"day":3,"hours":10,"minutes":51,"month":0,"seconds":30,"time":1514947890000,"timezoneOffset":-480,"year":118},"delFlag":"1","id":6,"memo":"","name":"回老家了","userNo":"10049975"}],"zsz":46}
     * header : {"code":"0000","desc":"成功","responseTime":"2018-01-03 15:43:44"}
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
         * proList : [{"amount":12,"assetId":"9999","buyTime":"2018年1月3日","comment":"分类内容","createTime":{"date":3,"day":3,"hours":15,"minutes":43,"month":0,"seconds":46,"time":1514965426000,"timezoneOffset":-480,"year":118},"delFlag":"1","id":15,"memo":"备注","name":"分类名称","userNo":"10049975"},{"amount":12,"assetId":"9999","buyTime":"2018年1月3日","comment":"回来了","createTime":{"date":3,"day":3,"hours":15,"minutes":35,"month":0,"seconds":57,"time":1514964957000,"timezoneOffset":-480,"year":118},"delFlag":"1","id":14,"memo":"啊啊啊啊","name":"自定义分类","userNo":"10049975"},{"amount":22,"assetId":"9999","buyTime":"","comment":"接机","createTime":{"date":3,"day":3,"hours":10,"minutes":51,"month":0,"seconds":30,"time":1514947890000,"timezoneOffset":-480,"year":118},"delFlag":"1","id":6,"memo":"","name":"回老家了","userNo":"10049975"}]
         * zsz : 46
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
             * amount : 12
             * assetId : 9999
             * buyTime : 2018年1月3日
             * comment : 分类内容
             * createTime : {"date":3,"day":3,"hours":15,"minutes":43,"month":0,"seconds":46,"time":1514965426000,"timezoneOffset":-480,"year":118}
             * delFlag : 1
             * id : 15
             * memo : 备注
             * name : 分类名称
             * userNo : 10049975
             */

            private String amount;
            private String assetId;
            private String buyTime;
            private String comment;
            private CreateTimeBean createTime;
            private String delFlag;
            private String id;
            private String memo;
            private String name;
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

            public String getBuyTime() {
                return buyTime;
            }

            public void setBuyTime(String buyTime) {
                this.buyTime = buyTime;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
                 * hours : 15
                 * minutes : 43
                 * month : 0
                 * seconds : 46
                 * time : 1514965426000
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
         * responseTime : 2018-01-03 15:43:44
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
