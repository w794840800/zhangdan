package com.beidou.ybz.accountbook.mvp.entity;

/**
 * Author: xu.yang on 2017/12/7
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:获取密钥secretKeyId的model
 */
public class SecretKeyResponseModel {

    /**
     * body : {"secretIv":"98441375","secretKey":"S161713WohkSYxk195oVf8ew8q8TD1x9","secretKeyId":"22a468afda684220ab4e9378c88720be"}
     * header : {"code":"0000","desc":"成功","responseTime":"2017-12-07 10:15:20"}
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
         * secretIv : 98441375
         * secretKey : S161713WohkSYxk195oVf8ew8q8TD1x9
         * secretKeyId : 22a468afda684220ab4e9378c88720be
         */

        private String secretIv;
        private String secretKey;
        private String secretKeyId;

        public String getSecretIv() {
            return secretIv;
        }

        public void setSecretIv(String secretIv) {
            this.secretIv = secretIv;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getSecretKeyId() {
            return secretKeyId;
        }

        public void setSecretKeyId(String secretKeyId) {
            this.secretKeyId = secretKeyId;
        }

        @Override
        public String toString() {
            return "BodyBean{" +
                    "secretIv='" + secretIv + '\'' +
                    ", secretKey='" + secretKey + '\'' +
                    ", secretKeyId='" + secretKeyId + '\'' +
                    '}';
        }
    }

    public static class HeaderBean {
        /**
         * code : 0000
         * desc : 成功
         * responseTime : 2017-12-07 10:15:20
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
        public String toString() {
            return "HeaderBean{" +
                    "code='" + code + '\'' +
                    ", desc='" + desc + '\'' +
                    ", responseTime='" + responseTime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SecretKeyResponseModel{" +
                "body=" + body +
                ", header=" + header +
                '}';
    }
}
