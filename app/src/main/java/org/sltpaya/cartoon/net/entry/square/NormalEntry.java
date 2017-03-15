package org.sltpaya.cartoon.net.entry.square;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.sltpaya.cartoon.net.entry.Entry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/3
 */
public class NormalEntry extends Entry {


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
        @SerializedName("userid")
        @Expose
        private int userid;

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
        @SerializedName("comment_num")
        @Expose
        private String commentNum;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("h")
        @Expose
        private String h;
        @SerializedName("head")
        @Expose
        private String head;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("page_count")
        @Expose
        private String pageCount;
        @SerializedName("praise")
        @Expose
        private String praise;
        @SerializedName("share_count")
        @Expose
        private String shareCount;
        @SerializedName("src_server_id")
        @Expose
        private String srcServerId;
        @SerializedName("thid")
        @Expose
        private String thid;
        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("userid")
        @Expose
        private String userid;
        @SerializedName("w")
        @Expose
        private String w;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(String commentNum) {
            this.commentNum = commentNum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getH() {
            return h;
        }

        public void setH(String h) {
            this.h = h;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPageCount() {
            return pageCount;
        }

        public void setPageCount(String pageCount) {
            this.pageCount = pageCount;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public String getShareCount() {
            return shareCount;
        }

        public void setShareCount(String shareCount) {
            this.shareCount = shareCount;
        }

        public String getSrcServerId() {
            return srcServerId;
        }

        public void setSrcServerId(String srcServerId) {
            this.srcServerId = srcServerId;
        }

        public String getThid() {
            return thid;
        }

        public void setThid(String thid) {
            this.thid = thid;
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

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getW() {
            return w;
        }

        public void setW(String w) {
            this.w = w;
        }

    }


}