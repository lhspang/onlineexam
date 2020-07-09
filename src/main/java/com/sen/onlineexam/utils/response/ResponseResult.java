package com.sen.onlineexam.utils.response;

public class ResponseResult {

    /**
     * 成功标示
     */
    private boolean success;

    /**
     * 返回状态码
     */
    private int code;

    /**
     * 返回错误信息
     */
    private String message;

    /**
     * 返回对象
     */
    private Object data;

    public ResponseResult() {
        this.success = true;
        this.code = 200;
    }

    public ResponseResult(Object data) {
        this.success = true;
        this.code = 200;
        this.data = data;
    }

    public ResponseResult(ResultCode resultCode){
        this.success = false;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public ResponseResult(int code, String message) {
        this.success = false;
        this.code = code;
        this.message = message;
    }

    public static ResponseResult success() {
        return new ResponseResult();
    }

    public static ResponseResult success(Object data) {
        return new ResponseResult(data);
    }

    public static ResponseResult failure(ResultCode resultCode) {
        return new ResponseResult(resultCode);
    }

    public static ResponseResult failure(int code,String message) {
        return new ResponseResult(code,message);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
