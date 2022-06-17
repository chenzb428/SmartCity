package com.chen.smartcitydemo.model.bean;

public class User {


    /**
     * msg : 操作成功
     * code : 200
     * user : {"userId":1112566,"userName":"Chenzb","nickName":"老彬","email":"laobin2020@gmail.com","phonenumber":"17520318136","sex":"0","avatar":"","idCard":"456465464894465","balance":1000,"score":1000}
     */

    private String msg;
    private int code;
    private UserBean user;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
        return "User{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", user=" + user +
                '}';
    }

    public static class UserBean {
        /**
         * userId : 1112566
         * userName : Chenzb
         * nickName : 老彬
         * email : laobin2020@gmail.com
         * phonenumber : 17520318136
         * sex : 0
         * avatar :
         * idCard : 456465464894465
         * balance : 1000.0
         * score : 1000
         */

        private int userId;
        private String userName;
        private String password;
        private String nickName;
        private String email;
        private String phonenumber;
        private String sex;
        private String avatar;
        private String idCard;
        private double balance;
        private int score;

        public UserBean(String userName, String password, String nickName, String email, String phonenumber, String sex, String avatar, String idCard, double balance, int score) {
            this.userName = userName;
            this.password = password;
            this.nickName = nickName;
            this.email = email;
            this.phonenumber = phonenumber;
            this.sex = sex;
            this.avatar = avatar;
            this.idCard = idCard;
            this.balance = balance;
            this.score = score;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
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
