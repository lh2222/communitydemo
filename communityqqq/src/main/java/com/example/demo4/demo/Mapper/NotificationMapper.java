package com.example.demo4.demo.Mapper;

import com.example.demo4.demo.entity.Notification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NotificationMapper {


    @Insert("insert into notification (notifier,receiver,outerid,type,status,gmt_create,notifier_name,outer_title) values (#{notifier},#{receiver},#{outerid},#{type},#{status},#{gmt_create},#{notifier_name},#{outer_title})")
    void insert(Notification notification);

    @Select("select * from notification where receiver=#{id}")
    List<Notification> selectByreceiver(int id);
    @Select("select count(*) from notification where receiver=#{id} and status=0")
    int selectCountreceiver(int id);
    @Select("select * from notification where id=#{id}")
    Notification selectByid(int id);

    @Update("update notification set status=#{status} where id=#{id} ")
    void updatestatus(Notification notification);
}
