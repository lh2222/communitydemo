package com.example.demo4.demo.Exception;

import com.example.demo4.demo.entity.Comment;

public enum CustomizedErrorCodeenum implements CustomizedErrorCode{
    QUESTION_NOT_FOUND(2001,"你找到的问题不存在，要不要换个试试"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何评论"),
    NO_LOGIN(2003,"当前操作需要登录"),
    SYS_ERROR(2004, "服务冒烟了，要不然你稍后再试试！！！"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在了，要不要换个试试？"),
    CONTENT_IS_EMPTY(2007, "输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008, "兄弟你这是读别人的信息呢？"),
    NOTIFICATION_NOT_FOUND(2009, "消息莫非是不翼而飞了？"),
    FILE_UPLOAD_FAIL(2010, "图片上传失败"),
    INVALID_INPUT(2011, "非法输入"),
    INVALID_OPERATION(2012, "兄弟，是不是走错房间了？"),
    ;
    private String message;
    private Integer code;

    CustomizedErrorCodeenum(String message){
        this.message=message;
    }

    CustomizedErrorCodeenum(Integer code,String message){
        this.message=message;
        this.code=code;
    }
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return null;
    }
}
