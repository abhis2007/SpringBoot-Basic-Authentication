package com.example.Spring.Boot.Basic.Authentication.Entity;

public class SignUpRequest {
    public SignUpRequest() {
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    private String UserName ;
    private String UserId ;
    private String Password;
    private String EmailId ;
}
