package com.example.liquorland.Networking.POJO;

import android.text.Editable;

public class UserRequest {
        private String username;
        private String password;
        private String phoneNumber, email;


    public UserRequest(String username, Editable editable, String phoneNumber, String email) {
    }

    public UserRequest(String username, Editable password) {
        this.username = String.valueOf(username);
        this.password = String.valueOf(password);
    }

    public UserRequest(String username, String password, String phoneNumber, String email) {
        this.username =String.valueOf(username);
        this.password = String.valueOf(password);
        this.phoneNumber =String.valueOf(phoneNumber);
        this.email =String.valueOf(email);
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
