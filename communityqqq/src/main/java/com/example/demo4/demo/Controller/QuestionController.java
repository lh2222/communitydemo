package com.example.demo4.demo.Controller;


import com.example.demo4.demo.Dto.CommentDtouser;
import com.example.demo4.demo.Dto.QuestionDto;
import com.example.demo4.demo.Service.CommentService;
import com.example.demo4.demo.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id") int id, Model model){
        QuestionDto questionDto= questionService.getById(id);
        // 增加评论数
        questionService.incView(id);
        // 展示评论
       List<CommentDtouser> commentDtouserList=commentService.listByquestionId(id);
       // 相关问题 根据这个问题的tag 匹配与之相关的question
        List<QuestionDto> relatequestionDto=questionService.selectRelate(questionDto);
       model.addAttribute("comments",commentDtouserList);
        model.addAttribute("question",questionDto);
        model.addAttribute("relatedQuestion",relatequestionDto);
        return "question";
    }
}
