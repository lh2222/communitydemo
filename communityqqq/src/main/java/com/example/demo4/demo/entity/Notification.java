package com.example.demo4.demo.entity;


import lombok.Data;

@Data
public class Notification {
    private int id;
    private  int  notifier; //通知的人
    private int receiver;// 接受通知的人
    private int outerid;// 有可能是回复 点赞 问题id 回复id 点赞id
    private int type;
    private  Long gmt_create;
    private  int status; // 0 是一读 1 是未读
    private String notifier_name;// 通知人的名字
    private  String outer_title;// 问题的 title
}
