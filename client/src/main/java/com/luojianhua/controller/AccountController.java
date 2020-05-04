package com.luojianhua.controller;

import com.luojianhua.entity.Admin;
import com.luojianhua.entity.User;
import com.luojianhua.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountFeign accountFeign;

    @PostMapping("/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        @RequestParam("type")String type,
                        HttpSession session){
           Object object=accountFeign.login(username,password,type);
           //这里的object 变成了LinkedHashMap,先强转
        LinkedHashMap<String,Object> map=(LinkedHashMap)object;
           String res=null;
           if(object==null){
               //登录失败
               res="login";
           }else{
               switch (type){
                   case "user":
                       User user=new User();
                       //id与用户相关联，设计业务
                      String idStr=String.valueOf(map.get("id"));
                      long id=Long.parseLong(idStr);
                      //这里在前端展示只需要nickName
                      String nickname=(String)map.get("nickname");
                      user.setId(id);
                      user.setNickname(nickname);

                       session.setAttribute("user",user);
                       //这里拿到session不能用重定向，只能转发
                       res="index";
                       break;
                   case "admin":
                       session.setAttribute("admin",(Admin)object);
                       res="";
                       break;
               }
           }


        return res;

    }



    @GetMapping("/logout")
    public String logout(HttpSession session){
        //销毁session
        session.invalidate();
        return "redirect:/login.html";
    }
}
