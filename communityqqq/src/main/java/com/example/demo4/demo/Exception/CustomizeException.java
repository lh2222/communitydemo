package com.example.demo4.demo.Exception;
// 自定义异常
public class CustomizeException extends  RuntimeException{

    private String message;
    private  Integer code;
    public CustomizeException(String message){
        this.message=message;
    }
    public CustomizeException(CustomizedErrorCode errorCode){
        this.code=errorCode.getCode();

        this.message=errorCode.getMessage();
    }
    public String getMessage(){
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
