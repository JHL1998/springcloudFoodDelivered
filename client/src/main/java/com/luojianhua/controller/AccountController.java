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
        String idStr=null;
        long id=0L;
           if(object==null){
               //登录失败
               res="login";
           }else{
               switch (type){
                   case "user":
                       User user=new User();
                       //id与用户相关联，设计业务
                       idStr=String.valueOf(map.get("id"));
                       id=Long.parseLong(idStr);
                      //这里在前端展示只需要nickName
                      String nickname=(String)map.get("nickname");
                      user.setId(id);
                      user.setNickname(nickname);

                       session.setAttribute("user",user);
                       //这里拿到session不能用重定向，只能转发
                       res="index";
                       break;
                   case "admin":
                       Admin admin=new Admin();
                      idStr=String.valueOf(map.get("id"));
                      id=Long.parseLong(idStr);
                      String newUsername=String.valueOf(map.get("username"));
                      admin.setId(id);
                      admin.setUsername(newUsername);
                       session.setAttribute("admin",admin);
                       res="main";
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
