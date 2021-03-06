package com.example.demo4.demo.Service;


import com.example.demo4.demo.Dto.CommentDtouser;
import com.example.demo4.demo.Dto.QuestionDto;
import com.example.demo4.demo.Exception.CustomizeException;
import com.example.demo4.demo.Exception.CustomizedErrorCodeenum;
import com.example.demo4.demo.Mapper.CommentMapper;
import com.example.demo4.demo.Mapper.NotificationMapper;
import com.example.demo4.demo.Mapper.QuestionMapper;
import com.example.demo4.demo.Mapper.UserMapper;
import com.example.demo4.demo.entity.Comment;
import com.example.demo4.demo.entity.Notification;
import com.example.demo4.demo.entity.Question;
import com.example.demo4.demo.entity.User;
import com.example.demo4.demo.enums.CommentTypeEnum;
import com.example.demo4.demo.enums.NotificationEnum;
import com.example.demo4.demo.enums.NotificationStatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper  questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    @Transactional // 事务 要么同时成功 要么同时失败
    public void insert(Comment comment) {
        // 处理异常
        if(comment.getParent_id()==null||comment.getParent_id()==0){
            throw new CustomizeException(CustomizedErrorCodeenum.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizedErrorCodeenum.TYPE_PARAM_WRONG);
        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论   找唯一的评论
            Comment dbcomment=commentMapper.selectByid(comment.getParent_id());
            if(dbcomment==null){
                throw new CustomizeException(CustomizedErrorCodeenum.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);

            // 增加评论数还没写
            //*
            //通知
            // 可以加判断 发送通知的人和接受通知的人一样 则不创建
            Question question=questionMapper.getById(dbcomment.getParent_id());//还是问题的title
            User user=userMapper.findById(comment.getCommentator());
            Notification notification=new Notification();
            notification.setGmt_create(System.currentTimeMillis());
            notification.setType(NotificationEnum.REPLY_COMMENT.getType()); //2
            notification.setOuterid(dbcomment.getParent_id());//对所问题的id 无论是什么都是问题id
            notification.setNotifier(comment.getCommentator());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notification.setReceiver(dbcomment.getCommentator());
            notification.setNotifier_name(user.getName());
            notification.setOuter_title(question.getTitle());


            notificationMapper.insert(notification);


        }else{
            // 回复问题
            // 这里注意一下 问题   有问题
            Question question=questionMapper.getById(comment.getParent_id());
            if(question==null)
            {  // 找的问题为空
                throw  new CustomizeException(CustomizedErrorCodeenum.QUESTION_NOT_FOUND);

            }
            commentMapper.insert(comment);
            // 增加评论数
            question.setComment_count(question.getComment_count()+1);
            questionMapper.inccomment(question);
            User user=userMapper.findById(comment.getCommentator());
            Notification notification=new Notification();
            notification.setGmt_create(System.currentTimeMillis());
            notification.setType(NotificationEnum.REPLY_QUESTION.getType()); //2
            notification.setOuterid(comment.getParent_id());//对所评论的id
            notification.setNotifier(comment.getCommentator());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notification.setReceiver(question.getCreator());
            notification.setOuter_title(question.getTitle());
            notification.setNotifier_name(user.getName());

            notificationMapper.insert(notification);
        }
    }
// 有问题
    public List<CommentDtouser> listByquestionId(int id) {
        //要根据 type 来区分是 评论 还是回复评论
        // 返回问题的所有回复
        List<Comment> commentList=commentMapper.selectCommentList(id);
        List<CommentDtouser> DtoList=new ArrayList<>();
        for(Comment comment:commentList)
        {
            User user= userMapper.findById(comment.getCommentator());

            CommentDtouser commentDtouser=new CommentDtouser();

            BeanUtils.copyProperties(comment,commentDtouser);
            commentDtouser.setUser(user );
            DtoList.add(commentDtouser);
        }
        return DtoList;

    }

    public List<CommentDtouser> listBycommentId(int id) {
        List<Comment> commentList=commentMapper.selectCommentsList(id);// type=2
        List<CommentDtouser> DtoList=new ArrayList<>();
        for(Comment comment:commentList)
        {
            User user= userMapper.findById(comment.getCommentator());

            CommentDtouser commentDtouser=new CommentDtouser();

            BeanUtils.copyProperties(comment,commentDtouser);
            commentDtouser.setUser(user );
            DtoList.add(commentDtouser);
        }
        return DtoList;
    }
}
