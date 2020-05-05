package com.luojianhua.controller;

import com.luojianhua.entity.Menu;
import com.luojianhua.entity.Order;
import com.luojianhua.entity.User;
import com.luojianhua.feign.OrderFeign;
import com.luojianhua.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderFeign orderFeign;

    @GetMapping("/save/{mid}")
    public String save(@PathVariable("mid")int mid, HttpSession session){
         User user=(User)session.getAttribute("user");
         Order order=new Order();
         order.setUser(user);

         Menu menu=new Menu();
         menu.setId(mid);
         order.setMenu(menu);
         orderFeign.save(order);
         return "order";

    }

    @GetMapping("/findAllById")
    @ResponseBody
    public OrderVO findAllById(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session){
        User user=(User)session.getAttribute("user");
        return orderFeign.findById((page-1)*limit,limit,(int)user.getId());
    }

    @GetMapping("/findByState")
    @ResponseBody
    public OrderVO findByState(@RequestParam("page") int page,
                               @RequestParam("limit") int limit){

        return orderFeign.findByState((page-1)*limit,limit);
    }

    @GetMapping("updateState/{id}")
    public String updateState(@PathVariable("id") long id){
        orderFeign.updateState(id);
        return "redirect:/menu/redirect/order_handler";
    }
}
