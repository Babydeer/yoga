package com.zero.yoga.bean.response;

import java.util.List;

/**
 * Created by zero on 2018/8/17.
 */

public class MerchanListResponse extends BaseResponse {


    /**
     * code : 0
     * data : {"rows":[{"id":1,"merchantName":"123瑜伽2222","address":"222","phoneNo":"22222","merchantPictureURL":null,"longitude":"22.5318175320","latitude":"113.9497815175","businessHours":"9:00-22:00"},{"id":3,"merchantName":"星宇瑜伽","address":"深圳市福田区泰然六路2号207","phoneNo":"15296503789","merchantPictureURL":null,"longitude":"114.03476348330996","latitude":"22.538404758145365","businessHours":"9:00-22:00"}],"total":4}
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
         * rows : [{"id":1,"merchantName":"123瑜伽2222","address":"222","phoneNo":"22222","merchantPictureURL":null,"longitude":"22.5318175320","latitude":"113.9497815175","businessHours":"9:00-22:00"},{"id":3,"merchantName":"星宇瑜伽","address":"深圳市福田区泰然六路2号207","phoneNo":"15296503789","merchantPictureURL":null,"longitude":"114.03476348330996","latitude":"22.538404758145365","businessHours":"9:00-22:00"}]
         * total : 4
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : 1
             * merchantName : 123瑜伽2222
             * address : 222
             * phoneNo : 22222
             * merchantPictureURL : null
             * longitude : 22.5318175320
             * latitude : 113.9497815175
             * businessHours : 9:00-22:00
             */

            private long id;
            private String merchantName;
            private String address;
            private String phoneNo;
            private String merchantPictureURL;
            private double longitude;
            private double latitude;
            private String businessHours;

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

            public String getPhoneNo() {
                return phoneNo;
            }

            public void setPhoneNo(String phoneNo) {
                this.phoneNo = phoneNo;
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

            public String getBusinessHours() {
                return businessHours;
            }

            public void setBusinessHours(String businessHours) {
                this.businessHours = businessHours;
            }
        }
    }
}
