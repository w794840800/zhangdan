package com.beidou.ybz.accountbook.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: xu.yang on 2017/12/10
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:股票搜索model
 */
public class StockSearchModel implements Parcelable {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Parcelable {
        /**
         * code : 002922
         * name : 伊戈尔
         * spell :
         * spellAll : eaglerise
         * type : stock
         * typeName : 股票
         */

        private String code;
        private String name;
        private String spell;
        private String spellAll;
        private String type;
        private String typeName;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSpell() {
            return spell;
        }

        public void setSpell(String spell) {
            this.spell = spell;
        }

        public String getSpellAll() {
            return spellAll;
        }

        public void setSpellAll(String spellAll) {
            this.spellAll = spellAll;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", spell='" + spell + '\'' +
                    ", spellAll='" + spellAll + '\'' +
                    ", type='" + type + '\'' +
                    ", typeName='" + typeName + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.code);
            dest.writeString(this.name);
            dest.writeString(this.spell);
            dest.writeString(this.spellAll);
            dest.writeString(this.type);
            dest.writeString(this.typeName);
        }

        public ResultBean() {
        }

        protected ResultBean(Parcel in) {
            this.code = in.readString();
            this.name = in.readString();
            this.spell = in.readString();
            this.spellAll = in.readString();
            this.type = in.readString();
            this.typeName = in.readString();
        }

        public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
            @Override
            public ResultBean createFromParcel(Parcel source) {
                return new ResultBean(source);
            }

            @Override
            public ResultBean[] newArray(int size) {
                return new ResultBean[size];
            }
        };
    }

    @Override
    public String toString() {
        return "StockSearchModel{" +
                "result=" + result +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.result);
    }

    public StockSearchModel() {
    }

    protected StockSearchModel(Parcel in) {
        this.result = new ArrayList<ResultBean>();
        in.readList(this.result, ResultBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<StockSearchModel> CREATOR = new Parcelable.Creator<StockSearchModel>() {
        @Override
        public StockSearchModel createFromParcel(Parcel source) {
            return new StockSearchModel(source);
        }

        @Override
        public StockSearchModel[] newArray(int size) {
            return new StockSearchModel[size];
        }
    };
}
