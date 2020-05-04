package com.luojianhua.repository;

import com.luojianhua.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {
    void save(Order order);
    List<Order> findAllById(int index, int limit, int uid);
    int countById(int uid);
}
