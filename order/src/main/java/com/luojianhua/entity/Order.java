package com.luojianhua.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private long id;
    //uid,mid,aid都是外键
    private User user;
    private Menu menu;
    private Admin admin;
    private Date date;
    private int state;

}
