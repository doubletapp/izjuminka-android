package ru.doubletapp.android.izjuminka.dagger;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hash on 20/10/2017.
 */

@Module
public class AndroidApplicationModule {
    @NonNull
    private final Context mApplicationContext;

    public AndroidApplicationModule(@NonNull Context context) {
        mApplicationContext = context.getApplicationContext();
    }

    @NonNull
    @Provides
    public Context provideApplicationContext() {
        return mApplicationContext;
    }
}
