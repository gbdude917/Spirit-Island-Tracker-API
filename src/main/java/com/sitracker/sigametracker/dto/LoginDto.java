package com.sitracker.sigametracker.dto;

public class LoginDto {

    public String username;

    public String password;

    public LoginDto() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
