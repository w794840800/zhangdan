package com.beidou.ybz.accountbook.mvp.entity;

/**
 * Author: ${Supreme} on 2017/12/14
 * QQ:977594142
 * E-mail:977594142@qq.com
 * module:
 */

public class CheckPiccodeModel {

    /**
     * body : {"checkFlag":1,"picUrl":"","sendStatus":"1","sendTime":"2017-12-14 13:49:53"}
     * header : {"code":"0000","desc":"成功","responseTime":"2017-12-14 13:49:53"}
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
         * checkFlag : 1
         * picUrl :
         * sendStatus : 1
         * sendTime : 2017-12-14 13:49:53
         */

        private int checkFlag;
        private String picUrl;
        private String sendStatus;
        private String sendTime;

        public int getCheckFlag() {
            return checkFlag;
        }

        public void setCheckFlag(int checkFlag) {
            this.checkFlag = checkFlag;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getSendStatus() {
            return sendStatus;
        }

        public void setSendStatus(String sendStatus) {
            this.sendStatus = sendStatus;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }
    }

    public static class HeaderBean {
        /**
         * code : 0000
         * desc : 成功
         * responseTime : 2017-12-14 13:49:53
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
