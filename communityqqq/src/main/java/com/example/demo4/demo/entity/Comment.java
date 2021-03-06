package com.example.demo4.demo.entity;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private Integer parent_id;
    private Integer type;
    private Integer commentator;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer like_count;
    private String content;
    private Integer comment_count;

}
