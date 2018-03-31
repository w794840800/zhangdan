package com.beidou.ybz.accountbook.mvp.entity;

/**
 * Author: ${Supreme} on 2018/3/26
 * QQ:977594142
 * E-mail:977594142@qq.com
 * module:接收通知的实体类
 */

public class PushModel {

    /**
     * display_type : notification
     * extra : {"messageType":"h5","url":"https://www.baidu.com/"}
     * msg_id : umw5wfj152205796882700
     * body : {"after_open":"go_app","ticker":"Android unicast ticker","text":"dgs","title":"nkjnknk"}
     * random_min : 0
     */

    private String display_type;
    private ExtraBean extra;
    private String msg_id;
    private BodyBean body;
    private int random_min;

    public String getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(String display_type) {
        this.display_type = display_type;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public int getRandom_min() {
        return random_min;
    }

    public void setRandom_min(int random_min) {
        this.random_min = random_min;
    }

    public static class ExtraBean {
        /**
         * messageType : h5
         * url : https://www.baidu.com/
         */

        private String messageType;
        private String url;
        private String param;

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }
    }

    public static class BodyBean {
        /**
         * after_open : go_app
         * ticker : Android unicast ticker
         * text : dgs
         * title : nkjnknk
         */

        private String after_open;
        private String ticker;
        private String text;
        private String title;

        public String getAfter_open() {
            return after_open;
        }

        public void setAfter_open(String after_open) {
            this.after_open = after_open;
        }

        public String getTicker() {
            return ticker;
        }

        public void setTicker(String ticker) {
            this.ticker = ticker;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
