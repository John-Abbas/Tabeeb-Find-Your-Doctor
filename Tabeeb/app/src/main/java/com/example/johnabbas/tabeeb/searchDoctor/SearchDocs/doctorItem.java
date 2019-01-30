package com.example.johnabbas.tabeeb.searchDoctor.SearchDocs;

public class doctorItem {

    private int Age,Gender,Special,Verification;
    private String Fee,Hours,Location,Name,ID;


    public doctorItem(){}

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public void setGender(int Gender) {
        this.Gender = Gender;
    }

    public void setSpecial(int Special) {
        this.Special = Special;
    }

    public void setVerification(int Verification) {
        this.Verification = Verification;
    }

    public void setFee(String Fee) {
        this.Fee = Fee;
    }

    public void setHours(String Hours) {
        this.Hours = Hours;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getAge() {
        return Age;
    }

    public int getGender() {
        return Gender;
    }

    public int getSpecial() {
        return Special;
    }

    public int getVerification() {
        return Verification;
    }

    public String getFee() {
        return Fee;
    }

    public String getHours() {
        return Hours;
    }

    public String getLocation() {
        return Location;
    }

    public String getName() {
        return Name;
    }
}
