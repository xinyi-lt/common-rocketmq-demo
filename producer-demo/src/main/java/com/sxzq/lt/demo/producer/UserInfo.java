package com.sxzq.lt.demo.producer;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lt on 2018/4/24.
 */
public class UserInfo implements Serializable{

    private static final long serialVersionUID = 5333042105146528262L;
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;



    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
