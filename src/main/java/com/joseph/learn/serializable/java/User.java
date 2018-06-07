package com.joseph.learn.serializable.java;

import java.io.Serializable;

/**
 * Created by Joseph on 2016/10/28.
 */
public class User implements Serializable{
    private static final long serialVersionUID = 5149128310592716591L;

    private int age;
    private String username;
    private String address;

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }
}
