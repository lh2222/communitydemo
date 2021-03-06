package com.example.demo4.demo.Controller;

import com.example.demo4.demo.Mapper.QuestionMapper;
import com.example.demo4.demo.Mapper.UserMapper;
import com.example.demo4.demo.Service.QuestionService;
import com.example.demo4.demo.cache.Tagcache;
import com.example.demo4.demo.entity.Question;
import com.example.demo4.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class publicController {
    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags", Tagcache.get());
        return "publish";
    }
@GetMapping("/publish/{id}")
public String edit(@PathVariable(name="id") int id,Model model){
       Question question= questionMapper.getById(id);

    model.addAttribute("title",question.getTitle());// 为了回显到页面
    model.addAttribute("description",question.getDescription());
    model.addAttribute("tag",question.getTag());
    model.addAttribute("tags", Tagcache.get());
    // model.addAttribute("id",question.getId());
        return "publish";
}
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @PostMapping("/publish")
    public String dopublish(

            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model

    ){
        model.addAttribute("title",title);// 为了回显到页面
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", Tagcache.get());

        if(title==null ||title==" "){
            model.addAttribute("error","问题不能为空");
            return "publish";
        }
        if(description==null ||description==" "){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(tag==null ||tag==" "){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
       // String invalid=Tagcache.filterisValid(tag);
      //  if(StringUtils.isNotBlank(invalid)){
       //     model.addAttribute("error","输入非法标签"+invalid);
        //    return "publish";
       // }

        User user= (User)request.getSession().getAttribute("user");

        if(user == null)
        {
            model.addAttribute("error","用户未登陆");
            return "publish";
        }
        Question question=new Question();
        question.setTitle(title);
        question.setTag(tag);
        question.setComment_count(0);
        question.setView_count(0);
        question.setLike_count(0);
        question.setDescription(description);
        question.setCreator(user.getId());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());
       // questionService.createOrUpdate(question);
      questionMapper.create(question);
        return "redirect:/index";
    }


}
