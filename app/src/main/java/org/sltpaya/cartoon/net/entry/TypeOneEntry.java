package org.sltpaya.cartoon.net.entry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class TypeOneEntry extends Entry {

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

        @SerializedName("data")
        @Expose
        private List<Datum> data = null;
        @SerializedName("end")
        @Expose
        private int end;
        @SerializedName("start")
        @Expose
        private int start;
        @SerializedName("type")
        @Expose
        private int type;

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

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

    }


    public class Datum {

        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("bookid")
        @Expose
        private String bookid;
        @SerializedName("catid")
        @Expose
        private String catid;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("gx_type")
        @Expose
        private String gxType;
        @SerializedName("status_bz")
        @Expose
        private String statusBz;
        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("thumb_1")
        @Expose
        private String thumb1;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("typeid")
        @Expose
        private String typeid;
        @SerializedName("update_chapter_name")
        @Expose
        private String updateChapterName;
        @SerializedName("updatetime")
        @Expose
        private String updatetime;
        @SerializedName("view_type")
        @Expose
        private String viewType;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getBookid() {
            return bookid;
        }

        public void setBookid(String bookid) {
            this.bookid = bookid;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getGxType() {
            return gxType;
        }

        public void setGxType(String gxType) {
            this.gxType = gxType;
        }

        public String getStatusBz() {
            return statusBz;
        }

        public void setStatusBz(String statusBz) {
            this.statusBz = statusBz;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getThumb1() {
            return thumb1;
        }

        public void setThumb1(String thumb1) {
            this.thumb1 = thumb1;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getUpdateChapterName() {
            return updateChapterName;
        }

        public void setUpdateChapterName(String updateChapterName) {
            this.updateChapterName = updateChapterName;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getViewType() {
            return viewType;
        }

        public void setViewType(String viewType) {
            this.viewType = viewType;
        }

    }

}