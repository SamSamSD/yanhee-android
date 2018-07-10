package com.example.helloworld.yhhospital;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Map;

public class SharePreference {
    private final static String PREF_FILE = "keb";

    public String getStringData(Context context, String data) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        return settings.getString(data, "");
    }

    public void setStringData(Context context, String name, String data) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(name, data);
        editor.apply();
    }

    public void setIntData(Context context, String name, int data) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(name, data);
        editor.apply();
    }
}
