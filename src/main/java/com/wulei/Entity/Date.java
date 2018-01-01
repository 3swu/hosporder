package com.wulei.Entity;

public class Date {

    private int dateid;

    private String name;

    private int doctorid;

    private int number;

    private int maxnumber;

    public Date(int dateid, String name, int doctorid, int number, int maxnumber) {
        this.dateid = dateid;
        this.name = name;
        this.doctorid = doctorid;
        this.number = number;
        this.maxnumber = maxnumber;
    }

    public Date() {

    }

    public int getDateid() {
        return dateid;
    }

    public void setDateid(int dateid) {
        this.dateid = dateid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMaxnumber() {
        return maxnumber;
    }

    public void setMaxnumber(int maxnumber) {
        this.maxnumber = maxnumber;
    }

    @Override
    public String toString() {
        return "Date{" +
                "dateid=" + dateid +
                ", name='" + name + '\'' +
                ", doctorid=" + doctorid +
                ", number=" + number +
                ", maxnumber=" + maxnumber +
                '}';
    }
}
