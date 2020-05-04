package com.luojianhua.repository;

import com.luojianhua.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository {
     Admin login(String username, String password);
}
