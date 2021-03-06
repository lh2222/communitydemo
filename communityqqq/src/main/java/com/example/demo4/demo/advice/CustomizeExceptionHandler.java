package com.example.demo4.demo.advice;


import com.alibaba.fastjson.JSON;
import com.example.demo4.demo.Dto.ResultDto;
import com.example.demo4.demo.Exception.CustomizeException;
import com.example.demo4.demo.Exception.CustomizedErrorCodeenum;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDto resultDto;
            // 返回 JSON
            if (e instanceof CustomizeException) {
                resultDto = ResultDto.errof((CustomizeException) e);
            } else {
               // log.error("handle error", e);
                resultDto = ResultDto.errorof1(CustomizedErrorCodeenum.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDto));
                writer.close();
            } catch (IOException ioe) {
            }
            return null;
        } else {
            // 错误页面跳转
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
               // log.error("handle error", e);
                model.addAttribute("message",CustomizedErrorCodeenum.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
        }



