package com.beidou.ybz.accountbook.mvp.entity;

import java.util.List;

/**
 * Author: ${Supreme} on 2018/1/4
 * qq_share:977594142
 * E-mail:977594142@qq.com
 * module:
 */

public class HuodongListModel {

    /**
     * body : {"rows":[{"id":1,"image":"upload/cms/common/activity/9dfde53a6e1746e0b14b54196210b124.jpg","link":"http://www.baidu.com","name":"test","tag":"normal"},{"id":2,"image":"upload/cms/common/activity/140d8f08124b41999f2047171eca8386.jpg","link":"http://www.baidu.com","name":"test1","tag":"normal"},{"id":3,"image":"upload/cms/common/activity/fe0e32dd301a4777a309aa78e07e951b.png","link":"http://www.baidu.com","name":"test2","tag":"normal"},{"id":5,"image":"upload/cms/common/activity/3c58ace46ddf4c5f989e0bfafc2afdf6.jpg","link":"http://www.baidu.com","name":"test3","tag":"normal"},{"id":7,"image":"upload/cms/common/activity/88118037c6224a34a76d4933393fe5ef.png","link":"http://www.baidu.com","name":"test4","tag":"normal"},{"id":9,"image":"upload/cms/common/activity/2635e50d3acb4becbb3f6132590a405e.png","link":"http://www.baidu.com","name":"test5","tag":"normal"},{"id":12,"image":"upload/cms/common/activity/e335103f6dc545bab79af6d3c6cefd2e.png","link":"http://www.baidu.com","name":"test2121212","tag":"normal"},{"id":14,"image":"upload/cms/common/activity/f63a190035eb40ad94408a764c84dfc3.png","link":"http://www.baidu.com","name":"年会","tag":"news"},{"id":15,"image":"","link":"http://www.360caifu.com","name":"哈哈哈","tag":"news"},{"id":16,"image":"","link":"http://www.baidu.com","name":"劳而无功","tag":"news"}],"total":11}
     * header : {"code":"0000","desc":"成功","responseTime":"2018-01-04 10:37:28"}
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
         * rows : [{"id":1,"image":"upload/cms/common/activity/9dfde53a6e1746e0b14b54196210b124.jpg","link":"http://www.baidu.com","name":"test","tag":"normal"},{"id":2,"image":"upload/cms/common/activity/140d8f08124b41999f2047171eca8386.jpg","link":"http://www.baidu.com","name":"test1","tag":"normal"},{"id":3,"image":"upload/cms/common/activity/fe0e32dd301a4777a309aa78e07e951b.png","link":"http://www.baidu.com","name":"test2","tag":"normal"},{"id":5,"image":"upload/cms/common/activity/3c58ace46ddf4c5f989e0bfafc2afdf6.jpg","link":"http://www.baidu.com","name":"test3","tag":"normal"},{"id":7,"image":"upload/cms/common/activity/88118037c6224a34a76d4933393fe5ef.png","link":"http://www.baidu.com","name":"test4","tag":"normal"},{"id":9,"image":"upload/cms/common/activity/2635e50d3acb4becbb3f6132590a405e.png","link":"http://www.baidu.com","name":"test5","tag":"normal"},{"id":12,"image":"upload/cms/common/activity/e335103f6dc545bab79af6d3c6cefd2e.png","link":"http://www.baidu.com","name":"test2121212","tag":"normal"},{"id":14,"image":"upload/cms/common/activity/f63a190035eb40ad94408a764c84dfc3.png","link":"http://www.baidu.com","name":"年会","tag":"news"},{"id":15,"image":"","link":"http://www.360caifu.com","name":"哈哈哈","tag":"news"},{"id":16,"image":"","link":"http://www.baidu.com","name":"劳而无功","tag":"news"}]
         * total : 11
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : 1
             * image : upload/cms/common/activity/9dfde53a6e1746e0b14b54196210b124.jpg
             * link : http://www.baidu.com
             * name : test
             * tag : normal
             */

            private int id;
            private String image;
            private String link;
            private String name;
            private String tag;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }
        }
    }

    public static class HeaderBean {
        /**
         * code : 0000
         * desc : 成功
         * responseTime : 2018-01-04 10:37:28
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
