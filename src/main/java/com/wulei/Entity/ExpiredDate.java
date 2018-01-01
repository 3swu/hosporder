package com.wulei.Entity;

public class ExpiredDate {
    int dateid;

    public int getDateid() {
        return dateid;
    }

    public void setDateid(int dateid) {
        this.dateid = dateid;
    }

    public ExpiredDate(){

    }

    public ExpiredDate(int dateid){
        this.dateid = dateid;
    }

    @Override
    public String toString() {
        return "ExpiredDate{" +
                "dateid=" + dateid +
                '}';
    }
}
