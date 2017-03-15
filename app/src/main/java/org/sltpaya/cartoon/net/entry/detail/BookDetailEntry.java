package org.sltpaya.cartoon.net.entry.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.sltpaya.cartoon.net.entry.Entry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/5
 */
public class BookDetailEntry extends Entry {

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

        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("author_userid")
        @Expose
        private String authorUserid;
        @SerializedName("average_score")
        @Expose
        private String averageScore;
        @SerializedName("bookid")
        @Expose
        private String bookid;
        @SerializedName("catid")
        @Expose
        private String catid;
        @SerializedName("comment_num")
        @Expose
        private String commentNum;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("get_coin")
        @Expose
        private String getCoin;
        @SerializedName("gx_type")
        @Expose
        private String gxType;
        @SerializedName("hit_num")
        @Expose
        private String hitNum;
        @SerializedName("label")
        @Expose
        private List<Label> label = null;
        @SerializedName("labeltwo")
        @Expose
        private List<Object> labeltwo = null;
        @SerializedName("status_bz")
        @Expose
        private String statusBz;
        @SerializedName("theme_id")
        @Expose
        private String themeId;
        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("thumb_2")
        @Expose
        private String thumb2;
        @SerializedName("thumb_large")
        @Expose
        private String thumbLarge;
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

        public String getAuthorUserid() {
            return authorUserid;
        }

        public void setAuthorUserid(String authorUserid) {
            this.authorUserid = authorUserid;
        }

        public String getAverageScore() {
            return averageScore;
        }

        public void setAverageScore(String averageScore) {
            this.averageScore = averageScore;
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

        public String getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(String commentNum) {
            this.commentNum = commentNum;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getGetCoin() {
            return getCoin;
        }

        public void setGetCoin(String getCoin) {
            this.getCoin = getCoin;
        }

        public String getGxType() {
            return gxType;
        }

        public void setGxType(String gxType) {
            this.gxType = gxType;
        }

        public String getHitNum() {
            return hitNum;
        }

        public void setHitNum(String hitNum) {
            this.hitNum = hitNum;
        }

        public List<Label> getLabel() {
            return label;
        }

        public void setLabel(List<Label> label) {
            this.label = label;
        }

        public List<Object> getLabeltwo() {
            return labeltwo;
        }

        public void setLabeltwo(List<Object> labeltwo) {
            this.labeltwo = labeltwo;
        }

        public String getStatusBz() {
            return statusBz;
        }

        public void setStatusBz(String statusBz) {
            this.statusBz = statusBz;
        }

        public String getThemeId() {
            return themeId;
        }

        public void setThemeId(String themeId) {
            this.themeId = themeId;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getThumb2() {
            return thumb2;
        }

        public void setThumb2(String thumb2) {
            this.thumb2 = thumb2;
        }

        public String getThumbLarge() {
            return thumbLarge;
        }

        public void setThumbLarge(String thumbLarge) {
            this.thumbLarge = thumbLarge;
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


    public class Label {

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
