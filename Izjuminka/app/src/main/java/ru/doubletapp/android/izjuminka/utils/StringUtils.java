package ru.doubletapp.android.izjuminka.utils;

import android.support.annotation.NonNull;

/**
 * Created by Denis Akimov on 20.10.2017.
 */

public class StringUtils {

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static String getShortString(@NonNull String longString) {
        return longString.substring(0, Math.min(longString.length(), 70));
    }

}
