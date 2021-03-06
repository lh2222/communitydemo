package com.example.demo4.demo.Dto;


import com.example.demo4.demo.Exception.CustomizeException;
import com.example.demo4.demo.Exception.CustomizedErrorCodeenum;
import lombok.Data;

@Data
public class ResultDto<T>{
    private int code;
    private String message;
    private T data;

    public static ResultDto errorOf(Integer code,String message){
        ResultDto resultDto=new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }
public static  ResultDto errorof1(CustomizedErrorCodeenum errCode){
        return errorOf(errCode.getCode(),errCode.getMessage());
}
public static ResultDto ok(){
        ResultDto resultDto=new ResultDto();

        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
}
public static ResultDto errof(CustomizeException e){
        return errorOf(e.getCode(),e.getMessage());
}
public  static <T> ResultDto okof(T t){
       ResultDto resultDto=new ResultDto();
       resultDto.setCode(200);
       resultDto.setMessage("请求成功");
       resultDto.setData(t);
       return resultDto;


}
}
