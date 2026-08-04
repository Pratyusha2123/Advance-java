package com.bloodcamp.model;

public class Donor {

    private int donorId;
    private String name;
    private int age;
    private String bloodGroup;
    private String phone;

    public Donor() {
    }
    public Donor(String name, int age, String bloodGroup, String phone) {
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.phone = phone;
    }
    public int getDonorId() {
        return donorId;
    }
    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
