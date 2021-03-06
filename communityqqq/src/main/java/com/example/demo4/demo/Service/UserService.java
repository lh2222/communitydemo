package com.example.demo4.demo.Service;


import com.example.demo4.demo.Mapper.UserMapper;
import com.example.demo4.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
       User dbuser= userMapper.findByAccountId(user.getAccount_id());
       if(dbuser==null)
       {  user.setGmt_create(System.currentTimeMillis());
           user.setGmt_modified(user.getGmt_create());
           //插入
           userMapper.insert(user);
       }else{
           dbuser.setGmt_modified(System.currentTimeMillis());
           dbuser.setAvatar_url(user.getAvatar_url());
           dbuser.setName(user.getName());
           dbuser.setToken(user.getToken());
           userMapper.update(dbuser);
           // 更新
       }
    }
}
