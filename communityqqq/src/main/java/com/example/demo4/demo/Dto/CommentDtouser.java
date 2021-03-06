package com.example.demo4.demo.Dto;

import com.example.demo4.demo.entity.User;
import lombok.Data;

@Data
public class CommentDtouser {
    private Integer id;
    private Integer parent_id;
    private Integer type;
    private Integer commentator;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer like_count;
    private String content;
    private User user;
}
