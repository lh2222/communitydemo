package com.example.demo4.demo.enums;

public enum NotificationEnum {


    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论");

    private  int type;
    private String  name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationEnum(int type, String name){
        this.name=name;
        this.type=type;
    }

    public static String nameofType(int type){
        for(NotificationEnum notificationEnum:NotificationEnum.values()){
            if(notificationEnum.getType()==type){
                return notificationEnum.getName();
            }
        }
        return "";
    }
}
