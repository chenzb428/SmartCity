package com.chen.smartcity.model.bean;

public class MineUserResult {

    private String msg;
    private Integer code;
    private UserBean user;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MineUserResult{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", user=" + user +
                '}';
    }

    public static class UserBean {
        private Integer userId;
        private String userName;
        private String nickName;
        private String email;
        private String phonenumber;
        private String sex;
        private String avatar;
        private String idCard;
        private Double balance;
        private Integer score;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "UserBean{" +
                    "userId=" + userId +
                    ", userName='" + userName + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", email='" + email + '\'' +
                    ", phonenumber='" + phonenumber + '\'' +
                    ", sex='" + sex + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", idCard='" + idCard + '\'' +
                    ", balance=" + balance +
                    ", score=" + score +
                    '}';
        }
    }
}
