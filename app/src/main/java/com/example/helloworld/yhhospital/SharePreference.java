package com.example.helloworld.yhhospital;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreference {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public SharePreference(Context context) {
        pref = context.getSharedPreferences("keb", Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public String getStringData(String data) {
        return pref.getString(data, "");
    }

    public void setStringData(String name, String data) {
        pref = (SharedPreferences) editor.putString(name, data);
        editor.commit();
    }
}
