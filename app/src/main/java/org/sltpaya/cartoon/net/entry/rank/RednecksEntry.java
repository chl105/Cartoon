package org.sltpaya.cartoon.net.entry.rank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.sltpaya.cartoon.net.entry.Entry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/13
 */
public class RednecksEntry extends Entry {

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
        @SerializedName("start")
        @Expose
        private int start;

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

    }

    public class Datum {

        @SerializedName("head")
        @Expose
        private String head;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("nickname")
        @Expose
        private String nickname;
        @SerializedName("rank_num")
        @Expose
        private String rankNum;
        @SerializedName("user_lv_title")
        @Expose
        private UserLvTitle userLvTitle;
        @SerializedName("userid")
        @Expose
        private String userid;

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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRankNum() {
            return rankNum;
        }

        public void setRankNum(String rankNum) {
            this.rankNum = rankNum;
        }

        public UserLvTitle getUserLvTitle() {
            return userLvTitle;
        }

        public void setUserLvTitle(UserLvTitle userLvTitle) {
            this.userLvTitle = userLvTitle;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
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
}
