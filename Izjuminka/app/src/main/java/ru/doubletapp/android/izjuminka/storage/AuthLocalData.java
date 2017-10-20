package ru.doubletapp.android.izjuminka.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Created by hash on 20/10/2017.
 */

public class AuthLocalData {

    private static final String USER_DATA_FILE_NAME = "auth_data";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USER_ID = "user_id";

    @NonNull
    private final SharedPreferences mPreferences;

    AuthLocalData(@NonNull Context context) {
        mPreferences = context.getSharedPreferences(USER_DATA_FILE_NAME, Context.MODE_PRIVATE);
    }

    private void putString(String key, String value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private String getString(String key) {
        return mPreferences.getString(key, null);
    }

    private void putInt(String key, int value) {
        mPreferences.edit().putInt(key, value).apply();
    }

    private int getInt(String key) {
        return mPreferences.getInt(key, 0);
    }

    public void setToken(String accessToken) {
        putString(KEY_TOKEN, accessToken);
    }

    public String getToken() {
        return getString(KEY_TOKEN);
    }

    public void setEmail(String refreshToken) {
        putString(KEY_EMAIL, refreshToken);
    }

    public String getEmail() {
        return getString(KEY_EMAIL);
    }

    public void setUserId(String user) {
        putString(KEY_USER_ID, user);
    }

    public String getUserId() {
        return getString(KEY_USER_ID);
    }

    public void logout() {
        mPreferences.edit().clear().apply();
    }
}
