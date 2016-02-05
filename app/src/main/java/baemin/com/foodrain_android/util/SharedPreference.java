package baemin.com.foodrain_android.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    private static SharedPreference instance;
    private Context mContext;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;

    public SharedPreference() {
    }

    public SharedPreference(Context context) {
        mPref = context.getSharedPreferences(Constants.PREF_NAME, mContext.MODE_PRIVATE);
    }

    public static SharedPreference getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreference(context.getApplicationContext());
        }
        return instance;
    }

    public boolean getBooleanPreference(String key) {
        return mPref.getBoolean(key, false);
    }

    public double getDoublePreference(String key) {
        return (double) mPref.getFloat(key, -1);
    }

    public String getStringPreference(String key) {
        return mPref.getString(key, "null");
    }


    public void putBooleanPreference(String key, boolean value) {
        mEditor = mPref.edit();
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public void putDoublePreference(String key, double value) {
        mEditor = mPref.edit();
        mEditor.putFloat(key, (float) value);
        mEditor.commit();
    }

    public void putStringPreference(String key, String value) {
        mEditor = mPref.edit();

        mEditor.putString(key, value);
        mEditor.commit();
    }

    public void removePreference(String key) {
        mEditor = mPref.edit();
        mEditor.remove(key);
        mEditor.commit();
    }

    public void removeAllPreferences() {
        mEditor = mPref.edit();
        mEditor.clear();
        mEditor.commit();
    }
}
