package com.sen.onlineexam.utils;

public enum LoginType {
    PASSWORD("password"),
    PHONE("phone");

    private String name;

    LoginType() {
    }

    LoginType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
