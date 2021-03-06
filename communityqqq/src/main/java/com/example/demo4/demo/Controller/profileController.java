package com.example.demo4.demo.Controller;


import com.example.demo4.demo.Dto.NotificationDto;
import com.example.demo4.demo.Dto.QuestionDto;
import com.example.demo4.demo.Mapper.UserMapper;
import com.example.demo4.demo.Service.NotificationService;
import com.example.demo4.demo.Service.QuestionService;
import com.example.demo4.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class profileController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name="action") String action, Model model, HttpServletRequest request){



      User user= (User)request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/index";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            List<QuestionDto> lists=questionService.list(user.getId());
            model.addAttribute("questions",lists);
        }else if("replies".equals(action)){
           List< NotificationDto> notificationDto=notificationService.list(user.getId());
           int unreadCount=notificationService.unreadcount(user.getId());
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的回复");
            model.addAttribute("pagination",notificationDto);
           //  model.addAttribute("unreadCount",unreadCount);// 在session里放

        }
       //  questionService.list(user.getId(),page size);

        return "profile";
    }
}
