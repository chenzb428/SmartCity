package com.chen.smartcity.model;

public class NewCommentParams {

    public NewCommentParams(int newsId, String content) {
        this.newsId = newsId;
        this.content = content;
    }

    private int newsId;
    private String content;

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
}
