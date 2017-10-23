package ru.doubletapp.android.izjuminka.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by hash on 20/10/2017.
 */

public class SettingsLocalData {

    private static final String USER_PREFS_FILE_NAME = "settings_data";

    @NonNull
    private final SharedPreferences mPreferences;

    SettingsLocalData(@NonNull Context context) {
        mPreferences = context.getSharedPreferences(USER_PREFS_FILE_NAME, Context.MODE_PRIVATE);
    }

    private void putString(@NonNull String key, @Nullable String value) {
        mPreferences.edit().putString(key, value).apply();
    }

    @Nullable
    private String getString(@NonNull String key) {
        return mPreferences.getString(key, null);
    }

    private void putBoolean(String key, boolean value) {
        mPreferences.edit().putBoolean(key, value).apply();
    }

    private boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }
}
