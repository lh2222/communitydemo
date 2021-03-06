package com.example.demo4.demo.Dto;

import com.example.demo4.demo.entity.User;
import lombok.Data;

@Data
public class QuestionDto {
    private int id;
    private String title;
    private String description;
    private String tag;
    private Long gmt_create;
    private Long gmt_modified;
    private int creator;
    private int view_count;
    private int comment_count;
    private int like_count;
    private User user;
}
