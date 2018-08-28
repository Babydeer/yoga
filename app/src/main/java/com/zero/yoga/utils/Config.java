package com.zero.yoga.utils;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

/**
 * Created by zero on 2017/11/21.
 * Describe: function
 */

public class Config {

    public static int mCount = 0;

    public static void init(Context context) {
        SpfUtils.setApplication((Application) context.getApplicationContext());
    }

//    public static String getCookie() {
//        return SpfUtils.getString("cookie");
//    }
//
//    public static void setCookie(String cookie) {
//        SpfUtils.putString("cookie", cookie);
//    }

    public static class UserInfo {

        public static String getHeaderPicture() {
            return SpfUtils.getString("headerPicture");
        }

        public static void setHeaderPicture(String headerPicture) {
            SpfUtils.putString("headerPicture", headerPicture);
        }

        public static int getGrade() {
            return SpfUtils.getInt("grade");
        }

        public static void setGrade(int grade) {
            SpfUtils.putInt("grade", grade);
        }

        public static String getNickname() {
            return SpfUtils.getString("nickname");
        }

        public static void setNickname(String nickname) {
            SpfUtils.putString("nickname", nickname);
        }

        public static String getPhotoPath() {
            return SpfUtils.getString("photoPath");
        }

        public static void setPhotoPath(String photoPath) {
            SpfUtils.putString("photoPath", photoPath);
        }

        public static String getPhoneNo() {
            return SpfUtils.getString("phoneNo");
        }

        public static void setPhoneNo(String phoneNo) {
            SpfUtils.putString("phoneNo", phoneNo);
        }

        public static String getUsername() {
            return SpfUtils.getString("username");
        }

        public static void setUsername(String username) {
            SpfUtils.putString("username", username);
        }

        public static boolean getLogin() {
            return SpfUtils.getBoolean("login");
        }

        public static void setLogin(boolean login) {
            SpfUtils.putBoolean("login", login);
        }

        public static String toStrings() {
            return "{UserInfo headerPicture:" + getHeaderPicture() + "\n"
                    + "grade: " + getGrade() + "\n"
                    + "nickname: " + getNickname() + "\n"
                    + "photoPath: " + getPhotoPath() + "\n"
                    + "phoneNo: " + getPhoneNo() + "\n"
                    + "username: " + getUsername() + "\n"
                    + "token: " + Config.geToken() + "\n"
                    + "}";
        }

        public static void clear() {
            Config.setToken("");
            setHeaderPicture("");
            setGrade(0);
            setNickname("");
            setPhoneNo("");
            setUsername("");
            setPhotoPath("");
            setLogin(false);
        }
    }

    public static String geToken() {
        return SpfUtils.getString("token");
    }

    public static void setToken(String token) {
        SpfUtils.putString("token", token);
    }

}
