package com.luojianhua.feign;

import com.luojianhua.entity.Order;
import com.luojianhua.vo.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value="order")
public interface OrderFeign {

    @PostMapping("/order/save")
    void save( Order order);


    @GetMapping("/order/findAllById/{index}/{limit}/{uid}")
    OrderVO findById(@PathVariable("index") int index, @PathVariable("limit") int limit, @PathVariable("uid") int uid);

}
