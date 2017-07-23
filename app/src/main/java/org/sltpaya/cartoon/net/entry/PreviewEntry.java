package org.sltpaya.cartoon.net.entry;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/7/18
 */
public class PreviewEntry extends Entry{

    /**
     * code : 0
     * data : {"data":[{"author":"大叔酱","bookid":"90","catid":"32","description":"为了接近自己暗恋的女生，韦良男","gx_type":"9","rank_id":"1","show_label":"","status_bz":"1","thumb":"http://cdn.517w.com/uploadfile/2016/0919/thumb_20160919135351401.jpg","thumb_3":"http://cdn.517w.com/uploadfile/2016/1118/20161118111830393.jpg","title":"困病之笼","typeid":"0","update_chapter_name":"第450话","updatetime":"1500305006","view_type":"0","views":"18305765"},{"author":"常盘勇者/蝴蝶蓝","bookid":"662","catid":"32","description":"《","gx_type":"2","rank_id":"10","show_label":"","status_bz":"1","thumb":"http://cdn.517w.com/uploadfile/2016/0226/20160226114137427.jpg","thumb_3":"http://cdn.517w.com/uploadfile/2016/1118/20161118120539479.jpg","title":"全职高手","typeid":"0","update_chapter_name":"第141话","updatetime":"1500007076","view_type":"0","views":"6101964"}],"end":"10","get_type":0,"home":1,"label":0,"start":0,"userid":0}
     * msg : 成功
     */

    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private DataBeanX data;
    @SerializedName("msg")
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBeanX.DataBean> getDatas() {
        if (getData() != null) {
            return getData().getData();
        }
        return null;
    }

    public static class DataBeanX {
        /**
         * data : [{"author":"大叔酱","bookid":"90","catid":"32","description":"为了接近自己暗恋的女生，韦良男","gx_type":"9","rank_id":"1","show_label":"","status_bz":"1","thumb":"http://cdn.517w.com/uploadfile/2016/0919/thumb_20160919135351401.jpg","thumb_3":"http://cdn.517w.com/uploadfile/2016/1118/20161118111830393.jpg","title":"困病之笼","typeid":"0","update_chapter_name":"第450话","updatetime":"1500305006","view_type":"0","views":"18305765"},{"author":"常盘勇者/蝴蝶蓝","bookid":"662","catid":"32","description":"《","gx_type":"2","rank_id":"10","show_label":"","status_bz":"1","thumb":"http://cdn.517w.com/uploadfile/2016/0226/20160226114137427.jpg","thumb_3":"http://cdn.517w.com/uploadfile/2016/1118/20161118120539479.jpg","title":"全职高手","typeid":"0","update_chapter_name":"第141话","updatetime":"1500007076","view_type":"0","views":"6101964"}]
         * end : 10
         * get_type : 0
         * home : 1
         * label : 0
         * start : 0
         * userid : 0
         */

        @SerializedName("end")
        private String end;
        @SerializedName("get_type")
        private int getType;
        @SerializedName("home")
        private int home;
        @SerializedName("label")
        private int label;
        @SerializedName("start")
        private int start;
        @SerializedName("userid")
        private int userid;
        @SerializedName("data")
        private List<DataBean> data;

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

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * author : 大叔酱
             * bookid : 90
             * catid : 32
             * description : 为了接近自己暗恋的女生，韦良男
             * gx_type : 9
             * rank_id : 1
             * show_label :
             * status_bz : 1
             * thumb : http://cdn.517w.com/uploadfile/2016/0919/thumb_20160919135351401.jpg
             * thumb_3 : http://cdn.517w.com/uploadfile/2016/1118/20161118111830393.jpg
             * title : 困病之笼
             * typeid : 0
             * update_chapter_name : 第450话
             * updatetime : 1500305006
             * view_type : 0
             * views : 18305765
             */

            @SerializedName("author")
            private String author;
            @SerializedName("bookid")
            private String bookid;
            @SerializedName("catid")
            private String catid;
            @SerializedName("description")
            private String description;
            @SerializedName("gx_type")
            private String gxType;
            @SerializedName("rank_id")
            private String rankId;
            @SerializedName("show_label")
            private String showLabel;
            @SerializedName("status_bz")
            private String statusBz;
            @SerializedName("thumb")
            private String thumb;
            @SerializedName("thumb_3")
            private String thumb3;
            @SerializedName("title")
            private String title;
            @SerializedName("typeid")
            private String typeid;
            @SerializedName("update_chapter_name")
            private String updateChapterName;
            @SerializedName("updatetime")
            private String updatetime;
            @SerializedName("view_type")
            private String viewType;
            @SerializedName("views")
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

            public String getRankId() {
                return rankId;
            }

            public void setRankId(String rankId) {
                this.rankId = rankId;
            }

            public String getShowLabel() {
                return showLabel;
            }

            public void setShowLabel(String showLabel) {
                this.showLabel = showLabel;
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

            public String getThumb3() {
                return thumb3;
            }

            public void setThumb3(String thumb3) {
                this.thumb3 = thumb3;
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
    }
}
