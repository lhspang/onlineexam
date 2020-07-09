package com.sen.onlineexam.pojo;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private User user;
    private String phone;
    private String email;
    private String qq;

    public UserInfo() {
    }

    public UserInfo(User user, String phone, String email, String qq) {
        this.user = user;
        this.phone = phone;
        this.email = email;
        this.qq = qq;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user=" + user +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                '}';
    }
}
