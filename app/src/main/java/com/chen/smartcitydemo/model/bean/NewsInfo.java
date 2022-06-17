package com.chen.smartcitydemo.model.bean;

public class NewsInfo {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":"1","createTime":"2021-04-01 11:56:38","updateBy":"1","updateTime":"2021-11-17 18:49:59","remark":null,"params":{},"id":5,"appType":"movie","cover":"/prod-api/profile/upload/image/2021/05/06/72bfbc63-268e-4abf-b497-c9dca9bf431a.jpg","title":"悬崖之上","subTitle":"","content":"<p>导演: 张艺谋<\/p><p>编剧: 全勇先 / 张艺谋<\/p><p>主演: 张译 / 于和伟 / 秦海璐 / 朱亚文 / 刘浩存 /<\/p><p><span style=\"color: rgb(102, 102, 102);\">类型:<\/span><span style=\"color: rgb(17, 17, 17);\">&nbsp;剧情&nbsp;/&nbsp;动作&nbsp;/&nbsp;悬疑<\/span><\/p><p><span style=\"color: rgb(102, 102, 102);\">制片国家/地区:<\/span><span style=\"color: rgb(17, 17, 17);\">&nbsp;中国大陆 / 中国香港<\/span><\/p><p><span style=\"color: rgb(102, 102, 102);\">语言:<\/span><span style=\"color: rgb(17, 17, 17);\">&nbsp;汉语普通话 / 英语<\/span><\/p><p><span style=\"color: rgb(102, 102, 102);\">上映日期:<\/span><span style=\"color: rgb(17, 17, 17);\">&nbsp;2021-04-30(中国大陆)<\/span><\/p><p><span style=\"color: rgb(102, 102, 102);\">片长:<\/span><span style=\"color: rgb(17, 17, 17);\">&nbsp;120分钟<\/span><\/p><p><span style=\"color: rgb(102, 102, 102);\">又名:<\/span><span style=\"color: rgb(17, 17, 17);\">&nbsp;Impasse / Cliff Walkers<\/span><\/p><p><span style=\"color: rgb(17, 17, 17);\">-<\/span><\/p><h3><strong>悬崖之上的剧情简介&nbsp;· · · · · ·<\/strong><\/h3><p> 上世纪三十年代，四位曾在苏联接受特训的共产党特工组成任务小队，回国执行代号为\u201c乌特拉\u201d的秘密行动。由于叛徒的出卖，他们从跳伞降落的第一刻起， 就已置身于敌人布下的罗网之中。同志能否脱身，任务能否完成，雪一直下，立于\u201c悬崖之上\u201d的行动小组面临严峻考验。<\/p><p>-<\/p>","status":"Y","publishDate":"2021-05-06","tags":null,"commentNum":112,"likeNum":321,"readNum":2003,"type":"2","top":"Y","hot":"Y"}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * searchValue : null
         * createBy : 1
         * createTime : 2021-04-01 11:56:38
         * updateBy : 1
         * updateTime : 2021-11-17 18:49:59
         * remark : null
         * params : {}
         * id : 5
         * appType : movie
         * cover : /prod-api/profile/upload/image/2021/05/06/72bfbc63-268e-4abf-b497-c9dca9bf431a.jpg
         * title : 悬崖之上
         * subTitle :
         * content : <p>导演: 张艺谋</p><p>编剧: 全勇先 / 张艺谋</p><p>主演: 张译 / 于和伟 / 秦海璐 / 朱亚文 / 刘浩存 /</p><p><span style="color: rgb(102, 102, 102);">类型:</span><span style="color: rgb(17, 17, 17);">&nbsp;剧情&nbsp;/&nbsp;动作&nbsp;/&nbsp;悬疑</span></p><p><span style="color: rgb(102, 102, 102);">制片国家/地区:</span><span style="color: rgb(17, 17, 17);">&nbsp;中国大陆 / 中国香港</span></p><p><span style="color: rgb(102, 102, 102);">语言:</span><span style="color: rgb(17, 17, 17);">&nbsp;汉语普通话 / 英语</span></p><p><span style="color: rgb(102, 102, 102);">上映日期:</span><span style="color: rgb(17, 17, 17);">&nbsp;2021-04-30(中国大陆)</span></p><p><span style="color: rgb(102, 102, 102);">片长:</span><span style="color: rgb(17, 17, 17);">&nbsp;120分钟</span></p><p><span style="color: rgb(102, 102, 102);">又名:</span><span style="color: rgb(17, 17, 17);">&nbsp;Impasse / Cliff Walkers</span></p><p><span style="color: rgb(17, 17, 17);">-</span></p><h3><strong>悬崖之上的剧情简介&nbsp;· · · · · ·</strong></h3><p> 上世纪三十年代，四位曾在苏联接受特训的共产党特工组成任务小队，回国执行代号为“乌特拉”的秘密行动。由于叛徒的出卖，他们从跳伞降落的第一刻起， 就已置身于敌人布下的罗网之中。同志能否脱身，任务能否完成，雪一直下，立于“悬崖之上”的行动小组面临严峻考验。</p><p>-</p>
         * status : Y
         * publishDate : 2021-05-06
         * tags : null
         * commentNum : 112
         * likeNum : 321
         * readNum : 2003
         * type : 2
         * top : Y
         * hot : Y
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String appType;
        private String cover;
        private String title;
        private String subTitle;
        private String content;
        private String status;
        private String publishDate;
        private Object tags;
        private int commentNum;
        private int likeNum;
        private int readNum;
        private String type;
        private String top;
        private String hot;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public Object getTags() {
            return tags;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public int getReadNum() {
            return readNum;
        }

        public void setReadNum(int readNum) {
            this.readNum = readNum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "searchValue=" + searchValue +
                    ", createBy='" + createBy + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", updateBy='" + updateBy + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", remark=" + remark +
                    ", params=" + params +
                    ", id=" + id +
                    ", appType='" + appType + '\'' +
                    ", cover='" + cover + '\'' +
                    ", title='" + title + '\'' +
                    ", subTitle='" + subTitle + '\'' +
                    ", content='" + content + '\'' +
                    ", status='" + status + '\'' +
                    ", publishDate='" + publishDate + '\'' +
                    ", tags=" + tags +
                    ", commentNum=" + commentNum +
                    ", likeNum=" + likeNum +
                    ", readNum=" + readNum +
                    ", type='" + type + '\'' +
                    ", top='" + top + '\'' +
                    ", hot='" + hot + '\'' +
                    '}';
        }

        public static class ParamsBean {
        }
    }
}
