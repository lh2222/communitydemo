package com.example.demo4.demo.Controller;


import com.example.demo4.demo.Dto.AccessTokenDto;
import com.example.demo4.demo.Dto.GithubUser;
import com.example.demo4.demo.Mapper.UserMapper;
import com.example.demo4.demo.Service.UserService;
import com.example.demo4.demo.entity.User;
import com.example.demo4.demo.provider.GithubProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@Controller
public class Authorize {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    // 获取用户的code
    @GetMapping("/callback") //github授权后返回的 url http://localhost:8080/callback?code=72561516ea964829154b&state=1
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDto accessTokenDto=new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id("34822b8342f78338da41");
        accessTokenDto.setClient_secret("5ad5433c221f844be6bab015cb023377d7a4ccd5");
       String accessToken= githubProvider.getAccessToken(accessTokenDto);
      GithubUser githubUser= githubProvider.getUser(accessToken);
      if(githubUser!=null){
          User user=new User();
          user.setAccount_id(String.valueOf(githubUser.getId()));
          String  token=UUID.randomUUID().toString();
          user.setToken(token);
          user.setName(githubUser.getName());
         user.setGmt_create(System.currentTimeMillis());
        //  user.setGmt_modified(user.getGmt_create());
         // user.setAvatar_url(githubUser.getAvatar_url());
         userService.createOrUpdate(user);
       //  userMapper.insert(user);  //这个user和session里user 不一样
          // d登录成功 写cookie和session
          response.addCookie(new Cookie("token1",token));

         // 以前的版本  request.getSession().setAttribute("user",githubUser);
          return "redirect:/index";
      }else{
          // 登录失败 加日志
          log.error(" callback方法 登录错误",githubUser);
          return "redirect:/index";
      }


    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token1",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/index";
    }

}
