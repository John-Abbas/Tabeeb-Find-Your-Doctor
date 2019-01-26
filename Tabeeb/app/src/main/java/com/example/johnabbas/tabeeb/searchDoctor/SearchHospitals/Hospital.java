package com.example.johnabbas.tabeeb.searchDoctor.SearchHospitals;

public class Hospital {

    private String Latitude,Longitude,Location,Name,Key;
    private int Verification;

    public Hospital() {
    }

    public String getKey() {return Key; }

    public void setKey(String key) {Key = key; }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public String getLocation() {
        return Location;
    }

    public String getName() {
        return Name;
    }

    public int getVerification() {
        return Verification;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setVerification(int verification) {
        Verification = verification;
    }
}
