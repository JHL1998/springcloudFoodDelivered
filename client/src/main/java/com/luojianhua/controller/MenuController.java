package com.luojianhua.controller;

import com.luojianhua.entity.Menu;
import com.luojianhua.feign.MenuFeign;
import com.luojianhua.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuFeign menuFeign;

    @GetMapping("/findAll")
    @ResponseBody
    public MenuVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
                int index=(page-1)*limit;
        return  menuFeign.findAll(index,limit);
    }


    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
           menuFeign.deleteById(id);
           return "redirect:/menu/redirect/index";
    }


    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location){
           return location;
    }

    /**
     * 方便添加菜品
     */
    @GetMapping("/findTypes")
    public ModelAndView findTypes(){
         ModelAndView modelAndView=new ModelAndView();
         modelAndView.setViewName("menu_add");
         modelAndView.addObject("list",menuFeign.findTypes());
         return modelAndView;

    }

    @PostMapping("/save")
    public String save(Menu menu){
        menuFeign.save(menu);
        return "redirect:/menu/redirect/menu_manage";

    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") long id){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("menu_update");
        modelAndView.addObject("menu",menuFeign.findById(id));
        modelAndView.addObject("list",menuFeign.findTypes());
        return modelAndView;

    }


    @PostMapping("/update")
    public String update( Menu menu){
         menuFeign.update(menu);
         return "redirect:/menu/redirect/menu_manage";
    }
}

