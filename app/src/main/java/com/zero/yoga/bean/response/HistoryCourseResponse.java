package com.zero.yoga.bean.response;

import java.util.List;

/**
 * Created by zero on 2018/8/20.
 */

public class HistoryCourseResponse extends BaseResponse {
    /**
     * code : 0
     * data : {"rows":[{"id":1,"merCourId":5,"courseId":1,"startTime":1534759200000,"endTime":1534762800000,"teacherId":0,"teacherName":0,"signFlag":"0","signNum":1,"merchantId":3,"merchantName":"星宇瑜伽","courseUrl":""}],"total":1}
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
         * rows : [{"id":1,"merCourId":5,"courseId":1,"startTime":1534759200000,"endTime":1534762800000,"teacherId":0,"teacherName":0,"signFlag":"0","signNum":1,"merchantId":3,"merchantName":"星宇瑜伽","courseUrl":""}]
         * total : 1
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
             * merCourId : 5
             * courseId : 1
             * startTime : 1534759200000
             * endTime : 1534762800000
             * teacherId : 0
             * teacherName : 0
             * signFlag : 0
             * signNum : 1
             * merchantId : 3
             * merchantName : 星宇瑜伽
             * courseUrl :
             */

            private int id;
            private int merCourId;
            private int courseId;
            private long startTime;
            private long endTime;
            private int teacherId;
            private String teacherName;
            private int signFlag;
            private int signNum;
            private int merchantId;
            private String merchantName;
            private String courseUrl;
            private String courseName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMerCourId() {
                return merCourId;
            }

            public void setMerCourId(int merCourId) {
                this.merCourId = merCourId;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
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

            public int getTeacherId() {
                return teacherId;
            }

            public void setTeacherId(int teacherId) {
                this.teacherId = teacherId;
            }

            public String getTeacherName() {
                return teacherName;
            }

            public void setTeacherName(String teacherName) {
                this.teacherName = teacherName;
            }

            public int getSignFlag() {
                return signFlag;
            }

            public void setSignFlag(int signFlag) {
                this.signFlag = signFlag;
            }

            public int getSignNum() {
                return signNum;
            }

            public void setSignNum(int signNum) {
                this.signNum = signNum;
            }

            public int getMerchantId() {
                return merchantId;
            }

            public void setMerchantId(int merchantId) {
                this.merchantId = merchantId;
            }

            public String getMerchantName() {
                return merchantName;
            }

            public void setMerchantName(String merchantName) {
                this.merchantName = merchantName;
            }

            public String getCourseUrl() {
                return courseUrl;
            }

            public void setCourseUrl(String courseUrl) {
                this.courseUrl = courseUrl;
            }

            public String getCourseName() {
                return courseName;
            }

            public void setCourseName(String courseName) {
                this.courseName = courseName;
            }
        }
    }
}
