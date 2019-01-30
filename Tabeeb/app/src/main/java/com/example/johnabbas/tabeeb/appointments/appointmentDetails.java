package com.example.johnabbas.tabeeb.appointments;

public class appointmentDetails {

    private String userID,docID,status,appTime,comment;
    private String docName,key;

    public appointmentDetails(String userID, String docID, String status, String appTime,String comment,String docName,String key) {
        this.userID = userID;
        this.docID = docID;
        this.status = status;
        this.appTime = appTime;
        this.comment = comment;
        this.docName = docName;
        this.key = key;
    }

    public appointmentDetails(String userID, String docID, String status, String appTime,String comment,String docName) {
        this.userID = userID;
        this.docID = docID;
        this.status = status;
        this.appTime = appTime;
        this.comment = comment;
        this.docName = docName;
    }

    public appointmentDetails() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getComment() {
        return comment;
    }

    public String getUserID() {
        return userID;
    }

    public String getDocID() {
        return docID;
    }

    public String getStatus() {
        return status;
    }

    public String getAppTime() {
        return appTime;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAppTime(String appTime) {
        this.appTime = appTime;
    }
}
