package com.example.demo4.demo.Mapper;


import com.example.demo4.demo.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
    public void insert(User user);
    @Select("select * from user where token=#{token}")
    User findBytoken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") int id);
    @Select("select * from user where account_id=#{account_id}")
    User findByAccountId(@Param("account_id") String account_id);


    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmt_modified},avatar_url=#{avatar_url} where id=#{id} ")
    void update(User user);
}
