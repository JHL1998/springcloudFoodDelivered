package com.luojianhua.repository;

import com.luojianhua.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {
    void save(Order order);
    List<Order> findAllById(int index, int limit, int uid);
    int countById(int uid);
    //找出未派送的订单号
    List<Order> findByState(int index,int limit);
    //修改订单状态
    void updateState(long id);

    //统计未派送的数量
    public int count();
}
