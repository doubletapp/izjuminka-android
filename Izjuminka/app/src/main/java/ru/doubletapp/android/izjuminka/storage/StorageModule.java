package ru.doubletapp.android.izjuminka.storage;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hash on 20/10/2017.
 */

@Module
public class StorageModule {

    @NonNull
    @Singleton
    @Provides
    public AuthLocalData provideAuthLocalData(@NonNull Context context) {
        return new AuthLocalData(context);
    }

    @NonNull
    @Singleton
    @Provides
    public SettingsLocalData provideSettings(@NonNull Context context) {
        return new SettingsLocalData(context);
    }
}
