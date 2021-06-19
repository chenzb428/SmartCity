package com.chen.smartcity.model.bean;

import java.util.List;

public class DingdanResult {

    private Integer code;
    private String msg;
    private List<RowsBean> rows;
    private Integer total;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DingdanResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", rows=" + rows +
                ", total=" + total +
                '}';
    }

    public static class RowsBean {
        private Integer id;
        private String orderNo;
        private Integer amount;
        private String orderStatus;
        private Integer userId;
        private String payTime;
        private String name;
        private String orderType;
        private String orderTypeName;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getOrderTypeName() {
            return orderTypeName;
        }

        public void setOrderTypeName(String orderTypeName) {
            this.orderTypeName = orderTypeName;
        }

        @Override
        public String toString() {
            return "RowsBean{" +
                    "id=" + id +
                    ", orderNo='" + orderNo + '\'' +
                    ", amount=" + amount +
                    ", orderStatus='" + orderStatus + '\'' +
                    ", userId=" + userId +
                    ", payTime='" + payTime + '\'' +
                    ", name='" + name + '\'' +
                    ", orderType='" + orderType + '\'' +
                    ", orderTypeName='" + orderTypeName + '\'' +
                    '}';
        }
    }
}
