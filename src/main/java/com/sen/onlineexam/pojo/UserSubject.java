package com.sen.onlineexam.pojo;

import java.io.Serializable;

/**
 * @author 
 * 
 */
public class UserSubject implements Serializable {
    private User user;

    private Subject subject;

    private static final long serialVersionUID = 1L;

    public UserSubject() {
    }

    public UserSubject(User user, Subject subject) {
        this.user = user;
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "UserSubject{" +
                "user=" + user +
                ", subject=" + subject +
                '}';
    }
}