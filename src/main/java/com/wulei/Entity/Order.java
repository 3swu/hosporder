package com.wulei.Entity;

public class Order {

    private int orderid;

    private int customerid;

    private int dateid;

    private String status;

    private String time;

    public Order(int orderid, int customerid, int dateid, String status, String time) {
        this.orderid = orderid;
        this.customerid = customerid;
        this.dateid = dateid;
        this.status = status;
        this.time = time;
    }

    public Order() {

    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getDateid() {
        return dateid;
    }

    public void setDateid(int dateid) {
        this.dateid = dateid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid=" + orderid +
                ", customerid=" + customerid +
                ", dateid=" + dateid +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
