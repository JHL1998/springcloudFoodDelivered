package com.luojianhua.controller;

import com.luojianhua.entity.Menu;
import com.luojianhua.entity.Type;
import com.luojianhua.repository.MenuRepository;
import com.luojianhua.repository.TypeRepository;
import com.luojianhua.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private TypeRepository typeRepository;


    @GetMapping("/findAll/{index}/{limit}")
   public MenuVO findAll(@PathVariable("index") int index,@PathVariable("limit") int limit){
        return new MenuVO(0,"",menuRepository.count(),menuRepository.findAll(index,limit));

   }


   @DeleteMapping("/deleteById/{id}")
   public void deleteById(@PathVariable("id") long id){
        menuRepository.deleteById(id);
   }


   @GetMapping("/findTypes")
   public List<Type> findTypes(){
      return  typeRepository.findAll();
   }

   @PostMapping("/save")
   public void save(@RequestBody Menu menu){
      menuRepository.save(menu);
   }

   @GetMapping("/findById/{id}")
   public Menu findById(@PathVariable("id") int id){
      return menuRepository.findById(id);
   }

   @PutMapping("/update")
   public void update(@RequestBody Menu menu){
         menuRepository.update(menu);
   }
}
