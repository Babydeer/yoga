package com.zero.yoga.stadiums;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import com.zero.yoga.bean.response.MerchanListResponse;
import com.zero.yoga.utils.LocationUtils;

/**
 * Created by zero on 2018/8/17.
 */

public class MerchanModel implements Parcelable {

    private long id;
    private String merchantName;
    private String address;
    private String merchantPictureURL;
    private double longitude;
    private double latitude;
    private String phoneNo;
    private String businessHours;

    private double distance;

    private String distanceStr;//显示用

    public MerchanModel() {
    }


    public static MerchanModel convertMerchanModel(MerchanListResponse.DataBean.RowsBean rowsBean, Location location) {
        double tmpdis = 0;
        if (location != null) {
            tmpdis = LocationUtils.gps2m(rowsBean.getLatitude(), rowsBean.getLongitude(), location.getLatitude(), location.getLongitude());
        }

        return new MerchanModel(rowsBean.getId(), rowsBean.getMerchantName(), rowsBean.getAddress(), rowsBean.getMerchantPictureURL(), rowsBean.getPhoneNo(), rowsBean.getBusinessHours(), rowsBean.getLongitude(), rowsBean.getLatitude(), tmpdis);
    }

    public MerchanModel(long id, String merchantName, String address, String merchantPictureURL, String phoneNo, String businessHours, double longitude, double latitude, double distance) {
        this.id = id;
        this.merchantName = merchantName;
        this.address = address;
        this.merchantPictureURL = merchantPictureURL;
        this.phoneNo = phoneNo;
        this.businessHours = businessHours;
        this.longitude = longitude;
        this.latitude = latitude;
        this.distance = distance;
        this.distanceStr = getDistanceString(distance);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
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
            ret = " < 1km";
        } else {
            ret = " > 1km";
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.merchantName);
        dest.writeString(this.address);
        dest.writeString(this.merchantPictureURL);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
        dest.writeString(this.phoneNo);
        dest.writeString(this.businessHours);
        dest.writeDouble(this.distance);
        dest.writeString(this.distanceStr);
    }

    protected MerchanModel(Parcel in) {
        this.id = in.readLong();
        this.merchantName = in.readString();
        this.address = in.readString();
        this.merchantPictureURL = in.readString();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.phoneNo = in.readString();
        this.businessHours = in.readString();
        this.distance = in.readDouble();
        this.distanceStr = in.readString();
    }

    public static final Parcelable.Creator<MerchanModel> CREATOR = new Parcelable.Creator<MerchanModel>() {
        @Override
        public MerchanModel createFromParcel(Parcel source) {
            return new MerchanModel(source);
        }

        @Override
        public MerchanModel[] newArray(int size) {
            return new MerchanModel[size];
        }
    };
}
