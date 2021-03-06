package com.example.demo4.demo.Service;


import com.example.demo4.demo.Dto.NotificationDto;
import com.example.demo4.demo.Dto.QuestionDto;
import com.example.demo4.demo.Mapper.NotificationMapper;
import com.example.demo4.demo.Mapper.UserMapper;
import com.example.demo4.demo.entity.Notification;
import com.example.demo4.demo.entity.Question;
import com.example.demo4.demo.entity.User;
import com.example.demo4.demo.enums.NotificationEnum;
import com.example.demo4.demo.enums.NotificationStatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationMapper notificationMapper;

    @Autowired
    UserMapper userMapper;
    public List<NotificationDto> list(int id) {
        List<Notification> notifications=notificationMapper.selectByreceiver(id);
        List<NotificationDto> notificationDtos=new ArrayList<>();
        if(notifications.size()==0){
            return new ArrayList<>();
        }else{

            for(Notification notification:notifications)
            {

               NotificationDto notificationDto=new NotificationDto();
               BeanUtils.copyProperties(notification,notificationDto);
                notificationDto.setTypeName(NotificationEnum.nameofType(notification.getType()));// 根据 notification的type 为 1 就是评论  2 就是问题

               notificationDtos.add(notificationDto);
            }
        }
        return notificationDtos;
    }

    public int unreadcount(int id) {
       int unreadCount= notificationMapper.selectCountreceiver(id);
       //  notificationMapper.selectCountreceiver(id,status); 挡状态为0 且id 真确的是
       return unreadCount;
    }

    public NotificationDto read(int id, User user) {
        Notification notification= notificationMapper.selectByid(id);// 可以做异常处理
        // 修改为已读
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updatestatus(notification);
        NotificationDto notificationDto=new NotificationDto();
        BeanUtils.copyProperties(notification,notificationDto);
        notificationDto.setTypeName(NotificationEnum.nameofType(notification.getType()));
        return notificationDto;

    }
}
