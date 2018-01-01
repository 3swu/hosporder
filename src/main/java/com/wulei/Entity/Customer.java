package com.wulei.Entity;

public class Customer {
    private int customerid;

    private String name;

    private String mobile;

    public Customer(int customerid, String name, String mobile) {
        this.customerid = customerid;
        this.name = name;
        this.mobile = mobile;
    }

    public Customer() {

    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerid=" + customerid +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
