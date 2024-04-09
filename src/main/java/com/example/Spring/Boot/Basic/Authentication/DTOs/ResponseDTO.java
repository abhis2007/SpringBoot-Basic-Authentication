package com.example.Spring.Boot.Basic.Authentication.DTOs;

public class ResponseDTO {

    public ResponseDTO(){}

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    private String UserName ;
    private String EmailId ;
    private String UserId ;
}


