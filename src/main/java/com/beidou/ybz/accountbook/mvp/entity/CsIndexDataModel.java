package com.beidou.ybz.accountbook.mvp.entity;

/**
 * Author: ${Supreme} on 2017/12/18
 * QQ:977594142
 * E-mail:977594142@qq.com
 * module:财商首页头部的数据
 */

public class CsIndexDataModel {

    /**
     * body : {"levelName":"财富初印象","studyOver":0,"wealthValue":25}
     * header : {"code":"0000","desc":"成功","responseTime":"2017-12-18 11:50:05"}
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
         * levelName : 财富初印象
         * studyOver : 0
         * wealthValue : 25
         */

        private String levelName;
        private int studyOver;
        private int wealthValue;

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public int getStudyOver() {
            return studyOver;
        }

        public void setStudyOver(int studyOver) {
            this.studyOver = studyOver;
        }

        public int getWealthValue() {
            return wealthValue;
        }

        public void setWealthValue(int wealthValue) {
            this.wealthValue = wealthValue;
        }
    }

    public static class HeaderBean {
        /**
         * code : 0000
         * desc : 成功
         * responseTime : 2017-12-18 11:50:05
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
