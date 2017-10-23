package ru.doubletapp.android.izjuminka.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Denis Akimov on 20.10.2017.
 */

public class ActivityUtils {

    public static void replaceFragment(FragmentManager manager, int containerId, @Nullable Fragment fragment, String fragmentTag,
                                       boolean isAddToBackStack, @Nullable Bundle args) {
        if (fragment == null) return;
        FragmentTransaction transaction = manager.beginTransaction();
        if (args != null) fragment.setArguments(args);
        transaction.replace(containerId, fragment);
        if (isAddToBackStack) transaction.addToBackStack(fragmentTag);
        transaction.commit();
    }

}
