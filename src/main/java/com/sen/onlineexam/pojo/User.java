package com.sen.onlineexam.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 
 */
public class User implements Serializable {
    private Integer userId;

    private String userName;

    private String sex;

    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    private Role role;

    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(Integer userId, String userName, String sex, Integer age, Date lastLoginTime,Role role) {
        this.userId = userId;
        this.userName = userName;
        this.sex = sex;
        this.age = age;
        this.lastLoginTime = lastLoginTime;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", role=" + role +
                '}';
    }
}