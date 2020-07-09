package com.sen.onlineexam.utils;

public enum IdentityType {
    SYSTEM(0,"系统用户","system"),
    PHONE(1,"手机号","phone"),
    EMAIL(2,"邮箱","email"),
    QQ(3,"qq号","qq");

    private int code;
    private String desc;
    private String name;

    IdentityType(int code, String desc, String name) {
        this.code = code;
        this.desc = desc;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }
}
