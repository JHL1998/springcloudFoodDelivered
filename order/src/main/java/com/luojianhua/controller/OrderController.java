package com.luojianhua.controller;

import com.luojianhua.entity.Order;
import com.luojianhua.repository.OrderRepository;
import com.luojianhua.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/save")
    public void save(@RequestBody Order order){
        order.setDate(new Date());
        orderRepository.save(order);
    }

    @GetMapping("/findAllById/{index}/{limit}/{uid}")
    public OrderVO findAllById(@PathVariable("index") int index, @PathVariable("limit") int limit, @PathVariable("uid") int uid){
        OrderVO orderVO=new OrderVO(0,"",orderRepository.countById(uid),orderRepository.findAllById(index,limit,uid));
           return orderVO;
    }


    @GetMapping("/countById/{uid}")
    public int countById(@PathVariable("uid") int uid){
          return orderRepository.countById(uid);
    }

    @GetMapping("findByState/{index}/{limit}")
    public OrderVO findByState(@PathVariable("index") int index,
                                   @PathVariable("limit") int limit){
        OrderVO orderVO=new OrderVO();
        orderVO.setCode(0);
        orderVO.setCount(orderRepository.count());
        orderVO.setMsg("");
        orderVO.setData(orderRepository.findByState(index,limit));
        return orderVO;
    }

    @GetMapping("updateState/{id}")
    public void updateState(@PathVariable("id") long id){
        orderRepository.updateState(id);

    }


}
