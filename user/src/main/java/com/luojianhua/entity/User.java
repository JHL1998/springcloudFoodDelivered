package com.luojianhua.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private long id;
    private String username;
    private String password;
    private String nickname;
    private  String gender;
    @JsonFormat
    private String telephone;
    private Date registerdate;
    private String address;
}
