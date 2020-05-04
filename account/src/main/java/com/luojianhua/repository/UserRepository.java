package com.luojianhua.repository;

import com.luojianhua.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
     User login(String username, String password);
}
