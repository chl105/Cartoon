package org.sltpaya.cartoon.net.entry.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.sltpaya.cartoon.net.entry.Entry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/7
 */
public class AuthorEntry extends Entry {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

        @SerializedName("bid")
        @Expose
        private Integer bid;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;
        @SerializedName("end")
        @Expose
        private String end;
        @SerializedName("start")
        @Expose
        private Integer start;
        @SerializedName("userid")
        @Expose
        private Integer userid;

        public Integer getBid() {
            return bid;
        }

        public void setBid(Integer bid) {
            this.bid = bid;
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

        public Integer getStart() {
            return start;
        }

        public void setStart(Integer start) {
            this.start = start;
        }

        public Integer getUserid() {
            return userid;
        }

        public void setUserid(Integer userid) {
            this.userid = userid;
        }
    }


    public class Datum {

        @SerializedName("bookid")
        @Expose
        private String bookid;
        @SerializedName("chapterid")
        @Expose
        private String chapterid;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("creat_at")
        @Expose
        private String creatAt;
        @SerializedName("floor_id")
        @Expose
        private String floorId;
        @SerializedName("from")
        @Expose
        private String from;
        @SerializedName("head")
        @Expose
        private String head;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("is_reply_del")
        @Expose
        private Integer isReplyDel;
        @SerializedName("mes_id")
        @Expose
        private String mesId;
        @SerializedName("reply")
        @Expose
        private String reply;
        @SerializedName("reply_count")
        @Expose
        private String replyCount;
        @SerializedName("reply_data")
        @Expose
        private ReplyData replyData;
        @SerializedName("support")
        @Expose
        private String support;
        @SerializedName("user_lv_title")
        @Expose
        private UserLvTitle_ userLvTitle;
        @SerializedName("user_title")
        @Expose
        private UserTitle_ userTitle;
        @SerializedName("user_type")
        @Expose
        private String userType;
        @SerializedName("userid")
        @Expose
        private String userid;
        @SerializedName("username")
        @Expose
        private String username;

        public String getBookid() {
            return bookid;
        }

        public void setBookid(String bookid) {
            this.bookid = bookid;
        }

        public String getChapterid() {
            return chapterid;
        }

        public void setChapterid(String chapterid) {
            this.chapterid = chapterid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreatAt() {
            return creatAt;
        }

        public void setCreatAt(String creatAt) {
            this.creatAt = creatAt;
        }

        public String getFloorId() {
            return floorId;
        }

        public void setFloorId(String floorId) {
            this.floorId = floorId;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
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

        public Integer getIsReplyDel() {
            return isReplyDel;
        }

        public void setIsReplyDel(Integer isReplyDel) {
            this.isReplyDel = isReplyDel;
        }

        public String getMesId() {
            return mesId;
        }

        public void setMesId(String mesId) {
            this.mesId = mesId;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        public String getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(String replyCount) {
            this.replyCount = replyCount;
        }

        public ReplyData getReplyData() {
            return replyData;
        }

        public void setReplyData(ReplyData replyData) {
            this.replyData = replyData;
        }

        public String getSupport() {
            return support;
        }

        public void setSupport(String support) {
            this.support = support;
        }

        public UserLvTitle_ getUserLvTitle() {
            return userLvTitle;
        }

        public void setUserLvTitle(UserLvTitle_ userLvTitle) {
            this.userLvTitle = userLvTitle;
        }

        public UserTitle_ getUserTitle() {
            return userTitle;
        }

        public void setUserTitle(UserTitle_ userTitle) {
            this.userTitle = userTitle;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    }

    public class ReplyData {

        @SerializedName("bookid")
        @Expose
        private String bookid;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("creat_at")
        @Expose
        private String creatAt;
        @SerializedName("floor_id")
        @Expose
        private String floorId;
        @SerializedName("head")
        @Expose
        private String head;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("reply")
        @Expose
        private String reply;
        @SerializedName("reply_count")
        @Expose
        private String replyCount;
        @SerializedName("support")
        @Expose
        private String support;
        @SerializedName("user_lv_title")
        @Expose
        private UserLvTitle userLvTitle;
        @SerializedName("user_title")
        @Expose
        private UserTitle userTitle;
        @SerializedName("user_type")
        @Expose
        private String userType;
        @SerializedName("userid")
        @Expose
        private String userid;
        @SerializedName("username")
        @Expose
        private String username;

        public String getBookid() {
            return bookid;
        }

        public void setBookid(String bookid) {
            this.bookid = bookid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreatAt() {
            return creatAt;
        }

        public void setCreatAt(String creatAt) {
            this.creatAt = creatAt;
        }

        public String getFloorId() {
            return floorId;
        }

        public void setFloorId(String floorId) {
            this.floorId = floorId;
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

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        public String getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(String replyCount) {
            this.replyCount = replyCount;
        }

        public String getSupport() {
            return support;
        }

        public void setSupport(String support) {
            this.support = support;
        }

        public UserLvTitle getUserLvTitle() {
            return userLvTitle;
        }

        public void setUserLvTitle(UserLvTitle userLvTitle) {
            this.userLvTitle = userLvTitle;
        }

        public UserTitle getUserTitle() {
            return userTitle;
        }

        public void setUserTitle(UserTitle userTitle) {
            this.userTitle = userTitle;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    }

    public class UserLvTitle {

        @SerializedName("exp_lv_id")
        @Expose
        private String expLvId;
        @SerializedName("exp_lv_title")
        @Expose
        private String expLvTitle;

        public String getExpLvId() {
            return expLvId;
        }

        public void setExpLvId(String expLvId) {
            this.expLvId = expLvId;
        }

        public String getExpLvTitle() {
            return expLvTitle;
        }

        public void setExpLvTitle(String expLvTitle) {
            this.expLvTitle = expLvTitle;
        }

    }

    public class UserLvTitle_ {

        @SerializedName("exp_lv_id")
        @Expose
        private String expLvId;
        @SerializedName("exp_lv_title")
        @Expose
        private String expLvTitle;

        public String getExpLvId() {
            return expLvId;
        }

        public void setExpLvId(String expLvId) {
            this.expLvId = expLvId;
        }

        public String getExpLvTitle() {
            return expLvTitle;
        }

        public void setExpLvTitle(String expLvTitle) {
            this.expLvTitle = expLvTitle;
        }

    }

    public class UserTitle {

        @SerializedName("cur_title")
        @Expose
        private String curTitle;
        @SerializedName("title_id")
        @Expose
        private Integer titleId;

        public String getCurTitle() {
            return curTitle;
        }

        public void setCurTitle(String curTitle) {
            this.curTitle = curTitle;
        }

        public Integer getTitleId() {
            return titleId;
        }

        public void setTitleId(Integer titleId) {
            this.titleId = titleId;
        }

    }

    public class UserTitle_ {

        @SerializedName("cur_title")
        @Expose
        private String curTitle;
        @SerializedName("title_id")
        @Expose
        private String titleId;

        public String getCurTitle() {
            return curTitle;
        }

        public void setCurTitle(String curTitle) {
            this.curTitle = curTitle;
        }

        public String getTitleId() {
            return titleId;
        }

        public void setTitleId(String titleId) {
            this.titleId = titleId;
        }

    }
}
