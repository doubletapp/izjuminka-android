package ru.doubletapp.android.izjuminka.api.settings;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by hash on 21/10/2017.
 */
@Module
public class EditModule {

    @NonNull
    @Singleton
    @Provides
    EditRetrofit provideEditRetrofit(@NonNull Retrofit retrofit) {
        return retrofit.create(EditRetrofit.class);
    }

    @NonNull
    @Singleton
    @Provides
    EditInteractor provideEditInteractor(@NonNull EditRetrofit editRetrofit) {
        return new EditInteractor(editRetrofit);
    }
}
