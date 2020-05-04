package com.luojianhua.feign;

import com.luojianhua.entity.User;
import com.luojianhua.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="user")
public interface UserFeign {

    @GetMapping("/user/findAll/{index}/{limit}")
    UserVO findAll(@PathVariable("index")int index, @PathVariable("limit") int limit);

    @GetMapping("/user/findById/{id}")
     User findById(@PathVariable("id") long id);

    @PostMapping( "/user/save")
    void save( User user);



    @DeleteMapping("/user/deleteById/{id}")
    void deleteById(@PathVariable("id") long id);
}
