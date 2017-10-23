package ru.doubletapp.android.izjuminka.api.profile;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by hash on 21/10/2017.
 */

@Module
public class ProfileModule {

    @NonNull
    @Singleton
    @Provides
    ProfileRetrofit provideProfileRetrofit(@NonNull Retrofit retrofit) {
        return retrofit.create(ProfileRetrofit.class);
    }

    @NonNull
    @Singleton
    @Provides
    ProfileInteractor provideProfileInteractor(@NonNull ProfileRetrofit profileRetrofit) {
        return new ProfileInteractor(profileRetrofit);
    }
}
