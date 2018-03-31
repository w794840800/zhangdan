package com.beidou.ybz.accountbook.mvp.entity;

import java.util.List;

/**
 * Author: xu.yang on 2017/12/10
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:保险详情model
 */
public class InsuranceDetailModel {

    /**
     * body : {"insuDto":{"amount":10000,"assetId":"0011","brokerName":"","createTime":{"date":13,"day":3,"hours":20,"minutes":38,"month":11,"seconds":31,"time":1513168711000,"timezoneOffset":-480,"year":117},"delFlag":"1","id":18,"investmentTerm":"","investmentTime":"","memo":"备注内容","productName":"保险名称","userNo":"10051550","yearBonus":"","yearIncome":""},"proList":[],"zsz":0}
     * header : {"code":"0000","desc":"成功","responseTime":"2017-12-13 20:52:02"}
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
         * insuDto : {"amount":10000,"assetId":"0011","brokerName":"","createTime":{"date":13,"day":3,"hours":20,"minutes":38,"month":11,"seconds":31,"time":1513168711000,"timezoneOffset":-480,"year":117},"delFlag":"1","id":18,"investmentTerm":"","investmentTime":"","memo":"备注内容","productName":"保险名称","userNo":"10051550","yearBonus":"","yearIncome":""}
         * proList : []
         * zsz : 0
         */

        private InsuDtoBean insuDto;
        private String zsz;
        private List<?> proList;

        public InsuDtoBean getInsuDto() {
            return insuDto;
        }

        public void setInsuDto(InsuDtoBean insuDto) {
            this.insuDto = insuDto;
        }

        public String getZsz() {
            return zsz;
        }

        public void setZsz(String zsz) {
            this.zsz = zsz;
        }

        public List<?> getProList() {
            return proList;
        }

        public void setProList(List<?> proList) {
            this.proList = proList;
        }

        public static class InsuDtoBean {
            /**
             * amount : 10000
             * assetId : 0011
             * brokerName :
             * createTime : {"date":13,"day":3,"hours":20,"minutes":38,"month":11,"seconds":31,"time":1513168711000,"timezoneOffset":-480,"year":117}
             * delFlag : 1
             * id : 18
             * investmentTerm :
             * investmentTime :
             * memo : 备注内容
             * productName : 保险名称
             * userNo : 10051550
             * yearBonus :
             * yearIncome :
             */

            private String amount;
            private String assetId;
            private String brokerName;
            private CreateTimeBean createTime;
            private String delFlag;
            private String id;
            private String investmentTerm;
            private String investmentTime;
            private String memo;
            private String productName;
            private String userNo;
            private String yearBonus;
            private String yearIncome;

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

            public String getBrokerName() {
                return brokerName;
            }

            public void setBrokerName(String brokerName) {
                this.brokerName = brokerName;
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

            public String getInvestmentTerm() {
                return investmentTerm;
            }

            public void setInvestmentTerm(String investmentTerm) {
                this.investmentTerm = investmentTerm;
            }

            public String getInvestmentTime() {
                return investmentTime;
            }

            public void setInvestmentTime(String investmentTime) {
                this.investmentTime = investmentTime;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getUserNo() {
                return userNo;
            }

            public void setUserNo(String userNo) {
                this.userNo = userNo;
            }

            public String getYearBonus() {
                return yearBonus;
            }

            public void setYearBonus(String yearBonus) {
                this.yearBonus = yearBonus;
            }

            public String getYearIncome() {
                return yearIncome;
            }

            public void setYearIncome(String yearIncome) {
                this.yearIncome = yearIncome;
            }

            public static class CreateTimeBean {
                /**
                 * date : 13
                 * day : 3
                 * hours : 20
                 * minutes : 38
                 * month : 11
                 * seconds : 31
                 * time : 1513168711000
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
         * responseTime : 2017-12-13 20:52:02
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
