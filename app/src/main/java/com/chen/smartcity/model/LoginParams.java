package com.chen.smartcity.model;

public class LoginParams {

    public LoginParams(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;

    public String getAccount() {
        return username;
    }

    public void setAccount(String account) {
        this.username = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
