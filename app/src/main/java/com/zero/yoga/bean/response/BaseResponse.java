package com.zero.yoga.bean.response;

import java.io.Serializable;

/**
 * Created by zero on 2018/8/13.
 */

public class BaseResponse implements Serializable{

    private boolean success;
    private int code;
    private String msg;

    public BaseResponse(boolean success, int code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse() {
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public String toString() {
        return "BaseResponse{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
