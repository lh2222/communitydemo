package com.example.demo4.demo.Mapper;


import com.example.demo4.demo.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment(parent_id,type,commentator,gmt_create,gmt_modified,content,like_count) values(#{parent_id},#{type},#{commentator},#{gmt_create},#{gmt_modified},#{content},#{like_count})")
    void insert(Comment comment);

     // 和 type 有关
    @Select("select * from comment where parent_id=#{parent_id} and type=1 ")
    List<Comment> selectCommentList(int id); // 对问题的回复

    @Select("select * from comment where parent_id=#{parent_id} and type=2")
    List<Comment> selectCommentsList(int id);// 对评论的回复


    @Select("select * from comment where id=#{id}")
    Comment selectByid(Integer id);
}
