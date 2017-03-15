package org.sltpaya.cartoon.net.entry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/25
 */
public class UpdateDayEntry extends Entry {

    @SerializedName("conrhtml")
    @Expose
    private List<Conrhtml> conrhtml = null;
    @SerializedName("enId")
    @Expose
    private String enId;

    public List<Conrhtml> getConrhtml() {
        return conrhtml;
    }

    public void setConrhtml(List<Conrhtml> conrhtml) {
        this.conrhtml = conrhtml;
    }

    public String getEnId() {
        return enId;
    }

    public void setEnId(String enId) {
        this.enId = enId;
    }

    public class Conrhtml {

        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("bookid")
        @Expose
        private String bookid;
        @SerializedName("c_description")
        @Expose
        private String cDescription;
        @SerializedName("catid")
        @Expose
        private String catid;
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
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("update_chapter_name")
        @Expose
        private String updateChapterName;
        @SerializedName("updatetime")
        @Expose
        private String updatetime;
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

        public String getCDescription() {
            return cDescription;
        }

        public void setCDescription(String cDescription) {
            this.cDescription = cDescription;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

    }

}