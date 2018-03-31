package com.beidou.ybz.accountbook.mvp.entity;

import java.util.List;

/**
 * Author: xu.yang on 2017/12/10
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:股票操作记录model
 */
public class StockRecordModel {

    /**
     * body : {"positionList":[],"recordList":[{"amount":0,"createTime":{"date":19,"day":2,"hours":10,"minutes":7,"month":11,"seconds":40,"time":1513649260000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"369","id":37,"positionId":"50","positionNumber":"1","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":20,"minutes":48,"month":11,"seconds":28,"time":1513601308000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"368","id":36,"positionId":"50","positionNumber":"-37","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":20,"minutes":48,"month":11,"seconds":14,"time":1513601294000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"405","id":35,"positionId":"50","positionNumber":"37","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":20,"minutes":47,"month":11,"seconds":52,"time":1513601272000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"368","id":34,"positionId":"50","positionNumber":"244","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":20,"minutes":39,"month":11,"seconds":24,"time":1513600764000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"124","id":33,"positionId":"50","positionNumber":"1","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":20,"minutes":38,"month":11,"seconds":19,"time":1513600699000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"123","id":32,"positionId":"50","positionNumber":"-5","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":20,"minutes":38,"month":11,"seconds":11,"time":1513600691000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"128","id":31,"positionId":"50","positionNumber":"10","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":20,"minutes":38,"month":11,"seconds":6,"time":1513600686000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"118","id":30,"positionId":"50","positionNumber":"10","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":17,"minutes":14,"month":11,"seconds":54,"time":1513588494000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"108","id":29,"positionId":"50","positionNumber":"1","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":17,"minutes":14,"month":11,"seconds":47,"time":1513588487000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"107","id":28,"positionId":"50","positionNumber":"1","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":16,"minutes":9,"month":11,"seconds":10,"time":1513584550000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"106","id":26,"positionId":"50","positionNumber":"3","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":16,"minutes":9,"month":11,"seconds":4,"time":1513584544000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"103","id":25,"positionId":"50","positionNumber":"3","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":15,"minutes":1,"month":11,"seconds":15,"time":1513580475000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"1232","id":23,"positionId":"50","positionNumber":"32","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":14,"minutes":55,"month":11,"seconds":0,"time":1513580100000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"15","id":22,"positionId":"50","positionNumber":"3","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":14,"minutes":45,"month":11,"seconds":52,"time":1513579552000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"21","id":21,"positionId":"50","positionNumber":"3","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":14,"minutes":45,"month":11,"seconds":41,"time":1513579541000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"18","id":20,"positionId":"50","positionNumber":"3","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":14,"minutes":45,"month":11,"seconds":32,"time":1513579532000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"15","id":19,"positionId":"50","positionNumber":"3","userNo":"10051550"},{"amount":153,"createTime":{"date":5,"day":2,"hours":11,"minutes":37,"month":11,"seconds":57,"time":1512445077000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"12","id":18,"positionId":"50","positionNumber":"12","userNo":"10051550"}],"stockPositionDto":null,"zde":0,"zdf":0,"zsz":0}
     * header : {"code":"0000","desc":"成功","responseTime":"2017-12-19 10:28:25"}
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
         * positionList : []
         * recordList : [{"amount":0,"createTime":{"date":19,"day":2,"hours":10,"minutes":7,"month":11,"seconds":40,"time":1513649260000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"369","id":37,"positionId":"50","positionNumber":"1","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":20,"minutes":48,"month":11,"seconds":28,"time":1513601308000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"368","id":36,"positionId":"50","positionNumber":"-37","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":20,"minutes":48,"month":11,"seconds":14,"time":1513601294000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"405","id":35,"positionId":"50","positionNumber":"37","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":20,"minutes":47,"month":11,"seconds":52,"time":1513601272000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"368","id":34,"positionId":"50","positionNumber":"244","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":20,"minutes":39,"month":11,"seconds":24,"time":1513600764000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"124","id":33,"positionId":"50","positionNumber":"1","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":20,"minutes":38,"month":11,"seconds":19,"time":1513600699000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"123","id":32,"positionId":"50","positionNumber":"-5","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":20,"minutes":38,"month":11,"seconds":11,"time":1513600691000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"128","id":31,"positionId":"50","positionNumber":"10","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":20,"minutes":38,"month":11,"seconds":6,"time":1513600686000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"118","id":30,"positionId":"50","positionNumber":"10","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":17,"minutes":14,"month":11,"seconds":54,"time":1513588494000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"108","id":29,"positionId":"50","positionNumber":"1","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":17,"minutes":14,"month":11,"seconds":47,"time":1513588487000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"107","id":28,"positionId":"50","positionNumber":"1","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":16,"minutes":9,"month":11,"seconds":10,"time":1513584550000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"106","id":26,"positionId":"50","positionNumber":"3","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":16,"minutes":9,"month":11,"seconds":4,"time":1513584544000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"103","id":25,"positionId":"50","positionNumber":"3","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":15,"minutes":1,"month":11,"seconds":15,"time":1513580475000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"1232","id":23,"positionId":"50","positionNumber":"32","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":14,"minutes":55,"month":11,"seconds":0,"time":1513580100000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"15","id":22,"positionId":"50","positionNumber":"3","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":14,"minutes":45,"month":11,"seconds":52,"time":1513579552000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"21","id":21,"positionId":"50","positionNumber":"3","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":14,"minutes":45,"month":11,"seconds":41,"time":1513579541000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"18","id":20,"positionId":"50","positionNumber":"3","userNo":"10051550"},{"amount":0,"createTime":{"date":18,"day":1,"hours":14,"minutes":45,"month":11,"seconds":32,"time":1513579532000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"15","id":19,"positionId":"50","positionNumber":"3","userNo":"10051550"},{"amount":153,"createTime":{"date":5,"day":2,"hours":11,"minutes":37,"month":11,"seconds":57,"time":1512445077000,"timezoneOffset":-480,"year":117},"delFlag":"1","holdNumber":"12","id":18,"positionId":"50","positionNumber":"12","userNo":"10051550"}]
         * stockPositionDto : null
         * zde : 0
         * zdf : 0
         * zsz : 0
         */

