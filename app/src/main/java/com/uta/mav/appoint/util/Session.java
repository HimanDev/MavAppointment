package com.uta.mav.appoint.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.uta.mav.appoint.dto.User;

public class Session {

    private SharedPreferences prefs;


    public static final String KEY_EMAIL="EMAIL";
    public static final String KEY_LAST_NAME="LAST_NAME";
    public static final String KEY_ROLE="ROLE";
    public static final String KEY_USER_ID="USER_ID";
    public static final String KEY_DEPT="DEPT";
    public static final String KEY_DEGREE_TYPE="DEGREE_TYPR";
    public static final String KEY_MAJOR="MAJOR";
    public static final String KEY_PHONE="PHONE";

    private SharedPreferences.Editor editor;



    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
        editor = prefs.edit();
    }

    public void setUser(User user) {
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_LAST_NAME, user.getLastNameInitial());
        editor.putString(KEY_ROLE, user.getRole());
        editor.putString(KEY_USER_ID, user.getStudentId());
        editor.putString(KEY_DEPT, user.getDept());
        editor.putString(KEY_DEGREE_TYPE, user.getDegreeType());
        editor.putString(KEY_MAJOR, user.getMajor());
        editor.putString(KEY_PHONE, user.getPhoneNo());

        editor.commit();


    }

    public String get(String key) {
        String value = prefs.getString(key,"");
        return value;
    }

    public void logout(){
        editor.clear();
        editor.apply();
    }
}