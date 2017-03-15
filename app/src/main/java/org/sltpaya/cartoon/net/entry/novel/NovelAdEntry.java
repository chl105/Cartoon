package org.sltpaya.cartoon.net.entry.novel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.sltpaya.cartoon.net.entry.Entry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/27
 */
public class NovelAdEntry extends Entry {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("advertise_type")
        @Expose
        private String advertiseType;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("cover")
        @Expose
        private String cover;
        @SerializedName("man_id")
        @Expose
        private String manId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("type_str")
        @Expose
        private String typeStr;

        public String getAdvertiseType() {
            return advertiseType;
        }

        public void setAdvertiseType(String advertiseType) {
            this.advertiseType = advertiseType;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getManId() {
            return manId;
        }

        public void setManId(String manId) {
            this.manId = manId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTypeStr() {
            return typeStr;
        }

        public void setTypeStr(String typeStr) {
            this.typeStr = typeStr;
        }

    }

}
