package com.example.demo4.demo.provider;


import com.alibaba.fastjson.JSON;
import com.example.demo4.demo.Dto.AccessTokenDto;
import com.example.demo4.demo.Dto.GithubUser;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.*;
import okhttp3.Response;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class GithubProvider {
    // 用 okhttp 模拟浏览器发送post请求
    public String getAccessToken(AccessTokenDto accessTokenDto){
         MediaType JSON1 = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON1,JSON.toJSONString(accessTokenDto));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                 String string=response.body().string();
                // access_token=59dbb3bf289eefb8b88168a3dc3ef10f579aa1da&scope=user&token_type=bearer
                  String token=string.split("&")[0].split("=")[1];
                  System.out.println(token);
                 return token;
            }catch (IOException e){
                e.printStackTrace();

            }
        return null;
    }
    public GithubUser getUser(String accessToken){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().
                url("https://api.github.com/user").
                header("Authorization","token " +accessToken).
                build();
        try{
            Response response=client.newCall(request).execute();
            String string=response.body().string();
            GithubUser githubUser= JSON.parseObject(string,GithubUser.class);
            return githubUser;
        }catch (IOException e){

        }
        return null;
    }
}
