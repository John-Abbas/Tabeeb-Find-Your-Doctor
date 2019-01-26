package com.example.johnabbas.tabeeb;

public class userInfo {

    String email;
    String name;
    String gender;
    int age;
    String userType;

    public userInfo(){

    }

    public userInfo(String email, String name, String gender, int age, String userType) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.userType = userType;
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
