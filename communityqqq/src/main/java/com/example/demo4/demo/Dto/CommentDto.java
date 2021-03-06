package com.example.demo4.demo.Dto;


import lombok.Data;

@Data
public class CommentDto {
    private int parent_id;
    private String content;
    private Integer type;
}
