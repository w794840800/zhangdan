package com.beidou.ybz.accountbook.mvp.entity;

import java.util.List;

/**
 * Author: ${Supreme} on 2017/12/19
 * QQ:977594142
 * E-mail:977594142@qq.com
 * module:我的首页model
 */

public class MyModel {

    /**
     * body : {"activityList":[{"activityPic":"upload/cms/bulletin/90d13b0173bb4d69a2d69450779bf079.jpg","activityTitle":"11111111111111111111111","activityUrl":"http://m.360caifu.test/html/cfh/account/property.html"},{"activityPic":"upload/cms/bulletin/9b815884374649f1a3b0ced833217b3c.png","activityTitle":"七夕活动","activityUrl":"http://m.360caifu.test/html/cfh/account/property.html"}],"badgeCount":"10","existNewBadge":"","messageCount":"0","nickName":"138****1217","portraitUrl":""}
     * header : {"code":"0000","desc":"成功","responseTime":"2018-03-21 14:16:00"}
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
         * activityList : [{"activityPic":"upload/cms/bulletin/90d13b0173bb4d69a2d69450779bf079.jpg","activityTitle":"11111111111111111111111","activityUrl":"http://m.360caifu.test/html/cfh/account/property.html"},{"activityPic":"upload/cms/bulletin/9b815884374649f1a3b0ced833217b3c.png","activityTitle":"七夕活动","activityUrl":"http://m.360caifu.test/html/cfh/account/property.html"}]
         * badgeCount : 10
         * existNewBadge :
         * messageCount : 0
         * nickName : 138****1217
         * portraitUrl :
         */

        private String badgeCount;
        private String existNewBadge;
        private String messageCount;
        private String nickName;
        private String portraitUrl;
        private List<ActivityListBean> activityList;

        public String getBadgeCount() {
            return badgeCount;
        }

        public void setBadgeCount(String badgeCount) {
            this.badgeCount = badgeCount;
        }

        public String getExistNewBadge() {
            return existNewBadge;
        }

        public void setExistNewBadge(String existNewBadge) {
            this.existNewBadge = existNewBadge;
        }

        public String getMessageCount() {
            return messageCount;
        }

        public void setMessageCount(String messageCount) {
            this.messageCount = messageCount;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPortraitUrl() {
            return portraitUrl;
        }

        public void setPortraitUrl(String portraitUrl) {
            this.portraitUrl = portraitUrl;
        }

        public List<ActivityListBean> getActivityList() {
            return activityList;
        }

        public void setActivityList(List<ActivityListBean> activityList) {
            this.activityList = activityList;
        }

        public static class ActivityListBean {
            /**
             * activityPic : upload/cms/bulletin/90d13b0173bb4d69a2d69450779bf079.jpg
             * activityTitle : 11111111111111111111111
             * activityUrl : http://m.360caifu.test/html/cfh/account/property.html
             */

            private String activityPic;
            private String activityTitle;
            private String activityUrl;

            public String getActivityPic() {
                return activityPic;
            }

            public void setActivityPic(String activityPic) {
                this.activityPic = activityPic;
            }

            public String getActivityTitle() {
                return activityTitle;
            }

            public void setActivityTitle(String activityTitle) {
                this.activityTitle = activityTitle;
            }

            public String getActivityUrl() {
                return activityUrl;
            }

            public void setActivityUrl(String activityUrl) {
                this.activityUrl = activityUrl;
            }
        }
    }

    public static class HeaderBean {
        /**
         * code : 0000
         * desc : 成功
         * responseTime : 2018-03-21 14:16:00
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
