package com.example.Spring.Boot.Basic.Authentication.Entity;

public class LoginRequest {
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    private String UserName ;
    private String Password ;
}
