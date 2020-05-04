package com.luojianhua.controller;

import com.luojianhua.repository.AdminRepository;
import com.luojianhua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;

    //由于登陆界面只有一个，单选框区别，利用多态特性

    @GetMapping("/login/{username}/{password}/{type}")
    public Object login(@PathVariable("username") String username, @PathVariable("password") String password,
                        @PathVariable("type") String type) {
        Object object=null;

        switch (type) {
            case "user":
                //普通用户登陆
                object=userRepository.login(username,password);

                break;
            case "admin":
                //管理员登陆
                object=adminRepository.login(username,password);
                break;

        }
        return object;

    }

}
