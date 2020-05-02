package com.luojianhua.repository;

import com.luojianhua.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuRepository {
    List<Menu> findAll(int index,int limit);
    int count();
    Menu findById(long id);
    void save(Menu menu);
    void update(Menu menu);
    void deleteById(long id);

}
