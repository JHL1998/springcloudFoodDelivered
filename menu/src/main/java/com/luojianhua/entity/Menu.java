package com.luojianhua.entity;

import lombok.Data;

@Data
public class Menu {

    private long id;
    private String name;
    private double price;
    private String flavor;
    //做两张表关联查询
    private Type type;

}
