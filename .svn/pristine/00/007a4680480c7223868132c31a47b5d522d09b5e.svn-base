package com.beidou.ybz.accountbook.mvp.entity;

import com.beidou.ybz.accountbook.util.Utils;

/**
 * Author: xu.yang on 2017/12/4
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:获取秘钥的请求参数
 */
public class RequestBody2 {

    /**
     * header : {"clientVersion":"1.0.0","systemVersion":"9.3.5","ip":"192.168.10.108","requestTime":"2017-12-416: 49: 55","tokenId":"","termType":"Android","deviceId":"","clientName":"ybz","secretKeyId":""}
     * body : {}
     */

    private HeaderBean header;
    private BodyBean body;

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * clientVersion : 1.0.0
         * systemVersion : 9.3.5
         * ip : 192.168.10.108
         * requestTime : 2017-12-416: 49: 55
         * tokenId :
         * termType : Android
         * deviceId :
         * clientName : ybz
         * secretKeyId :
         */

        private String clientVersion;
        private String systemVersion;
        private String ip;
        private String requestTime;
        private String tokenId;
        private String termType;
        private String deviceId;
        private String clientName;
        private String secretKeyId;

        public HeaderBean() {
            this.clientVersion = Utils.getVersionName();
            this.systemVersion = android.os.Build.VERSION.SDK_INT+"";
            this.ip = "192.168.1.102";
            this.requestTime = Utils.getCurrentTime(System.currentTimeMillis());
            this.tokenId = "";
            this.termType = "Android";
            this.deviceId = "";
            this.clientName = "ybz";
            this.secretKeyId = "";
        }
        public String getClientVersion() {
            return clientVersion;
        }

        public void setClientVersion(String clientVersion) {
            this.clientVersion = clientVersion;
        }

        public String getSystemVersion() {
            return systemVersion;
        }

        public void setSystemVersion(String systemVersion) {
            this.systemVersion = systemVersion;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getRequestTime() {
            return requestTime;
        }

        public void setRequestTime(String requestTime) {
            this.requestTime = requestTime;
        }

        public String getTokenId() {
            return tokenId;
        }

        public void setTokenId(String tokenId) {
            this.tokenId = tokenId;
        }

        public String getTermType() {
            return termType;
        }

        public void setTermType(String termType) {
            this.termType = termType;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public String getSecretKeyId() {
            return secretKeyId;
        }

        public void setSecretKeyId(String secretKeyId) {
            this.secretKeyId = secretKeyId;
        }
    }

    public static class BodyBean {
    }
}
