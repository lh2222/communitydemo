package com.example.demo4.demo.Dto;

//相当于实体类  github中几个参数 https://docs.github.com/en/developers/apps/authorizing-oauth-apps 里可以看
public class AccessTokenDto {
   private String client_id ;
   private String redirect_uri;
   private String  client_secret;
   private String  state;
   private String  code;

    public String getClient_id() {
        return client_id;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String getState() {
        return state;
    }

    public String getCode() {
        return code;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
