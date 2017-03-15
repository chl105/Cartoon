package org.sltpaya.cartoon.net.entry;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/22
 */
public class ClassifyEntry extends Entry {

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

        @SerializedName("src_host")
        @Expose
        private String srcHost;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;

        public String getSrcHost() {
            return srcHost;
        }

        public void setSrcHost(String srcHost) {
            this.srcHost = srcHost;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }
    }


    public class Datum {

        @SerializedName("label_id")
        @Expose
        private String labelId;
        @SerializedName("labelname")
        @Expose
        private String labelname;
        @SerializedName("pic_url")
        @Expose
        private String picUrl;

        public String getLabelId() {
            return labelId;
        }

        public void setLabelId(String labelId) {
            this.labelId = labelId;
        }

        public String getLabelname() {
            return labelname;
        }

        public void setLabelname(String labelname) {
            this.labelname = labelname;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

    }


}
