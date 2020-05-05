package com.luojianhua.repository;

import com.luojianhua.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    List<User> findAll(int index, int limit);
    public User findById(long id);
    int count();
    void save(User user);
    void update(User user);
    void deleteById(long id);

}
