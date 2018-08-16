package com.zero.yoga.bean.response;

/**
 * Created by wanghuajun on 2018/8/14.
 */

public class LoginResponse extends BaseResponse {


    /**
     * code : 0
     * data : {"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODY3MDMwMTg2NCIsImV4cCI6MTUzNDQ5ODAxNn0.9fAHg_-puGycyuVgASsrinRaMYxSJr2AixlfHu8jOP9WC3M6H-IM1n_bGPChT6hcefE_VSbLGJDCmDbnipUNaw"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODY3MDMwMTg2NCIsImV4cCI6MTUzNDQ5ODAxNn0.9fAHg_-puGycyuVgASsrinRaMYxSJr2AixlfHu8jOP9WC3M6H-IM1n_bGPChT6hcefE_VSbLGJDCmDbnipUNaw
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "data=" + data +
                "} " + super.toString();
    }
}