        private Object stockPositionDto;
        private String zde;
        private String zdf;
        private String zsz;
        private List<?> positionList;
        private List<RecordListBean> recordList;

        public Object getStockPositionDto() {
            return stockPositionDto;
        }

        public void setStockPositionDto(Object stockPositionDto) {
            this.stockPositionDto = stockPositionDto;
        }

        public String getZde() {
            return zde;
        }

        public void setZde(String zde) {
            this.zde = zde;
        }

        public String getZdf() {
            return zdf;
        }

        public void setZdf(String zdf) {
            this.zdf = zdf;
        }

        public String getZsz() {
            return zsz;
        }

        public void setZsz(String zsz) {
            this.zsz = zsz;
        }

        public List<?> getPositionList() {
            return positionList;
        }

        public void setPositionList(List<?> positionList) {
            this.positionList = positionList;
        }

        public List<RecordListBean> getRecordList() {
            return recordList;
        }

        public void setRecordList(List<RecordListBean> recordList) {
            this.recordList = recordList;
        }

        public static class RecordListBean {
            /**
             * amount : 0
             * createTime : {"date":19,"day":2,"hours":10,"minutes":7,"month":11,"seconds":40,"time":1513649260000,"timezoneOffset":-480,"year":117}
             * delFlag : 1
             * holdNumber : 369
             * id : 37
             * positionId : 50
             * positionNumber : 1
             * userNo : 10051550
             */

            private String amount;
            private CreateTimeBean createTime;
            private String delFlag;
            private String holdNumber;
            private String id;
            private String positionId;
            private String positionNumber;
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

            public String getHoldNumber() {
                return holdNumber;
            }

            public void setHoldNumber(String holdNumber) {
                this.holdNumber = holdNumber;
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

            public String getPositionNumber() {
                return positionNumber;
            }

            public void setPositionNumber(String positionNumber) {
                this.positionNumber = positionNumber;
            }

            public String getUserNo() {
                return userNo;
            }

            public void setUserNo(String userNo) {
                this.userNo = userNo;
            }

            public static class CreateTimeBean {
                /**
                 * date : 19
                 * day : 2
                 * hours : 10
                 * minutes : 7
                 * month : 11
                 * seconds : 40
                 * time : 1513649260000
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
         * responseTime : 2017-12-19 10:28:25
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
