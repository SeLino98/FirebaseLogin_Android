package com.example.firebaselogin;

public class UserAccount {

    private String idToken ; //파베 유아이디 고유정보 토큰 유니크 값임
    private  String email;
    private  String password;

    public UserAccount(){
        //디비조회할 때 생성자 생성은 필수
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

}
