package com.example.lettre;

public class User {

    private String uid,name,mail,dp,country;
    private  double lan,longt;

    public User(){

    }

    public User(String uid, String name, String mail, String dp,String country, double lan,double longt) {
        this.uid = uid;
        this.name = name;
        this.mail = mail;
        this.dp = dp;
        this.country = country;
        this.lan=lan;
        this.longt=longt;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLan() {
        return lan;
    }

    public void setLan(double lan) {
        this.lan = lan;
    }

    public double getLongt() {
        return longt;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }
}
