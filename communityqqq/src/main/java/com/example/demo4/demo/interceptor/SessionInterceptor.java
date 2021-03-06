package com.example.demo4.demo.interceptor;

import com.example.demo4.demo.Mapper.UserMapper;
import com.example.demo4.demo.Service.NotificationService;
import com.example.demo4.demo.entity.Notification;
import com.example.demo4.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// 不写@Service userMapper 不能用
@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationService notificationService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies=request.getCookies();
        if(cookies!=null && cookies.length!=0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token1"))
                {
                    String token=cookie.getValue();
                    User user=userMapper.findBytoken(token);
                    if(user !=null){
                        request.getSession().setAttribute("user",user);
                        int unreadCount=notificationService.unreadcount(user.getId());// 通知一样
                        request.getSession().setAttribute("unreadCount",unreadCount);
                    }
                    break;
                }

            }}
        return true;
    }
}
