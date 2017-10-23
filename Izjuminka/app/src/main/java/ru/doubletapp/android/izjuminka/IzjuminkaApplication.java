package ru.doubletapp.android.izjuminka;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

import ru.doubletapp.android.izjuminka.dagger.AndroidApplicationModule;
import ru.doubletapp.android.izjuminka.dagger.ApplicationComponent;
import ru.doubletapp.android.izjuminka.dagger.DaggerApplicationComponent;

/**
 * Created by hash on 20/10/2017.
 */

public class IzjuminkaApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    private VKAccessTokenTracker tracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(@Nullable VKAccessToken oldToken, @Nullable VKAccessToken newToken) {
            if (newToken == null) {
                // TODO: 20.10.2017 logout
            }
        }
    };

    public static IzjuminkaApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .androidApplicationModule(new AndroidApplicationModule(this))
                .build();

        instance = this;
        tracker.startTracking();
        VKSdk.initialize(this);
    }

    @NonNull
    public static ApplicationComponent getApplicationComponent(@NonNull Context context) {
        return ((IzjuminkaApplication) context.getApplicationContext()).mApplicationComponent;
    }
}
