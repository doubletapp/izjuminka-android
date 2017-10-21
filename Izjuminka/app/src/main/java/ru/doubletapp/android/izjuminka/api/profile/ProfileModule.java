package ru.doubletapp.android.izjuminka.api.profile;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hash on 21/10/2017.
 */

@Module
public class ProfileModule {

    @NonNull
    @Singleton
    @Provides
    ProfileInteractor provideProfileInteractor() {
        return new ProfileInteractor();
    }
}
