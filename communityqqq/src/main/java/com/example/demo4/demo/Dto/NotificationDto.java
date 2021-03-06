package com.example.demo4.demo.Dto;

import com.example.demo4.demo.entity.User;
import lombok.Data;

@Data
public class NotificationDto {

    private int id;
   // private  int  notifier; //通知的人
  //  private int receiver;// 接受通知的人
  //  private int outerid;// 有可能是回复 点赞 问题id 回复id 点赞id
    //private int type;
    private  Long gmt_create;
    private  int  notifier;
    private  int status; // 0 是一读 1 是未读
    private String notifier_name;//通知的人
    private  String outer_title; // 通知的标题
    private  String typeName;// 是回复了问题 还是回复了评论
    private  int type;
    private int outerid;
}
