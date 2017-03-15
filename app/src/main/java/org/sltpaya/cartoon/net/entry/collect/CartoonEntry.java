package org.sltpaya.cartoon.net.entry.collect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.sltpaya.cartoon.net.entry.Entry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/28
 */
public class CartoonEntry extends Entry {

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
        @SerializedName("get_type")
        @Expose
        private int getType;
        @SerializedName("home")
        @Expose
        private int home;
        @SerializedName("label")
        @Expose
        private int label;
        @SerializedName("start")
        @Expose
        private int start;
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

        public int getGetType() {
            return getType;
        }

        public void setGetType(int getType) {
            this.getType = getType;
        }

        public int getHome() {
            return home;
        }

        public void setHome(int home) {
            this.home = home;
        }

        public int getLabel() {
            return label;
        }

        public void setLabel(int label) {
            this.label = label;
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
        @SerializedName("label")
        @Expose
        private List<Object> label = null;
        @SerializedName("labeltwo")
        @Expose
        private List<Labeltwo> labeltwo = null;
        @SerializedName("rank_id")
        @Expose
        private String rankId;
        @SerializedName("status_bz")
        @Expose
        private String statusBz;
        @SerializedName("thumb")
        @Expose
        private String thumb;
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
        @SerializedName("views")
        @Expose
        private String views;

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

        public List<Object> getLabel() {
            return label;
        }

        public void setLabel(List<Object> label) {
            this.label = label;
        }

        public List<Labeltwo> getLabeltwo() {
            return labeltwo;
        }

        public void setLabeltwo(List<Labeltwo> labeltwo) {
            this.labeltwo = labeltwo;
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

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

    }

    public class Labeltwo {

        @SerializedName("label_id")
        @Expose
        private String labelId;
        @SerializedName("labelname")
        @Expose
        private String labelname;

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
    }

}
