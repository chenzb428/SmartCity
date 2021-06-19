package com.chen.smartcity.model;

public class UpdateUserInfoParams {

    public UpdateUserInfoParams(String nickName, String email, String phonenumber, String idCard, String sex) {
        this.nickName = nickName;
        this.email = email;
        this.phonenumber = phonenumber;
        this.idCard = idCard;
        this.sex = sex;
    }

    private String nickName;
    private String email;
    private String phonenumber;
    private String idCard;
    private String sex;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
