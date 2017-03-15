package org.sltpaya.cartoon.net.entry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/22
 */
public class BarCartoonEntry extends Entry {

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


    public class ClassLabel {

        @SerializedName("class_id")
        @Expose
        private String classId;
        @SerializedName("class_name")
        @Expose
        private String className;

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

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
        private String end;
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

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
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
        @SerializedName("author_userid")
        @Expose
        private String authorUserid;
        @SerializedName("bookid")
        @Expose
        private String bookid;
        @SerializedName("car_number")
        @Expose
        private String carNumber;
        @SerializedName("class_label")
        @Expose
        private ClassLabel classLabel;
        @SerializedName("comment_num")
        @Expose
        private String commentNum;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("thumb_rank")
        @Expose
        private String thumbRank;
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

        public String getAuthorUserid() {
            return authorUserid;
        }

        public void setAuthorUserid(String authorUserid) {
            this.authorUserid = authorUserid;
        }

        public String getBookid() {
            return bookid;
        }

        public void setBookid(String bookid) {
            this.bookid = bookid;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public ClassLabel getClassLabel() {
            return classLabel;
        }

        public void setClassLabel(ClassLabel classLabel) {
            this.classLabel = classLabel;
        }

        public String getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(String commentNum) {
            this.commentNum = commentNum;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getThumbRank() {
            return thumbRank;
        }

        public void setThumbRank(String thumbRank) {
            this.thumbRank = thumbRank;
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
