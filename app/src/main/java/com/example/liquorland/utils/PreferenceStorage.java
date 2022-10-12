package com.example.liquorland.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceStorage {
    private SharedPreferences myFile;
    private Context context;



    private static final String SHARED_PREF_NAME = "com.example.liquorland.utils.SHARED_PREF";
    private static final String USER_NAME = " com.example.liquorland.utils.USER_FIRSTNAME";
    private static final String USER_NUMBER = " com.example.liquorland.utils.USER_LASTNAME";
    private static final String USER_EMAIL = "com.example.liquorland.utils.USER_EMAIL";
    private static final String USER_PASSWORD = "com.example.liquorland.utils.USER_PASSWORD";
    private static final String USER_STATUS = "com.example.liquorland.utils.USER_STATUS";
    private static final String USER_TOKEN = "com.example.liquorland.utils.USER_TOKEN";

    public PreferenceStorage(Context context) {
        this.myFile = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        this.context = context;
    }

    public void SaveUserData(String name, String number, String email, String password) {
        // declare Editor as new instance variable

        SharedPreferences.Editor editor = myFile.edit();
        editor.putString(USER_NAME, name);
        editor.putString(USER_NUMBER, number);
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
        return myFile.getString(USER_EMAIL, "USER");
    }

    public  String getUserName(){
        return myFile.getString(USER_NAME, "USER");
    }

    public boolean authenticate(String name, String password) {
        String current_name = getUserName();
        String current_password = myFile.getString(USER_PASSWORD, SHARED_PREF_NAME);

        if (current_name.contentEquals(name) && current_password.contentEquals(password)) {

            return true;

        } else {

            return false;
        }
    }

    public String getAuthToken(){
       return myFile.getString(USER_TOKEN, null );

    }

    public void saveToken(String token){
        SharedPreferences.Editor editor = myFile.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }




    }

