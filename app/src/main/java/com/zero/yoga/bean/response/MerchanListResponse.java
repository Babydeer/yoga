package com.zero.yoga.bean.response;

import java.util.List;

/**
 * Created by zero on 2018/8/17.
 */

public class MerchanListResponse extends BaseResponse {

    /**
     * code : 0
     * data : {"rows":[{"id":1,"merchantName":"123瑜伽2222","address":"222","merchantPictureURL":null,"longitude":"22.5318175320","latitude":"113.9497815175"},{"id":2,"merchantName":"123瑜伽2222","address":"222","merchantPictureURL":null,"longitude":"22.5318175320","latitude":"113.9497815175"},{"id":3,"merchantName":"星宇瑜伽","address":"南山区高新南十路12号201","merchantPictureURL":null,"longitude":"22.5318175320","latitude":"113.9497815175"}],"total":3}
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
         * rows : [{"id":1,"merchantName":"123瑜伽2222","address":"222","merchantPictureURL":null,"longitude":"22.5318175320","latitude":"113.9497815175"},{"id":2,"merchantName":"123瑜伽2222","address":"222","merchantPictureURL":null,"longitude":"22.5318175320","latitude":"113.9497815175"},{"id":3,"merchantName":"星宇瑜伽","address":"南山区高新南十路12号201","merchantPictureURL":null,"longitude":"22.5318175320","latitude":"113.9497815175"}]
         * total : 3
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
             * merchantPictureURL : null
             * longitude : 22.5318175320
             * latitude : 113.9497815175
             */

            private int id;
            private String merchantName;
            private String address;
            private String merchantPictureURL;
            private String longitude;
            private String latitude;

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
                double ret = 0;
                try {
                    ret = Double.parseDouble(longitude);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ret;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public double getLatitude() {
                double ret = 0;
                try {
                    ret = Double.parseDouble(latitude);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ret;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }
        }
    }
}
