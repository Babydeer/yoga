package com.zero.yoga.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.net.URISyntaxException;

public class MapUtils {

    public static final String PKG_BAIDU = "com.baidu.BaiduMap";
    public static final String PKG_GAODE = "com.autonavi.minimap";
    public static final String PKG_GOOGLE = "com.google.android.apps.maps";

    //高德转百度
    public static double[] bd_encrypt(double gg_lat, double gg_lon) {
        double x = gg_lon, y = gg_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * Math.PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * Math.PI);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        double[] result = new double[]{bd_lat, bd_lon};
        return result;
    }

    //百度转高德
    public static double[] bd_decrypt(double bd_lat, double bd_lon) {
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * Math.PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * Math.PI);
        double gg_lon = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);

        double[] result = new double[]{gg_lat, gg_lon};
        return result;
    }


    public static boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static void goToGoogle(Context context, double lat, double lon, String address) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q="
                + lat + "," + lon
                + ", + Sydney +Australia");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        context.startActivity(mapIntent);
    }


    public static void goToBaidu(Context context, double lat, double lon, String address) {
        Intent intent = null;
        try {
            intent = Intent.getIntent("intent://map/direction?destination=latlng:"
                    + lat + ","
                    + lon + "|name:" + address + // 终点
                    "&mode=driving&" + // 导航路线方式
                    "region=北京" + //
                    "&src=新疆和田#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        context.startActivity(intent);

    }


    public static void goToGaode(Context context, double lat, double lon, String address) {//传的是百度坐标
        double[] gg = bd_decrypt(lat, lon);
        Intent intent = null;
        try {
            intent = Intent.getIntent("androidamap://navi?sourceApplication=新疆和田&poiname=" + address + "&lat="
                    + gg[0]
                    + "&lon="
                    + gg[1] + "&dev=0");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        context.startActivity(intent);
    }


    public static void goToMap(Context context, double lat, double lon, String address) {
        if (checkApkExist(context, PKG_BAIDU)) {
            goToBaidu(context, lat, lon, address);
            return;
        }

        if (checkApkExist(context, PKG_GAODE)) {
            goToGaode(context, lat, lon, address);
            return;
        }

        if (checkApkExist(context, PKG_GOOGLE)) {
            goToGoogle(context, lat, lon, address);
            return;
        }

    }


}
