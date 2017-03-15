package org.sltpaya.cartoon.net.entry.square;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.sltpaya.cartoon.net.entry.Entry;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/3
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

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("man_id")
        @Expose
        private String manId;
        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("type")
        @Expose
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getManId() {
            return manId;
        }

        public void setManId(String manId) {
            this.manId = manId;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }
}
