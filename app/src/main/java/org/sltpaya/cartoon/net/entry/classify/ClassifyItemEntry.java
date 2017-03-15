package org.sltpaya.cartoon.net.entry.classify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.sltpaya.cartoon.net.entry.Entry;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/9
 */
public class ClassifyItemEntry extends Entry {

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
        @SerializedName("end")
        @Expose
        private String end;
        @SerializedName("error_line")
        @Expose
        private int errorLine;
        @SerializedName("label_type")
        @Expose
        private int labelType;
        @SerializedName("start")
        @Expose
        private int start;
        @SerializedName("tag")
        @Expose
        private int tag;
        @SerializedName("type")
        @Expose
        private int type;
        @SerializedName("userid")
        @Expose
        private int userid;

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public int getErrorLine() {
            return errorLine;
        }

        public void setErrorLine(int errorLine) {
            this.errorLine = errorLine;
        }

        public int getLabelType() {
            return labelType;
        }

        public void setLabelType(int labelType) {
            this.labelType = labelType;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
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
        @SerializedName("rank_id")
        @Expose
        private String rankId;
        @SerializedName("status_bz")
        @Expose
        private String statusBz;
        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("thumb_big")
        @Expose
        private String thumbBig;
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
        @SerializedName("views")
        @Expose
        private String views;

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

        public String getRankId() {
            return rankId;
        }

        public void setRankId(String rankId) {
            this.rankId = rankId;
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

        public String getThumbBig() {
            return thumbBig;
        }

        public void setThumbBig(String thumbBig) {
            this.thumbBig = thumbBig;
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

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public String getViewType() {
            return viewType;
        }

        public void setViewType(String viewType) {
            this.viewType = viewType;
        }
    }


}