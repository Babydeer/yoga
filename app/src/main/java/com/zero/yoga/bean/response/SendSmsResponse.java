package com.zero.yoga.bean.response;

/**
 * Created by wanghuajun on 2018/8/14.
 */

public class SendSmsResponse extends BaseResponse {

    private Object data;

    public SendSmsResponse() {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "data=" + data +
                "} " + super.toString();
    }
}
