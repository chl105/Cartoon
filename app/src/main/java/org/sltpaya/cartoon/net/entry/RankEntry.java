package org.sltpaya.cartoon.net.entry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/22
 */
public class RankEntry extends Entry {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("msg")
    @Expose
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Datum> getDataList() {
        if (getData() != null) {
            return getData().getData();
        }
        return null;
    }

    public class Data {

        @SerializedName("data")
        @Expose
        private List<Datum> data = null;
        @SerializedName("src_host")
        @Expose
        private String srcHost;

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

        public String getSrcHost() {
            return srcHost;
        }

        public void setSrcHost(String srcHost) {
            this.srcHost = srcHost;
        }

    }


    public class Datum {

        @SerializedName("pic_url2")
        @Expose
        private String picUrl2;
        @SerializedName("pic_url")
        @Expose
        private String picUrl;
        @SerializedName("rank_id")
        @Expose
        private String rankId;
        @SerializedName("rank_name")
        @Expose
        private String rankName;

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getPicUrl2() {
            return picUrl2;
        }

        public void setPicUrl2(String picUrl2) {
            this.picUrl2 = picUrl2;
        }

        public String getRankId() {
            return rankId;
        }

        public void setRankId(String rankId) {
            this.rankId = rankId;
        }

        public String getRankName() {
            return rankName;
        }

        public void setRankName(String rankName) {
            this.rankName = rankName;
        }

    }


}
