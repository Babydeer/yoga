package com.zero.yoga.bean.response;

/**
 * Created by wanghuajun on 2018/8/14.
 */

public class LoginResponse extends BaseResponse {


    /**
     * code : 0
     * data : {"headerPicture":"","grade":"","nickname":"","phoneNo":"18670301864","token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODY3MDMwMTg2NCIsImV4cCI6MTUzNDg0Mjg2M30.z1h4pb0wdvO28aLA8UNKFkftXwbvYrIIsLq_efhlsaXYR5IeKDFuEvRPV_xUxddFRt7Z6xQefd9gPCOgj_ss0w","username":"18670301864"}
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
         * headerPicture :
         * grade :
         * nickname :
         * phoneNo : 18670301864
         * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODY3MDMwMTg2NCIsImV4cCI6MTUzNDg0Mjg2M30.z1h4pb0wdvO28aLA8UNKFkftXwbvYrIIsLq_efhlsaXYR5IeKDFuEvRPV_xUxddFRt7Z6xQefd9gPCOgj_ss0w
         * username : 18670301864
         */

        private String headerPicture;
        private int grade;
        private String nickname;
        private String phoneNo;
        private String token;
        private String username;

        public String getHeaderPicture() {
            return headerPicture;
        }

        public void setHeaderPicture(String headerPicture) {
            this.headerPicture = headerPicture;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
