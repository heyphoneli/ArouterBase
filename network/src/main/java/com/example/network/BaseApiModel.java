package com.example.network;

/**
 * 接口返回数据基础结构
 * 使用java实现（kotlin data class创建的类是final类型，无法被继承，不方便做兼容）
 *
 * @param <T>
 */
public class BaseApiModel<T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static BaseApiModel netErr() {
        BaseApiModel baseApiModel = new BaseApiModel();
        baseApiModel.setCode(-999);
        baseApiModel.setMsg("网络异常");
        return baseApiModel;
    }
}
