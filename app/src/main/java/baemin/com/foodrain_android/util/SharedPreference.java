package baemin.com.foodrain_android.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    private static SharedPreference instance;
    private Context mContext;
    private SharedPreferences mPref;
    //            = mContext.getSharedPreferences(Constants.PREF, mContext.MODE_PRIVATE);
    private SharedPreferences.Editor mEditor;

    public SharedPreference() {
    }

    public SharedPreference(Context context) {
        mPref = context.getSharedPreferences(Constants.PREF, mContext.MODE_PRIVATE);
    }

    public static SharedPreference getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreference(context.getApplicationContext());
        }
        return instance;
    }


    public String getRegion() {
        return mPref.getString(Constants.PREF_REGION, "");
    }

    public void putRegion(String region) {
        mEditor = mPref.edit();
        mEditor.putString(Constants.PREF_REGION, region);
        mEditor.commit();
    }

    /**
     * Remove all keys from SharedPreferences.
     */
    private boolean mBulkUpdate = false;
    public void clear() {
        mEditor = mPref.edit();
        mEditor.clear();
        mEditor = null;
//        doEdit();
//        mEditor.clear();
//        doCommit();
    }

    public void edit() {
        mBulkUpdate = true;
        mEditor = mPref.edit();
    }

    public void commit() {
        mBulkUpdate = false;
        mEditor.commit();
        mEditor = null;
    }

    private void doEdit() {
        if (!mBulkUpdate && mEditor == null) {
            mEditor = mPref.edit();
        }
    }

    private void doCommit() {
        if (!mBulkUpdate && mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }
}
