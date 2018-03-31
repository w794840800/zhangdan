package com.beidou.ybz.accountbook.mvp.entity;

/**
 * Author: xu.yang on 2017/12/10
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:股票编辑成功model
 */
public class StockEditModel {

    /**
     * body : {"andorsub":"","brokerName":"","delFlag":"","id":65,"memo":"","positionId":"","positionNumber":"222","stockCode":"000995","stockName":"皇台酒业","userNo":"10051564"}
     * header : {"code":"0000","desc":"成功","responseTime":"2017-12-21 18:29:46"}
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
         * andorsub :
         * brokerName :
         * delFlag :
         * id : 65
         * memo :
         * positionId :
         * positionNumber : 222
         * stockCode : 000995
         * stockName : 皇台酒业
         * userNo : 10051564
         */

        private String andorsub;
        private String brokerName;
        private String delFlag;
        private String id;
        private String memo;
        private String positionId;
        private String positionNumber;
        private String stockCode;
        private String stockName;
        private String userNo;

        public String getAndorsub() {
            return andorsub;
        }

        public void setAndorsub(String andorsub) {
            this.andorsub = andorsub;
        }

        public String getBrokerName() {
            return brokerName;
        }

        public void setBrokerName(String brokerName) {
            this.brokerName = brokerName;
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

        public String getStockCode() {
            return stockCode;
        }

        public void setStockCode(String stockCode) {
            this.stockCode = stockCode;
        }

        public String getStockName() {
            return stockName;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }
    }

    public static class HeaderBean {
        /**
         * code : 0000
         * desc : 成功
         * responseTime : 2017-12-21 18:29:46
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
