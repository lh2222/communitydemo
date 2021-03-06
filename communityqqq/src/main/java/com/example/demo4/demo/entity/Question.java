package com.example.demo4.demo.entity;

public class Question {
    private int id;
    private String title;
    private String description;
    private String tag;
    private Long gmt_create;
    private Long gmt_modified;
    private int creator;// 创建者
    private int view_count;
    private int comment_count;
    private int like_count;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTag() {
        return tag;
    }

    public Long getGmt_create() {
        return gmt_create;
    }

    public Long getGmt_modified() {
        return gmt_modified;
    }

    public int getCreator() {
        return creator;
    }

    public int getView_count() {
        return view_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setGmt_create(Long gmt_create) {
        this.gmt_create = gmt_create;
    }

    public void setGmt_modified(Long gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }
}
