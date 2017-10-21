package ru.doubletapp.android.izjuminka.api.authorization;

import android.app.AuthenticationRequiredException;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.doubletapp.android.izjuminka.storage.AuthLocalData;

/**
 * Created by hash on 21/10/2017.
 */

@Module
public class AuthModule {

    @NonNull
    @Singleton
    @Provides
    AuthRetrofit provideAuthRetrofit(@NonNull Retrofit retrofit) {
        return retrofit.create(AuthRetrofit.class);
    }

    @NonNull
    @Singleton
    @Provides
    AuthInteractor provideAuthInteractor(@NonNull AuthRetrofit authRetrofit,
                                         @NonNull AuthLocalData localData) {
        return new AuthInteractor(authRetrofit, localData);
    }
}
