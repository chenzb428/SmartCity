package com.chen.smartcitydemo.model.bean;

import java.util.List;

public class Comment {

    /**
     * total : 18
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":210,"appType":"smart_city","newsId":10,"content":"评论测试","commentDate":"2021-10-23 14:44:53","userId":1111256,"likeNum":0,"userName":"xxin","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":211,"appType":"smart_city","newsId":10,"content":"评论测试2","commentDate":"2021-10-23 14:45:04","userId":1111256,"likeNum":0,"userName":"xxin","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":212,"appType":"smart_city","newsId":10,"content":"评论测试3","commentDate":"2021-10-23 14:45:10","userId":1111256,"likeNum":0,"userName":"xxin","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":213,"appType":"smart_city","newsId":10,"content":"评论测试4","commentDate":"2021-10-23 14:45:13","userId":1111256,"likeNum":0,"userName":"xxin","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":214,"appType":"smart_city","newsId":10,"content":"评论测试5","commentDate":"2021-10-23 14:45:16","userId":1111256,"likeNum":42,"userName":"xxin","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":215,"appType":"smart_city","newsId":10,"content":"评论测试6","commentDate":"2021-10-23 14:45:19","userId":1111256,"likeNum":15,"userName":"xxin","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":216,"appType":"smart_city","newsId":10,"content":"评论测试7","commentDate":"2021-10-23 14:45:23","userId":1111256,"likeNum":5,"userName":"xxin","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":217,"appType":"smart_city","newsId":10,"content":"完美","commentDate":"2021-10-23 14:45:27","userId":1111256,"likeNum":0,"userName":"xxin","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1202,"appType":"smart_city","newsId":10,"content":"加我微信啊JackeyA06共同学习交流啊","commentDate":"2021-10-26 16:09:39","userId":1111122,"likeNum":2,"userName":"test01","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1531,"appType":"smart_city","newsId":10,"content":"加我微信啊JackeyA06共同学习交流啊","commentDate":"2021-10-26 16:10:59","userId":1111122,"likeNum":0,"userName":"test01","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1672,"appType":"smart_city","newsId":10,"content":"加我微信啊JackeyA06共同学习交流啊","commentDate":"2021-10-26 16:13:29","userId":1111122,"likeNum":0,"userName":"test01","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2367,"appType":"smart_city","newsId":10,"content":"加我微信啊JackeyA06共同学习交流啊","commentDate":"2021-10-26 16:13:42","userId":1111122,"likeNum":0,"userName":"test01","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":5883,"appType":"smart_city","newsId":10,"content":"12312312","commentDate":"2021-11-05 14:49:13","userId":1111942,"likeNum":0,"userName":"111910185","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":5884,"appType":"smart_city","newsId":10,"content":"123123123","commentDate":"2021-11-05 14:49:21","userId":1111942,"likeNum":0,"userName":"111910185","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":6277,"appType":"smart_city","newsId":10,"content":"123","commentDate":"2021-11-14 23:40:04","userId":1112190,"likeNum":0,"userName":"lzy003","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7341,"appType":"smart_city","newsId":10,"content":"qwee12","commentDate":"2021-11-24 14:23:27","userId":1111412,"likeNum":0,"userName":"object","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7346,"appType":"smart_city","newsId":10,"content":"``````","commentDate":"2021-11-24 14:23:53","userId":1111412,"likeNum":0,"userName":"object","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7347,"appType":"smart_city","newsId":10,"content":"``````","commentDate":"2021-11-24 14:23:53","userId":1111412,"likeNum":0,"userName":"object","newsTitle":"北京市\u201c十四五\u201d时期智慧城市发展行动纲要"}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "total=" + total +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", rows=" + rows +
                '}';
    }

    public static class RowsBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 210
         * appType : smart_city
         * newsId : 10
         * content : 评论测试
         * commentDate : 2021-10-23 14:44:53
         * userId : 1111256
         * likeNum : 0
         * userName : xxin
         * newsTitle : 北京市“十四五”时期智慧城市发展行动纲要
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String appType;
        private int newsId;
        private String content;
        private String commentDate;
        private int userId;
        private int likeNum;
        private String userName;
        private String newsTitle;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
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

        public int getNewsId() {
            return newsId;
        }

        public void setNewsId(int newsId) {
            this.newsId = newsId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCommentDate() {
            return commentDate;
        }

        public void setCommentDate(String commentDate) {
            this.commentDate = commentDate;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNewsTitle() {
            return newsTitle;
        }

        public void setNewsTitle(String newsTitle) {
            this.newsTitle = newsTitle;
        }

        @Override
        public String toString() {
            return "RowsBean{" +
                    "searchValue=" + searchValue +
                    ", createBy=" + createBy +
                    ", createTime=" + createTime +
                    ", updateBy=" + updateBy +
                    ", updateTime=" + updateTime +
                    ", remark=" + remark +
                    ", params=" + params +
                    ", id=" + id +
                    ", appType='" + appType + '\'' +
                    ", newsId=" + newsId +
                    ", content='" + content + '\'' +
                    ", commentDate='" + commentDate + '\'' +
                    ", userId=" + userId +
                    ", likeNum=" + likeNum +
                    ", userName='" + userName + '\'' +
                    ", newsTitle='" + newsTitle + '\'' +
                    '}';
        }

        public static class ParamsBean {
        }
    }
}
