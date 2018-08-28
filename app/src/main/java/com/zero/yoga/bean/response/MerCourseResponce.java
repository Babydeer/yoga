package com.zero.yoga.bean.response;

import java.util.List;

/**
 * Created by zero on 2018/8/20.
 */

public class MerCourseResponce extends BaseResponse {


    /**
     * code : 0
     * data : {"rows":[{"id":4,"courseId":1,"courseName":"基础瑜伽1","tercherId":2,"tercherName":"Tony","startTime":1534723200000,"endTime":1534726800000,"signNum":0,"orderFlag":"0"},{"id":5,"courseId":1,"courseName":"基础瑜伽2","tercherId":3,"tercherName":"Lisa","startTime":1534759200000,"endTime":1534762800000,"signNum":0,"orderFlag":"0"},{"id":6,"courseId":1,"courseName":"基础瑜伽2","tercherId":3,"tercherName":"Lisa","startTime":1534723200000,"endTime":1534726800000,"signNum":0,"orderFlag":"0"}],"total":3}
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
         * rows : [{"id":4,"courseId":1,"courseName":"基础瑜伽1","tercherId":2,"tercherName":"Tony","startTime":1534723200000,"endTime":1534726800000,"signNum":0,"orderFlag":"0"},{"id":5,"courseId":1,"courseName":"基础瑜伽2","tercherId":3,"tercherName":"Lisa","startTime":1534759200000,"endTime":1534762800000,"signNum":0,"orderFlag":"0"},{"id":6,"courseId":1,"courseName":"基础瑜伽2","tercherId":3,"tercherName":"Lisa","startTime":1534723200000,"endTime":1534726800000,"signNum":0,"orderFlag":"0"}]
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
             * id : 4
             * courseId : 1
             * courseName : 基础瑜伽1
             * tercherId : 2
             * tercherName : Tony
             * startTime : 1534723200000
             * endTime : 1534726800000
             * signNum : 0
             * orderFlag : 0
             */

            private long id;
            private long courseId;
            private String courseName;
            private long tercherId;
            private String tercherName;
            private long startTime;
            private long endTime;
            private int signNum;
            private int orderFlag;
            private String courseUrl;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getCourseId() {
                return courseId;
            }

            public void setCourseId(long courseId) {
                this.courseId = courseId;
            }

            public String getCourseName() {
                return courseName;
            }

            public void setCourseName(String courseName) {
                this.courseName = courseName;
            }

            public long getTercherId() {
                return tercherId;
            }

            public void setTercherId(long tercherId) {
                this.tercherId = tercherId;
            }

            public String getTercherName() {
                return tercherName;
            }

            public void setTercherName(String tercherName) {
                this.tercherName = tercherName;
            }

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            public int getSignNum() {
                return signNum;
            }

            public void setSignNum(int signNum) {
                this.signNum = signNum;
            }

            public int getOrderFlag() {
                return orderFlag;
            }

            public void setOrderFlag(int orderFlag) {
                this.orderFlag = orderFlag;
            }

            public String getCourseUrl() {
                return courseUrl;
            }

            public void setCourseUrl(String courseUrl) {
                this.courseUrl = courseUrl;
            }
        }
    }
}
