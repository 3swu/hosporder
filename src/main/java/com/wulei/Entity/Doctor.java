package com.wulei.Entity;

public class Doctor {

    private int doctorid;

    private String name;

    private int catid;

    private String description;

    public Doctor(int doctorid, String name, int catid, String description) {
        this.doctorid = doctorid;
        this.name = name;
        this.catid = catid;
        this.description = description;
    }

    public Doctor() {

    }

    public int getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorid=" + doctorid +
                ", name='" + name + '\'' +
                ", catid=" + catid +
                ", description='" + description + '\'' +
                '}';
    }
}
