package com.androidapk.diakonetapps.networkmarketing;

// Created by Emperor95 on 11/19/2018.

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Calendar;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "HoME";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String LICENSE_STATUS = "app_license_status";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setLicenseStatus(boolean status) {
        editor.putBoolean(LICENSE_STATUS, status);
        editor.commit();
    }

    public boolean getLicenseStatus() {
        return pref.getBoolean(LICENSE_STATUS, false);
    }

//    public void removeUSerStatus(){
//        editor.remove(USER_STATUS);
//        editor.commit();
//    }
    public void removeFirst(){
        editor.remove(IS_FIRST_TIME_LAUNCH);
        editor.commit();
    }

}
