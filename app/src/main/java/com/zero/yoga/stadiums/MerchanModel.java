package com.zero.yoga.stadiums;

import android.location.Location;

import com.zero.yoga.bean.response.MerchanListResponse;
import com.zero.yoga.utils.LocationUtils;

/**
 * Created by zero on 2018/8/17.
 */

public class MerchanModel {

    private int id;
    private String merchantName;
    private String address;
    private String merchantPictureURL;
    private double longitude;
    private double latitude;

    private double distance;

    private String distanceStr;//显示用

    public MerchanModel() {
    }


    public static MerchanModel convertMerchanModel(MerchanListResponse.DataBean.RowsBean rowsBean, Location location) {
        double tmpdis = 0;
        if (location != null) {
            tmpdis = LocationUtils.gps2m(rowsBean.getLatitude(), rowsBean.getLongitude(), location.getLatitude(), location.getLongitude());
        }

        return new MerchanModel(rowsBean.getId(), rowsBean.getMerchantName(), rowsBean.getAddress(), rowsBean.getMerchantPictureURL(), rowsBean.getLongitude(), rowsBean.getLatitude(), tmpdis);
    }

    public MerchanModel(int id, String merchantName, String address, String merchantPictureURL, double longitude, double latitude, double distance) {
        this.id = id;
        this.merchantName = merchantName;
        this.address = address;
        this.merchantPictureURL = merchantPictureURL;
        this.longitude = longitude;
        this.latitude = latitude;
        this.distance = distance;
        this.distanceStr = getDistanceString(distance)
        ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMerchantPictureURL() {
        return merchantPictureURL;
    }

    public void setMerchantPictureURL(String merchantPictureURL) {
        this.merchantPictureURL = merchantPictureURL;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
        distanceStr = getDistanceString(distance);
    }

    private String getDistanceString(double distance) {
        String ret = "";
        //转为为米
        long dis = (long) (distance * 1000);
        if (dis <= 100) {
            ret = dis + "m";
        } else if (dis > 100 && dis <= 500) {
            ret = "< 500m";
        } else if (dis > 500 && dis <= 1000) {
            ret = " < 1Km";
        } else {
            ret = " > 1Km";
        }
        return ret;
    }

    public String getDistanceStr() {
        return distanceStr;
    }

    @Override
    public String toString() {
        return "MerchanModel{" +
                "id=" + id +
                ", merchantName='" + merchantName + '\'' +
                ", address='" + address + '\'' +
                ", merchantPictureURL='" + merchantPictureURL + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", distance=" + distance +
                ", distanceStr='" + distanceStr + '\'' +
                '}';
    }
}
