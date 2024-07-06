package com.example.mobilerestaurant.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mobilerestaurant.model.User;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "my_shared_preff";

    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }

        return mInstance;
    }
    public void saveUser(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("_id",user.getId());
        editor.putString("name",user.getName());

        editor.apply();

    }

    public boolean isLoggedin() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("_id",null) != null;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(

                sharedPreferences.getString("_id",null),
                sharedPreferences.getString("email",null),
                sharedPreferences.getString("name",null)
        );
    }

    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
