package com.example.demo4.demo.Dto;

import lombok.Data;

import java.util.List;

@Data
public class TagDto {
    private  String categoryname;
    private List<String> tags;
}
