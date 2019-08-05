package com.fangzhizun.cowxgzh.entity;

/**
 * 封装json对象，所有返回结果都使用它
 */
public class Result<T> {

    private Integer code;// 是否成功标志

    private String msg;// 错误信息

    private T body;// 成功时返回的数据

    public Result() {
    }

    public Result(Integer code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", msg=" + msg + ", body=" + body + "]";
    }
}
