package com.example.demo4.demo.Controller;
import com.example.demo4.demo.Dto.QuestionDto;
import com.example.demo4.demo.Mapper.QuestionMapper;
import com.example.demo4.demo.Mapper.UserMapper;
import com.example.demo4.demo.Service.QuestionService;
import com.example.demo4.demo.entity.Question;
import com.example.demo4.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name="name") String name, Model model){
        model.addAttribute("name",name);
        return "hello";

    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/index")
    public String index(HttpServletRequest request,
                        HttpServletResponse response,
                        Model model,
                        @RequestParam(name="search",required = false) String search){

             List<QuestionDto> lists=questionService.list();
             model.addAttribute("questions",lists);
             // 有search时  questionServiceserach.list(serach)
        return "index";

    }
}
