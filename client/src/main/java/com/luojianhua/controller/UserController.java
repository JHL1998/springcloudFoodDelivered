package com.luojianhua.controller;

import com.luojianhua.entity.User;
import com.luojianhua.feign.UserFeign;
import com.luojianhua.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserFeign userFeign;

    @GetMapping("/findAll")
    @ResponseBody
    public UserVO findAll(@RequestParam("page") int page,@RequestParam("limit") int limit){
         return userFeign.findAll((page-1)*limit,limit);
    }

    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location){
        return location;
    }


    @PostMapping("/save")
    public String save( User user){
        user.setRegisterdate(new Date());
        userFeign.save(user);
        return "redirect:/user/redirect/user_manage";

    }
    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") long id){
         return userFeign.findById(id);
    }


    //通过超链接删除，不是deleteMapping
    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        userFeign.deleteById(id);
        return "redirect:/user/redirect/user_manage";
    }


}

