package com.sen.onlineexam.utils.response;

public enum ResultCode {
    SUCCESS(200, "请求成功！"),
    REDIRECT(301, "重定向！"),
    BAD_REQUEST(400, "调用接口异常!"),
    NOT_AUTHORIZED(401, "未经授权!"),
    NOT_FOUND(404, "资源未找到！"),
    SERVER_ERROR(500, "服务器内部错误！"),
    USER_ACCOUNT_ERROR(601,"账号或密码错误"),
    USER_HAS_EXIST(602,"账号已存在"),
    NOT_LOGIN(603,"用户未登录"),
    PHONE_ERROR(604,"发送失败，请稍后再试"),
    CODE_ERROR(605,"验证码输入有误"),
    ERROR(701,"操作失败！");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 根据code获取状态码及信息
     *
     * @param code 状态码
     * @return 返回状态码类
     */
    public static ResultCode getMessage(int code) {
        for (ResultCode value : values()) {
            if (code == value.code) {
                return value;
            }
        }
        return SUCCESS;
    }
}
