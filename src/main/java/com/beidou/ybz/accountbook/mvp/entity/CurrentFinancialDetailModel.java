package com.beidou.ybz.accountbook.mvp.entity;

import java.util.List;

/**
 * Author: xu.yang on 2017/12/12
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:活期理财详情
 */
public class CurrentFinancialDetailModel {

    /**
     * body : {"curfinDto":{"amount":10000,"assetId":"0003","currentType":"5","delFlag":"1","id":23,"productName":"自定义产品名称","userNo":"10051550"},"proList":[],"zsz":0}
     * header : {"code":"0000","desc":"成功","responseTime":"2017-12-14 17:57:27"}
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
         * curfinDto : {"amount":10000,"assetId":"0003","currentType":"5","delFlag":"1","id":23,"productName":"自定义产品名称","userNo":"10051550"}
         * proList : []
         * zsz : 0
         */

        private CurfinDtoBean curfinDto;
        private String zsz;
        private List<?> proList;

        public CurfinDtoBean getCurfinDto() {
            return curfinDto;
        }

        public void setCurfinDto(CurfinDtoBean curfinDto) {
            this.curfinDto = curfinDto;
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

        public static class CurfinDtoBean {
            /**
             * amount : 10000
             * assetId : 0003
             * currentType : 5
             * delFlag : 1
             * id : 23
             * productName : 自定义产品名称
             * userNo : 10051550
             */

            private String amount;
            private String assetId;
            private String currentType;
            private String delFlag;
            private String id;
            private String productName;
            private String userNo;

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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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
         * responseTime : 2017-12-14 17:57:27
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
