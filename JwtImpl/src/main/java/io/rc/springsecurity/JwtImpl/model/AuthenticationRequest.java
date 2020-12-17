package io.rc.springsecurity.JwtImpl.model;

public class AuthenticationRequest {

    private String userename;
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String userename, String password) {
        this.userename = userename;
        this.password = password;
    }

    public String getUserename() {
        return userename;
    }

    public void setUserename(String userename) {
        this.userename = userename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
