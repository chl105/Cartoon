package org.sltpaya.cartoon.net.entry.square;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.sltpaya.cartoon.net.entry.Entry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/1
 */
public class HotEntry extends Entry {

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

    public class Data {

        @SerializedName("cdn")
        @Expose
        private String cdn;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;
        @SerializedName("end")
        @Expose
        private int end;
        @SerializedName("home")
        @Expose
        private int home;
        @SerializedName("start")
        @Expose
        private int start;
        @SerializedName("userid")
        @Expose
        private int userid;

        public String getCdn() {
            return cdn;
        }

        public void setCdn(String cdn) {
            this.cdn = cdn;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getHome() {
            return home;
        }

        public void setHome(int home) {
            this.home = home;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

    }

    public class Datum {

        @SerializedName("book_type")
        @Expose
        private String bookType;
        @SerializedName("bookid")
        @Expose
        private String bookid;
        @SerializedName("concern_num")
        @Expose
        private String concernNum;
        @SerializedName("create_time")
        @Expose
        private String createTime;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("introduction")
        @Expose
        private String introduction;
        @SerializedName("label_id")
        @Expose
        private String labelId;
        @SerializedName("labelname")
        @Expose
        private String labelname;
        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("work_num")
        @Expose
        private String workNum;

        public String getBookType() {
            return bookType;
        }

        public void setBookType(String bookType) {
            this.bookType = bookType;
        }

        public String getBookid() {
            return bookid;
        }

        public void setBookid(String bookid) {
            this.bookid = bookid;
        }

        public String getConcernNum() {
            return concernNum;
        }

        public void setConcernNum(String concernNum) {
            this.concernNum = concernNum;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

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

        public String getWorkNum() {
            return workNum;
        }

        public void setWorkNum(String workNum) {
            this.workNum = workNum;
        }

    }

}
