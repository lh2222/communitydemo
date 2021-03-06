package com.example.demo4.demo.Mapper;


import com.example.demo4.demo.entity.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
   public void create(Question question);

    @Select("select * from question")
    List<Question> list();

    @Select("select * from question where creator=#{userid}")
    List<Question> listbyuserid(@Param("userid") int userid);


    @Select("select * from question where id=#{id}")
    Question getById(@Param("id") int id);
// 更新评论数
    @Update("update question set view_count=#{view_count} where id=#{id} ")
    void update(Question updateQuestion);
    @Update("update question set comment_count=#{comment_count} where id=#{id} ")
    void inccomment(Question question);


    // 相关问题
    @Select("select * from question where  tag regexp #{tag} and id !=#{id}")
    List<Question> relatequestion(Question question);
}
