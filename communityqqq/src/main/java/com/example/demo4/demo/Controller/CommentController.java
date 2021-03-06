package com.example.demo4.demo.Controller;


import com.example.demo4.demo.Dto.CommentDto;
import com.example.demo4.demo.Dto.CommentDtouser;
import com.example.demo4.demo.Dto.ResultDto;
import com.example.demo4.demo.Exception.CustomizedErrorCodeenum;
import com.example.demo4.demo.Mapper.CommentMapper;
import com.example.demo4.demo.Service.CommentService;
import com.example.demo4.demo.entity.Comment;
import com.example.demo4.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value="/comment",method= RequestMethod.POST)
    public  Object post(@RequestBody CommentDto commentDto, HttpServletRequest request){
         User user=(User)request.getSession().getAttribute("user");
         if(user == null){
             // return ResultDto.errorOf(2002,"未登录请先登录 不能评论");
             return ResultDto.errorof1(CustomizedErrorCodeenum.NO_LOGIN);
         }
        Comment comment=new Comment();
        comment.setContent(commentDto.getContent());
        comment.setParent_id(commentDto.getParent_id());// 1为对问题的回复 2.对评论   回复按钮 ajax
        comment.setType(commentDto.getType());
        comment.setGmt_create(System.currentTimeMillis());
        comment.setGmt_modified(comment.getGmt_create());
       //  comment.setCommentator(1);// 评论人
        comment.setCommentator(user.getId());
        comment.setLike_count(0);
       //  comment.setLike_count(0L); Long
        // commentMapper.insert(comment);
        commentService.insert(comment);// 通过点击 发送 为 1对问题回复  通过点击评论 是2 是对评论 用ajax实现
// 评论时增加通知功能
        return ResultDto.ok();

    }

   // 通过ajax请求   返回所有2级评论数据     提交
   @ResponseBody
   @RequestMapping(value="/comment/{id}",method= RequestMethod.GET)
   public  ResultDto<List> comments(@PathVariable(name="id") int id){
        //  最外层评论是获取 question id
        // 里层评论是获取 comment id  type=2
       // List<CommentDtouser> commentDtouserList=commentService.listBycommentId(id,type);
       List<CommentDtouser> commentDtouserList=commentService.listBycommentId(id);
return  ResultDto.okof(commentDtouserList);
    }
}
