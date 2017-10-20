package ru.doubletapp.android.izjuminka;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import ru.doubletapp.android.izjuminka.dagger.AndroidApplicationModule;
import ru.doubletapp.android.izjuminka.dagger.ApplicationComponent;
import ru.doubletapp.android.izjuminka.dagger.DaggerApplicationComponent;

/**
 * Created by hash on 20/10/2017.
 */

public class IzjuminkaApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    public static IzjuminkaApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .androidApplicationModule(new AndroidApplicationModule(this))
                .build();

        instance = this;
    }

    @NonNull
    public static ApplicationComponent getApplicationComponent(@NonNull Context context) {
        return ((IzjuminkaApplication) context.getApplicationContext()).mApplicationComponent;
    }
}
