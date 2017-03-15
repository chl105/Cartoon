package org.sltpaya.cartoon.net.entry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class BannerEntry extends Entry {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("msg")
    @Expose
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public class Datum {

        @SerializedName("advertise_type")
        @Expose
        private String advertiseType;
        @SerializedName("cover")
        @Expose
        private String cover;
        @SerializedName("man_id")
        @Expose
        private String manId;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("view_type")
        @Expose
        private String viewType;

        public String getAdvertiseType() {
            return advertiseType;
        }

        public void setAdvertiseType(String advertiseType) {
            this.advertiseType = advertiseType;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getViewType() {
            return viewType;
        }

        public void setViewType(String viewType) {
            this.viewType = viewType;
        }

    }
}
