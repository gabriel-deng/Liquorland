package com.example.liquorland.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.liquorland.AuthenticationActivity;

public class PreferenceStorage {
    private SharedPreferences myFile;
    private Context context;

    private static final String SHARED_PREF_NAME = "com.example.liquorland.utils.SHARED_PREF";
    private static final String USER_FIRSTNAME = " com.example.liquorland.utils.USER_FIRSTNAME";
    private static final String USER_LASTNAME = " com.example.liquorland.utils.USER_LASTNAME";
    private static final String USER_EMAIL = "com.example.liquorland.utils.USER_EMAIL";
    private static final String USER_PASSWORD = "com.example.liquorland.utils.USER_PASSWORD";
    private static final String USER_STATUS = "com.example.liquorland.utils.USER_STATUS";

    public PreferenceStorage(Context context) {
        this.myFile = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        this.context = context;
    }

    public void SaveUserData(String fname, String lname, String email, String password) {
        // declare Editor as new instance variable

        SharedPreferences.Editor editor = myFile.edit();
        editor.putString(USER_FIRSTNAME, fname);
        editor.putString(USER_LASTNAME, lname);
        editor.putString(USER_EMAIL, email);
        editor.putString(USER_PASSWORD, password);
        editor.apply();
    }

    public void setLoggingInStatus(boolean status) {
        SharedPreferences.Editor editor = myFile.edit();
        editor.putBoolean(USER_STATUS, status);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return myFile.getBoolean(USER_STATUS, false);
    }

    public String getUserEmail() {
        return myFile.getString(USER_EMAIL, "BUYER");
    }

    public  String getUserFirstname(){
        return myFile.getString(USER_FIRSTNAME, "USER");
    }

    public boolean authenticate(String email, String password) {
        String current_email = getUserEmail();
        String current_password = myFile.getString(USER_PASSWORD, SHARED_PREF_NAME);

        if (current_email.contentEquals(email) && current_password.contentEquals(password)) {

            return true;

        } else {

            return false;
        }
    }



    }

