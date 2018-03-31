package com.beidou.ybz.accountbook.mvp.entity;

import java.util.List;

/**
 * Author: xu.yang on 2017/12/12
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:活期理财列表
 */
public class CurrentFinancialListModel {

    /**
     * body : {"curfinDto":null,"proList":[{"amount":1000,"assetId":"0003","currentType":"1","delFlag":"1","productName":"","userNo":"10051550"},{"amount":1000,"assetId":"0003","currentType":"1","delFlag":"1","productName":"","userNo":"10051550"},{"amount":1000,"assetId":"0003","currentType":"1","delFlag":"1","productName":"","userNo":"10051550"},{"amount":1000,"assetId":"0003","currentType":"1","delFlag":"1","productName":"","userNo":"10051550"},{"amount":1000,"assetId":"0003","currentType":"1","delFlag":"1","productName":"","userNo":"10051550"}]}
     * header : {"code":"0000","desc":"成功","responseTime":"2017-12-12 16:57:56"}
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
         * curfinDto : null
         * proList : [{"amount":1000,"assetId":"0003","currentType":"1","delFlag":"1","productName":"","userNo":"10051550"},{"amount":1000,"assetId":"0003","currentType":"1","delFlag":"1","productName":"","userNo":"10051550"},{"amount":1000,"assetId":"0003","currentType":"1","delFlag":"1","productName":"","userNo":"10051550"},{"amount":1000,"assetId":"0003","currentType":"1","delFlag":"1","productName":"","userNo":"10051550"},{"amount":1000,"assetId":"0003","currentType":"1","delFlag":"1","productName":"","userNo":"10051550"}]
         */

        private String curfinDto;
        private String zsz;

        public String getZsz() {
            return zsz;
        }

        public void setZsz(String zsz) {
            this.zsz = zsz;
        }

        private List<ProListBean> proList;

        public String getCurfinDto() {
            return curfinDto;
        }

        public void setCurfinDto(String curfinDto) {
            this.curfinDto = curfinDto;
        }

        public List<ProListBean> getProList() {
            return proList;
        }

        public void setProList(List<ProListBean> proList) {
            this.proList = proList;
        }

        public static class ProListBean {
            /**
             * amount : 1000
             * assetId : 0003
             * currentType : 1
             * delFlag : 1
             * productName :
             * userNo : 10051550
             */

            private String amount;
            private String assetId;
            private String currentType;
            private String delFlag;
            private String productName;
            private String userNo;
            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

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

            public String getCurrentType() {
                return currentType;
            }

            public void setCurrentType(String currentType) {
                this.currentType = currentType;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
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
        }
    }

    public static class HeaderBean {
        /**
         * code : 0000
         * desc : 成功
         * responseTime : 2017-12-12 16:57:56
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
