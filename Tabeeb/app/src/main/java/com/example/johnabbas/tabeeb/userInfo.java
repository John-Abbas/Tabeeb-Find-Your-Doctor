package com.example.johnabbas.tabeeb;

public class userInfo {

    private String email,name,gender,userType,phoneNum;
    private int age;

    public userInfo(){

    }

    public userInfo(String email, String name, String gender, int age, String userType, String phoneNum) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.userType = userType;
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getUserType() {
        return userType;
    }
}
